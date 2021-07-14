package com.terzocloud.empmgmt.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.terzocloud.empmgmt.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>,JpaSpecificationExecutor<Department> {
	List<Department> findAllByName(String name);
	
	@Transactional
	@Modifying
	@Query("update Department d set d.name = :departmentName where d.id = :deptId")
	void updateDeptName(@Param("departmentName") String departmentName,@Param("deptId") Long deptId);
	

}