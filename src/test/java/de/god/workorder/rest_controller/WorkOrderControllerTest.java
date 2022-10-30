package de.god.workorder.rest_controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.god.workorder.entity.Part;
import de.god.workorder.entity.WorkOrder;
import de.god.workorder.service.ValidationService;
import lombok.SneakyThrows;


/**
 * @author belbaghazaoui
 *
 */
@WebMvcTest(WorkOrderController.class)
class WorkOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ValidationService validationService;

    @SneakyThrows
    @Test
    void validation_OK() {
        //given
        WorkOrder workOrder = new WorkOrder(
                "ANALYSYS",
                "GOoD analysis department",
                LocalDate.parse("2022-02-01"),
                LocalDate.parse("2022-02-10"),
                Currency.getInstance("EUR"),
                123.4D,
                LocalDate.parse("2022-02-02"),
                LocalDate.parse("2022-02-03"),
                "Responsible Person #1",
                "Factory Name #1",
                "DN12345678",
                List.of(
                        new Part("Inv name #1", "name #1", 3),
                        new Part("Inv name #2", "name #2", 1)
                )
        );

        given(validationService.Validate(workOrder)).willReturn("VALID");

        String url = "/api/work-order/validation";

        //when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(workOrder)))
                .andExpect(status().isOk())
                .andReturn();

        String actualResult = mvcResult.getResponse().getContentAsString();
        String expectedResult = "VALID";

        //then
        assertThat(actualResult).isEqualTo(expectedResult);

    }

}