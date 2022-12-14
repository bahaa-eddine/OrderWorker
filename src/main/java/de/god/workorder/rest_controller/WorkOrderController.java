package de.god.workorder.rest_controller;

import de.god.workorder.entity.WorkOrder;
import de.god.workorder.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author belbaghazaoui
 *
 */
@RestController
@RequestMapping("/api/work-order")
@RequiredArgsConstructor
public class WorkOrderController {

    private final ValidationService validationService;

    @PostMapping("/validation")
    public ResponseEntity<String> validation(@RequestBody WorkOrder workOrder){
        return new ResponseEntity<>(validationService.Validate(workOrder), HttpStatus.OK);
    }
}
