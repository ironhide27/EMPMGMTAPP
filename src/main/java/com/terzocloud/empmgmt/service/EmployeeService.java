package com.terzocloud.empmgmt.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.terzocloud.empmgmt.exception.ManagerAssignmentException;
import com.terzocloud.empmgmt.model.Employee;
import com.terzocloud.empmgmt.repositories.EmployeeRepository;
import com.terzocloud.empmgmt.specification.EmployeeSpecifications;
import com.terzocloud.empmgmt.util.Constants;

@Service
public class EmployeeService{
	@Autowired
	private EmployeeRepository empRepo;
	
	public Page<Employee> getAllEmps(Pageable pageable) {
		return empRepo.findAll(pageable);
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
	
	public Employee mapManagerToEmp(Long empId,Long managerEmpId)throws Exception {
		Employee employee = getEmployeeById(empId);
		if(employee==null) {
			throw new ManagerAssignmentException("Employee not found");	
		}
		Employee manager = getEmployeeById(managerEmpId);
		if(manager!=null) {
			if(Constants.MANAGER_DESIGNATION.equalsIgnoreCase(manager.getDesignation().toString())) {
				empRepo.mapManagerToEmp(managerEmpId,empId);
			}else {
				throw new ManagerAssignmentException("Only Employees with MANAGER Designation can be assigned");	
			}
		}else {
			throw new ManagerAssignmentException("Manager not found");	
		}
		
		return getEmployeeById(empId);
	}
	
	public Page<Employee> getAllEmpsGTSalary(BigDecimal salary,String criteria,Long deptId,String sortFieldName,String sortOrder,Pageable pageable){
		if(deptId!=null) {
			return empRepo.findAll(EmployeeSpecifications.deptSpec(deptId).and(EmployeeSpecifications.salarySpec(salary,criteria)),pageable);
		}else {
			return empRepo.findAll(EmployeeSpecifications.salarySpec(salary,criteria),pageable);
		}
	}
	
	/*
	 * public List<Employee> getAllEmpsGTSalaryForDept(BigDecimal salary,Long
	 * deptId){ return
	 * empRepo.findAll(EmployeeSpecifications.isFromDepartment(deptId).and(
	 * EmployeeSpecifications.isSalaryGreater(salary)),Sort.by(Direction.DESC,
	 * "salary")); }
	 */
}