package com.terzocloud.empmgmt.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.terzocloud.empmgmt.entity.Department;
import com.terzocloud.empmgmt.entity.Employee;
import com.terzocloud.empmgmt.exception.DepartmentNotFoundException;
import com.terzocloud.empmgmt.service.DepartmentService;
import com.terzocloud.empmgmt.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/empMgmt")
public class EmpMgmtController{
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private DepartmentService deptService;
	
	@Operation(summary = "Add an employee",description = "{\"name\": \"Tony\", \"salary\" : 259999.00, \"designation\" : \"ARCHITECT\"}")
	@RequestMapping(path = "/addEmployee",method = RequestMethod.POST,consumes = {"application/json"},produces = {"application/json"})
	public Employee addEmployee(@RequestBody Employee employee) {
		return empService.addEmployee(employee.getName(),employee.getSalary(),employee.getDesignation().toString());
	}
	
	@RequestMapping(path = "/updateEmpDetails",method = RequestMethod.POST)
	public String updateEmpDetails() {
		return "ACH";
	}
	
	@RequestMapping(path = "/mapEmpToDept",method = RequestMethod.POST)
	public ResponseEntity mapEmpToDept(@RequestParam(name = "empId") Long empId,@RequestParam(name = "deptId") Long deptId) {
		
		try {
			deptService.getDepartmentById(deptId);
		}catch(Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return  new ResponseEntity<Employee>(empService.mapEmpToDept(empId,deptId),HttpStatus.OK);
	}
	
	@RequestMapping(path = "/mapManagerToEmp",method = RequestMethod.POST)
	public Employee mapManagerToEmp(@RequestParam(name = "empId") Long empId,@RequestParam(name = "managerEmpId") Long managerEmpId) {
		return empService.mapManagerToEmp(empId,managerEmpId);
	}
	
	@RequestMapping(path = "/addDepartment",method = RequestMethod.POST)
	public Department addDepartment(@RequestParam(name = "departmentName") String departmentName) {
		return deptService.addDepartment(departmentName);
	}
	
	@RequestMapping(path = "/updateDeptName",method = RequestMethod.POST)
	public ResponseEntity updateDeptName(@RequestParam(name = "departmentId") Long deptId,@RequestParam(name = "departmentName") String departmentName) {
		try {
			return new ResponseEntity<Department>(deptService.updateDepartment(departmentName,deptId),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	

	@RequestMapping(path = "/getAllEmps",method = RequestMethod.GET)
	public List<Employee> getEmpById() {
		return empService.getAllEmps();
	}
	
	@RequestMapping(path = "/getEmpById/{id}",method = RequestMethod.GET)
	public Employee getEmpById(@PathVariable Long id) {
		return empService.getEmployeeById(id);
	}
	
	@RequestMapping(path = "/getDeptById/{id}",method = RequestMethod.GET)
	public ResponseEntity getDeptById(@PathVariable Long id) {
		try {
			return new ResponseEntity<Department>(deptService.getDepartmentById(id),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(path = "/getEmpsByDeptId/{id}",method = RequestMethod.GET)
	public Set<Employee> getEmpsByDeptId(@PathVariable Long id) {
		return deptService.getEmployeesByDeptId(id);
	}
	
	@RequestMapping(path = "/getAllEmpsGTSalary/{salary}",method = RequestMethod.GET)
	public List<Employee> getAllEmpsGTSalary(@PathVariable BigDecimal salary) {
		return empService.getAllEmpsGTSalary(salary);
	}
	
	@RequestMapping(path = "/getAllEmpsGTSalaryForDept",method = RequestMethod.POST)
	public List<Employee> getAllEmpsGTSalaryForDept(@RequestParam(name = "salary")BigDecimal salary,@RequestParam(name = "deptId")Long deptId) {
		return empService.getAllEmpsGTSalaryForDept(salary,deptId);
	}
}