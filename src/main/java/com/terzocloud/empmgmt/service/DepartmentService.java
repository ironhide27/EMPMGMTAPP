package com.terzocloud.empmgmt.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terzocloud.empmgmt.model.Department;
import com.terzocloud.empmgmt.model.Employee;
import com.terzocloud.empmgmt.exception.DepartmentNotFoundException;
import com.terzocloud.empmgmt.repositories.DepartmentRepository;

@Service
public class DepartmentService{
	
	@Autowired
	private DepartmentRepository deptRepo;
	
	public Department getDepartmentById(Long departmentId)throws Exception {
		Optional<Department> optionalDept = deptRepo.findById(departmentId);
		Department department = optionalDept.isPresent()?optionalDept.get():null;
		if(department==null) {
			throw new DepartmentNotFoundException("Department Not Found");
		}
		return department;
	}
	
	public Set<Employee> getEmployeesByDeptId(Long departmentId) {
		Optional<Department> optionalDept = deptRepo.findById(departmentId);
		return optionalDept.isPresent()?optionalDept.get().getEmployees():null;
	}
	
	public Department addDepartment(String departmentName) {
		return deptRepo.save(new Department(departmentName));
	}
	
	public Department updateDepartment(String departmentName,long deptId) throws Exception{
		Department department = getDepartmentById(deptId);
		deptRepo.updateDeptName(departmentName, deptId);
		return getDepartmentById(deptId);
	}
}