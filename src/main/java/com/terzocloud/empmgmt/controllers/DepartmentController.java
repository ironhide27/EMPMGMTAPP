package com.terzocloud.empmgmt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.terzocloud.empmgmt.model.Department;
import com.terzocloud.empmgmt.service.DepartmentService;
import com.terzocloud.empmgmt.vo.CommonRequestVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;


@RestController
@RequestMapping("/department")
public class DepartmentController{
	
	@Autowired
	private DepartmentService deptService;
	
	@Operation(summary = "Add a new department",method = "POST",parameters = {@Parameter(name = "departmentName",required = true)})
	@PostMapping(path = "/add")
	public Department addDepartment(@RequestBody CommonRequestVO requestVO) {
		return deptService.addDepartment(requestVO.getDepartmentName());
	}
	
	@Operation(summary = "Update department name",method = "POST",parameters = {@Parameter(name = "departmentId",required = true),@Parameter(name = "departmentName",required = true)})
	@PostMapping(path = "/update-name")
	public ResponseEntity updateDeptName(@RequestBody CommonRequestVO requestVO) {
		try {
			return new ResponseEntity<Department>(deptService.updateDepartment(requestVO.getDepartmentName(),requestVO.getDeptId()),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Retrieve department details by department id",method = "GET")
	@GetMapping(path = "/{deptId}")
	public ResponseEntity getDeptById(@PathVariable Long deptId) {
		try {
			return new ResponseEntity<Department>(deptService.getDepartmentById(deptId),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}