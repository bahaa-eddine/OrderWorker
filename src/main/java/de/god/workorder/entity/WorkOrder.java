package de.god.workorder.entity;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author belbaghazaoui
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkOrder {
    @NotEmpty
    private String type;
    @NotEmpty
    private String department;
    @NotEmpty
    private LocalDate start_date;
    @NotEmpty
    private LocalDate end_date;
    @NotEmpty
    private Currency currency;
    @NotEmpty
    private Double cost;
    @NotEmpty
    private LocalDate analysis_date;
    @NotEmpty
    private LocalDate test_date;
    @NotEmpty
    private String responsible_person;
    @NotEmpty
    private String factory_name;
    @NotEmpty
    private String factory_order_number;

    private List<Part> parts;

}
