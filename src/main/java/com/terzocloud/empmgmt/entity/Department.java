package com.terzocloud.empmgmt.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="DEPARTMENT")  
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","employees"})
public class Department{
	  @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private Long id;
	  
	  private String name;
	  
	  @OneToMany(mappedBy="department")
	 // @JsonIgnoreProperties("department")
	  private Set<Employee> employees;
	  
	  public Department(String name) {
		  this.name = name;
	  }
	  

}