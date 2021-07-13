package com.terzocloud.empmgmt.specification;


import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.terzocloud.empmgmt.entity.Employee;
import com.terzocloud.empmgmt.entity.Employee_;

public class EmployeeSpecifications{

	 public static Specification<Employee> isSalaryGreater(BigDecimal salary) {
		 return (root, query, criteriaBuilder) -> {
	            return criteriaBuilder.greaterThan(root.get(Employee_.salary), salary);
	        };
	 }
	 
	 public static Specification<Employee> isFromDepartment(Long deptId) {
		 return (root, query, criteriaBuilder) -> {
	            return criteriaBuilder.equal(root.get(Employee_.department).get("id"), deptId);
	        };
	 }

}