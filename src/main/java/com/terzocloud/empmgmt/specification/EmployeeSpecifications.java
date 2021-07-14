package com.terzocloud.empmgmt.specification;


import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.terzocloud.empmgmt.model.Employee;

public class EmployeeSpecifications{

	 public static Specification<Employee> salarySpec(BigDecimal salary,String criteria) {
		 return (root, query, criteriaBuilder) -> {
			 if(criteria.equalsIgnoreCase("gt")) {
	            return criteriaBuilder.greaterThan(root.get("salary"), salary);
			 }else{
				 return criteriaBuilder.lessThan(root.get("salary"), salary);
			 }
	        };
	 }
	 
	 public static Specification<Employee> deptSpec(Long deptId) {
		 return (root, query, criteriaBuilder) -> {
	            return criteriaBuilder.equal(root.get("department").get("id"), deptId);
	        };
	 }

}