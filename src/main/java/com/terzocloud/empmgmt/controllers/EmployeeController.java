package com.terzocloud.empmgmt.controllers;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.terzocloud.empmgmt.model.Employee;
import com.terzocloud.empmgmt.service.DepartmentService;
import com.terzocloud.empmgmt.service.EmployeeService;
import com.terzocloud.empmgmt.vo.CommonRequestVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;


@RestController
@RequestMapping("/employee")
public class EmployeeController{
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private DepartmentService deptService;
	
	@Operation(summary = "Add an employee",description = "{\"name\": \"Tony\", \"salary\" : 259999.00, \"designation\" : \"ARCHITECT\"}",
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {@Content(examples = 
			@ExampleObject(value = "{\"name\": \"Tony\", \"salary\" : 259999.00, \"designation\" : \"ARCHITECT\"}"))}))
	@PostMapping(path = "/add",consumes = {"application/json"},produces = {"application/json"})
	public Employee addEmployee(@RequestBody Employee employee) {
		return empService.addEmployee(employee.getName(),employee.getSalary(),employee.getDesignation().toString());
	}
	
	@Operation(summary = "Update employee details",description = "{\"name\": \"Tony\", \"salary\" : 259999.00, \"designation\" : \"ARCHITECT\"}")
	@PostMapping(path = "/update",consumes = {"application/json"},produces = {"application/json"})
	public Employee updateEmpDetails(@RequestBody Employee employee) {
		return empService.addEmployee(employee.getName(),employee.getSalary(),employee.getDesignation().toString());
	}
	
	@Operation(summary = "Map employee to a department",method = "POST",description = "Requires empId and deptId sent as request params",parameters = {@Parameter(name = "empId",required = true),@Parameter(name = "deptId",required = true)})
	@PostMapping(path = "/update-department")
	public ResponseEntity mapEmpToDept(@RequestBody CommonRequestVO requestVO) {
		
		try {
			deptService.getDepartmentById(requestVO.getDeptId());
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return  new ResponseEntity<Employee>(empService.mapEmpToDept(requestVO.getEmpId(),requestVO.getDeptId()),HttpStatus.OK);
	}
	
	@Operation(summary = "Map Manager to an Employee",method = "POST",description = "Requires empId and managerEmpId sent as request params")
	@PostMapping(path = "/update-manager")
	public ResponseEntity mapManagerToEmp(@RequestBody CommonRequestVO requestVO) {
		try {
			return new ResponseEntity<Employee>(empService.mapManagerToEmp(requestVO.getEmpId(),requestVO.getManagerEmpId()),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Retrieve all employee details",method = "GET")
	@GetMapping(path = "/all",produces = {"application/json"})
	public Page<Employee> getEmpById(PageRequest page) {
		return empService.getAllEmps(page);
	}
	
	@Operation(summary = "Retrieve employee details by emp id",method = "GET")
	@GetMapping(path = "/{empId}")
	public Employee getEmpById(@PathVariable Long empId) {
		return empService.getEmployeeById(empId);
	}
	
	
	@Operation(summary = "Retrieve employees belonging to a department by department id",method = "GET")
	@GetMapping(path = "/by-dept-id/{deptId}")
	public Set<Employee> getEmpsByDeptId(@PathVariable Long deptId) {
		return deptService.getEmployeesByDeptId(deptId);
	}
	
	@Operation(summary = "Retrieve employees based on criteria",method = "GET")
	@GetMapping(path = "/filter")
	public Page<Employee> getAllEmpsGTSalary(@RequestParam(required = true) BigDecimal salary,@RequestParam(required = true) String criteria,@RequestParam(required = false) Long deptId,@RequestParam(required = true) String sortFieldName,@RequestParam(required = true) String sortOrder) {
		return empService.getAllEmpsGTSalary(salary,criteria,deptId,sortFieldName,sortOrder);
	}
	
	/*@Operation(summary = "Retrieve employees with salary greater than input for a given department id. Sorted by salary desc",method = "POST")
	@GetMapping(path = "/getAllEmpsGTSalaryForDept")
	public List<Employee> getAllEmpsGTSalaryForDept(@RequestParam(name = "salary")BigDecimal salary,@RequestParam(name = "deptId")Long deptId) {
		return empService.getAllEmpsGTSalaryForDept(salary,deptId);
	}*/
}