package com.terzocloud.empmgmt.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.terzocloud.empmgmt.model.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>,JpaSpecificationExecutor<Employee> {

	List<Employee> findAllByName(String name);
	
	@Transactional
	@Modifying
	@Query(value= "update Employee e set e.department_id = :deptId where e.id = :empId" , nativeQuery = true)
	void mapEmpToDept(@Param("deptId") Long deptId,@Param("empId") Long empId);
	
	@Transactional
	@Modifying
	@Query(value= "update Employee e set e.manager_id = :managerEmpId where e.id = :empId" , nativeQuery = true)
	void mapManagerToEmp(@Param("managerEmpId") Long managerEmpId,@Param("empId") Long empId);

}