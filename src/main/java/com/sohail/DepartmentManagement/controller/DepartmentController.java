package com.sohail.DepartmentManagement.controller;

import com.sohail.DepartmentManagement.dto.DepartmentDTO;
import com.sohail.DepartmentManagement.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService service;
    private final ModelMapper modelMapper;

    @GetMapping(path = "/getSecretMessage")
    public ResponseEntity<String> getSecretMessage(){
        return ResponseEntity.ok("This is a secret message");
    }

    @PostMapping(path = "/createDepartment")
    public ResponseEntity<DepartmentDTO> createDepartment(@Valid @RequestBody DepartmentDTO departmentDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(service.createDepartment(departmentDTO) , DepartmentDTO.class));
    }

    @GetMapping(path = "getAllDepartment")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartment(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        service.getAllDepartment()
                                .stream()
                                .map(department -> modelMapper.map(department , DepartmentDTO.class))
                                .toList()
                );
    }

    @GetMapping(path = "getDepartmentById/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(modelMapper.map(service.getDepartmentById(id) , DepartmentDTO.class));
    }

    @DeleteMapping(path = "deleteDepartmentWithId/{id}")
    public ResponseEntity<Boolean> deleteDepartmentWithId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.deleteDepartmentWithId(id));
    }

    @PutMapping(path = "updateDepartmentWithId/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartmentWithId(@PathVariable Long id , @RequestBody DepartmentDTO update){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body( modelMapper.map(service.updateDepartmentWithId(id , update) , DepartmentDTO.class));
    }

    @PatchMapping(path = "partialUpdateDepartmentWithId/{id}")
    public ResponseEntity<DepartmentDTO> partialUpdateDepartmentWithId(@PathVariable Long id , @RequestBody Map<String , Object> updates) throws IllegalAccessException {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(modelMapper.map(service.partialUpdateDepartmentWithId(id , updates) , DepartmentDTO.class));
    }

}
