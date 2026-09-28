package com.sohail.DepartmentManagement.service.impl;

import com.sohail.DepartmentManagement.dto.DepartmentDTO;
import com.sohail.DepartmentManagement.entity.Department;
import com.sohail.DepartmentManagement.exception.DuplicateResourceFoundException;
import com.sohail.DepartmentManagement.exception.ResourceNotFoundException;
import com.sohail.DepartmentManagement.repository.DepartmentRepository;
import com.sohail.DepartmentManagement.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public Department createDepartment(DepartmentDTO departmentDTO) {
        if(repository.existsByTitle(departmentDTO.getTitle())){
            throw  new DuplicateResourceFoundException("DUPLICATE RESOURCE FOUND EXCEPTION" , "DUPLICATE DEPARTMENT FOUND WITH TITLE : " + departmentDTO.getTitle());
        }

        Department departmentToBeSaved = modelMapper.map(departmentDTO , Department.class);
        Department savedDepartment = repository.save(departmentToBeSaved);
        return savedDepartment;
    }

    @Override
    public List<Department> getAllDepartment() {
        return repository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) {
        Optional<Department> department = repository.findById(id);

        if(department.isEmpty()){
            throw  new ResourceNotFoundException("RESOURCE NOT FOUND EXCEPTION" , "DEPARTMENT NOT FOUND WITH ID : " + id);
        }
        else{
            return department.get();
        }
    }

    @Override
    public Boolean deleteDepartmentWithId(Long id) {
        Department departmentToBeDeleted = repository.findById(id).orElse(null);

        if(departmentToBeDeleted == null){
            throw  new ResourceNotFoundException("RESOURCE NOT FOUND EXCEPTION" , "DEPARTMENT NOT FOUND WITH ID : " + id);
        }

        repository.delete(departmentToBeDeleted);
        return true;
    }

    @Override
    public Department updateDepartmentWithId(Long id , DepartmentDTO update) {
        Department departmentToBeUpdated = repository.findById(id).orElse(null);

        if(departmentToBeUpdated == null){
            throw  new ResourceNotFoundException("RESOURCE NOT FOUND EXCEPTION" , "DEPARTMENT NOT FOUND WITH ID : " + id);
        }
        else if(repository.existsByTitle(update.getTitle()) && !departmentToBeUpdated.getTitle().equals(update.getTitle())){
            throw  new DuplicateResourceFoundException("DUPLICATE RESOURCE FOUND EXCEPTION" , "DUPLICATE DEPARTMENT FOUND WITH TITLE : " + update.getTitle());
        }

        departmentToBeUpdated = modelMapper.map(update , Department.class);
        departmentToBeUpdated.setId(id);
        departmentToBeUpdated.setCreatedAt(LocalDateTime.now());

        return repository.save(departmentToBeUpdated);
    }

    @Override
    public Department partialUpdateDepartmentWithId(Long id, Map<String, Object> updates) throws IllegalAccessException {
        Department departmentToBePartlyUpdated = repository.findById(id).orElse(null);

        if(departmentToBePartlyUpdated == null){
            throw  new ResourceNotFoundException("RESOURCE NOT FOUND EXCEPTION" , "DEPARTMENT NOT FOUND WITH ID : " + id);
        }

        Class<?> c = departmentToBePartlyUpdated.getClass();
        Field fields[] = c.getDeclaredFields();

        for(Field field : fields){
            if(updates.containsKey(field.getName())){
                field.setAccessible(true);
                field.set(departmentToBePartlyUpdated , updates.get(field.getName()));
            }
        }
        return repository.save(departmentToBePartlyUpdated);
    }
}
