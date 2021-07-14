package com.terzocloud.empmgmt.listener;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.terzocloud.empmgmt.model.Employee;

public class EmployeeListener{ 
	private static Logger log = LogManager.getLogger(Employee.class);

	@PrePersist
	private void beforeInsert(Employee employee) {
		log.info("About to add a user");
	}
	
	@PreUpdate
	@PreRemove
	private void beforeUpdate(Employee employee) {
		log.info("About to update/delete employee: " + employee.getId());
	}

	@PostPersist
	@PostUpdate
	@PostRemove
	private void afterAnyUpdate(Employee employee) {
		log.info("update complete for employee: " + employee.getId());
	}

	@PostLoad
	private void afterLoad(Employee employee) {
		log.info("employee loaded from database: " + employee.getId());
	}}