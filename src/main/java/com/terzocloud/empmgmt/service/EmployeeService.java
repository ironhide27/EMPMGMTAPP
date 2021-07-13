package com.terzocloud.empmgmt.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.terzocloud.empmgmt.entity.Employee;
import com.terzocloud.empmgmt.repositories.EmployeeRepository;
import com.terzocloud.empmgmt.specification.EmployeeSpecifications;
import com.terzocloud.empmgmt.util.Constants;

@Service
public class EmployeeService{
	@Autowired
	private EmployeeRepository empRepo;
	
	public List<Employee> getAllEmps() {
		return empRepo.findAll();
	}
	
	
	public Employee getEmployeeById(Long empId) {
		Optional<Employee> optionalEmp = empRepo.findById(empId);
		return optionalEmp.isPresent()?optionalEmp.get():null;
	}
	
	public Employee addEmployee(String name,BigDecimal salary, String designation) {
		return empRepo.save(new Employee(name,salary,designation));
	}
	
	public Employee mapEmpToDept(Long empId,Long deptId) {
		empRepo.mapEmpToDept(deptId,empId);
		return getEmployeeById(empId);
	}
	
	public Employee mapManagerToEmp(Long empId,Long managerEmpId) {
		Employee manager = getEmployeeById(managerEmpId);
		if(Constants.MANAGER_DESIGNATION.equalsIgnoreCase(manager.getDesignation().toString())) {
			empRepo.mapManagerToEmp(managerEmpId,empId);
		}else {
			
		}
		return getEmployeeById(empId);
	}
	
	public List<Employee> getAllEmpsGTSalary(BigDecimal salary){
		return empRepo.findAll(EmployeeSpecifications.isSalaryGreater(salary),Sort.by("name"));
	}
	
	public List<Employee> getAllEmpsGTSalaryForDept(BigDecimal salary,Long deptId){
		return empRepo.findAll(EmployeeSpecifications.isFromDepartment(deptId).and(EmployeeSpecifications.isSalaryGreater(salary)),Sort.by(Direction.DESC,"salary"));
	}
}