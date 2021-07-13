package com.terzocloud.empmgmt.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.terzocloud.empmgmt.util.DESIGNATION;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="EMPLOYEE")  
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee{
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
	  private DESIGNATION designation;
	  
	  public Employee(String name,BigDecimal salary,String designation) {
		  this.name = name;
		  this.salary = salary;
		  this.designation = DESIGNATION.valueOf(designation);
	  }

}

