package de.god.workorder.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.god.workorder.entity.Part;
import de.god.workorder.entity.WorkOrder;
import de.god.workorder.exception.WorkOrderValidationException;
import de.god.workorder.repository.DepartmentRepository;
import de.god.workorder.repository.WorkOrderTypeRepository;


/**
 * @author belbaghazaoui
 *
 */
@ExtendWith(MockitoExtension.class)
class ValidationServiceTest {

    @InjectMocks
    private ValidationService underTest;

    @Mock
    private ValidationHistoryService validationHistoryService;

    @Mock
    private WorkOrderTypeRepository workOrderTypeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Test
    void validate_OK_ANALYSYS() {
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

        given(workOrderTypeRepository.existsById(anyString())).willReturn(true);
        given(departmentRepository.existsById(anyString())).willReturn(true);

        //when
        String result = underTest.Validate(workOrder);

        //then
        verify(workOrderTypeRepository).existsById(workOrder.getType());
        verify(departmentRepository).existsById(workOrder.getDepartment());
        assertThat(Currency.getAvailableCurrencies().contains(workOrder.getCurrency())).isTrue();
        assertThat(result).isEqualTo("VALID");
    }

    @Test
    void validate_OK_REPAIR() {
        //given
        WorkOrder workOrder = new WorkOrder(
                "REPAIR",
                "GOoD department",
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

        given(workOrderTypeRepository.existsById(anyString())).willReturn(true);
        given(departmentRepository.existsById(anyString())).willReturn(true);

        //when
        String result = underTest.Validate(workOrder);

        //then
        verify(workOrderTypeRepository).existsById(workOrder.getType());
        verify(departmentRepository).existsById(workOrder.getDepartment());
        assertThat(Currency.getAvailableCurrencies().contains(workOrder.getCurrency())).isTrue();
        assertThat(result).isEqualTo("VALID");
    }

    @Test
    void validate_OK_REPLACEMENT() {
        //given
        WorkOrder workOrder = new WorkOrder(
                "REPLACEMENT",
                "GOoD department",
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

        given(workOrderTypeRepository.existsById(anyString())).willReturn(true);
        given(departmentRepository.existsById(anyString())).willReturn(true);

        //when
        String result = underTest.Validate(workOrder);

        //then
        verify(workOrderTypeRepository).existsById(workOrder.getType());
        verify(departmentRepository).existsById(workOrder.getDepartment());
        assertThat(Currency.getAvailableCurrencies().contains(workOrder.getCurrency())).isTrue();
        assertThat(result).isEqualTo("VALID");
    }

    @Test
    void validate_invalid_type() {
        //given
        WorkOrder workOrder = new WorkOrder(
                "any",
                "GOoD department",
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

        given(workOrderTypeRepository.existsById(anyString())).willReturn(false);
        given(departmentRepository.existsById(anyString())).willReturn(true);

        //when

        //then

        assertThatThrownBy(() -> underTest.Validate(workOrder))
                .isInstanceOf(WorkOrderValidationException.class)
                .hasMessageContaining("Work order type is invalid.");
    }

    @Test
    void validate_invalid_department() {
        //given
        WorkOrder workOrder = new WorkOrder(
                "any",
                "any",
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

        given(workOrderTypeRepository.existsById(anyString())).willReturn(true);
        given(departmentRepository.existsById(anyString())).willReturn(false);

        //when

        //then

        assertThatThrownBy(() -> underTest.Validate(workOrder))
                .isInstanceOf(WorkOrderValidationException.class)
                .hasMessageContaining("Department is invalied.");
    }

    /**
     * and so on for other section of validate() method...
     */

}