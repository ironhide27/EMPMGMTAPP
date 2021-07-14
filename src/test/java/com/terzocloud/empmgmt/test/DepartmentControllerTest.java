package com.terzocloud.empmgmt.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.terzocloud.empmgmt.controllers.DepartmentController;
import com.terzocloud.empmgmt.repositories.DepartmentRepository;
import com.terzocloud.empmgmt.repositories.EmployeeRepository;
import com.terzocloud.empmgmt.service.DepartmentService;
import com.terzocloud.empmgmt.service.EmployeeService;
/**
 * 
 * TODO
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DepartmentService deptService;

	@MockBean
	private EmployeeRepository empRepo;

	@MockBean
	private DepartmentRepository deptRepo;



	@Test
	public void whenValidUrlAndMethodAndContentType_thenReturns200() throws Exception { 
		mockMvc.perform(get("/department/100"))
		.andExpect(status().isOk()); 
	}
	
	@Test
	public void whenInvalidUrlAndMethodAndContentType_thenReturns404() throws Exception { 
		mockMvc.perform(get("/emp/100"))
		.andExpect(status().is4xxClientError()); 
	}


}