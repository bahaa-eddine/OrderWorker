package de.god.workorder.entity;

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
public class Part {

    private String inventory_number;

    private String name;

    private Integer count;
}
