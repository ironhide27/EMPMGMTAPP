package com.terzocloud.empmgmt.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.terzocloud.empmgmt.controllers.EmployeeController;
import com.terzocloud.empmgmt.repositories.EmployeeRepository;
import com.terzocloud.empmgmt.service.EmployeeService;
/**
 * 
 * TODO
 *
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EmployeeController.class)
@ContextConfiguration(classes = {EmployeeService.class,EmployeeRepository.class})
@WebAppConfiguration
@ComponentScan
public class EmployeeControllerTest {
	@Autowired
	private MockMvc mockMvc;

	
	@MockBean
    private EmployeeRepository empRepo;
	
	


	
	/*
	 * @Test void whenValidUrlAndMethodAndContentType_thenReturns200() throws
	 * Exception { mockMvc.perform(get("/employee/all"))
	 * .andExpect(status().isOk()); }
	 */
	 



}