package de.god.workorder.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.god.workorder.entity.Department;
import de.god.workorder.entity.WorkOrderType;
import de.god.workorder.repository.DepartmentRepository;
import de.god.workorder.repository.WorkOrderTypeRepository;

/**
 * @author belbaghazaoui
 */
@Configuration
public class InitialData {

    @Autowired
    private WorkOrderTypeRepository orderTypeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * Save initial data
     */
    @Bean
    public void init(){
        
        if(!orderTypeRepository.existsById("ANALYSIS"))
            orderTypeRepository.save(new WorkOrderType("ANALYSIS"));
        if(!orderTypeRepository.existsById("REPAIR"))
            orderTypeRepository.save(new WorkOrderType("REPAIR"));
        if(!orderTypeRepository.existsById("REPLACEMENT"))
            orderTypeRepository.save(new WorkOrderType("REPLACEMENT"));

        if(!departmentRepository.existsById("GOoD analysis department"))
            departmentRepository.save(new Department("GOoD analysis department"));
        if(!departmentRepository.existsById("GOoD repair department"))
            departmentRepository.save(new Department("GOoD repair department"));
        if(!departmentRepository.existsById("GOoD replacement department"))
            departmentRepository.save(new Department("GOoD replacement department"));

    }
}
