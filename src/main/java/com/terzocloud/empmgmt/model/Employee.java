package com.terzocloud.empmgmt.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.terzocloud.empmgmt.listener.EmployeeListener;
import com.terzocloud.empmgmt.util.Constants;
import com.terzocloud.empmgmt.util.DesignationEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="EMPLOYEE")  
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(EmployeeListener.class)
public class Employee{
	private static final Logger log = LogManager.getLogger(Employee.class);
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String name;

	private BigDecimal salary;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="DEPARTMENT_ID")
	@JsonIgnoreProperties(value = {"employees","id"})
	private Department department;  

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MANAGER_ID")
	@JsonIgnoreProperties(value = {"department","id","manager","salary"})
	private Employee manager;  

	@Enumerated(EnumType.STRING)
	private DesignationEnum designation;

	public Employee(String name,BigDecimal salary,String designation) {
		this.name = name;
		this.salary = salary;
		this.designation = DesignationEnum.valueOf(designation);
	}

	@PrePersist
	public void logNewEmpAttempt() {
		log.info(Constants.LISTENER_MARKER,"Attempting to add new employee with username: " + name);
	}

	@PostPersist
	public void logNewEmpAdded() {
		log.info(Constants.LISTENER_MARKER,"Added employee '" + name + "' with ID: " + id);
	}


	@PreUpdate
	public void logEmpUpdateAttempt() {
		log.info(Constants.LISTENER_MARKER,"Attempting to update employee: " + name);
	}

	@PostUpdate
	public void logEmpUpdate() {
		log.info(Constants.LISTENER_MARKER,"Updated employee: " + name);


	}
}
