package de.god.workorder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author belbaghazaoui
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    @Id
    private String department_name;
}
