package de.god.workorder.mvc_controller;

import org.apache.logging.log4j.Level;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.god.workorder.entity.WorkOrder;
import de.god.workorder.mvc_controller.model.WorkOrderModel;
import de.god.workorder.service.ValidationHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @author belbaghazaoui
 *
 */
@Controller
@RequestMapping("/validation-history")
@RequiredArgsConstructor
@Log4j2
public class ValidationHistoryMvcController {

    private final ObjectMapper objectMapper;
    private final ValidationHistoryService validationHistoryService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("workOrderModel", new WorkOrderModel());
        return "index";
    }

    @PostMapping("/")
    public String validate(@ModelAttribute("workOrderModel") WorkOrderModel workOrderModel, Model model) {
        try {
            WorkOrder workOrder = objectMapper.readValue(workOrderModel.getJson(), WorkOrder.class);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080//api/work-order/validation", workOrder, String.class);
            workOrderModel.setResult(response.getBody());
        } catch (RestClientException e) {
            workOrderModel.setResult(" " + e.getMessage().substring(e.getMessage().indexOf("[") + 1, e.getMessage().indexOf("]")).replaceAll(",","\n"));
        } catch (Exception e) {
            log.log(Level.ERROR, e);
        }

        model.addAttribute("workOrderModel", workOrderModel);

        return "index";
    }

    @GetMapping("/getall")
    public String getAll(Model model){
        model.addAttribute("workOrders", validationHistoryService.getAll());
        return "getall";
    }
}
