package com.ait.bank;

import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ait.bank.controller.CustomerController;
import com.ait.bank.model.Customers;
import com.ait.bank.service.BankService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ComponentScan(basePackages="com.ait.bank")
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes= {MockMvcControllerTests.class})
public class MockMvcControllerTests {

	@Autowired
	MockMvc mockMvc;
	
	@Mock
	BankService bankService;
	
	@InjectMocks
	CustomerController customerController;
	
	List<Customers> myCustomers;
	Customers customers;
	
	@BeforeEach
	public void setUp()
	{
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}
	@Test
	@Order(1)
	public void test_getAllCustomers() throws Exception
	{
		myCustomers = new ArrayList<Customers>();
		myCustomers.add(new Customers(1,"shashi","shashi@gmail.com","Hyderabad"));
		myCustomers.add(new Customers(2,"sai","sai@gmail.com","India"));
		//Mocking		
		when (bankService.getCustomers()).thenReturn(myCustomers);
		this.mockMvc.perform(get("/customers")).andExpect(status().isFound()).andDo(print());
	}
	@Test
	@Order(2)
	public void test_getCustomersById() throws Exception
	{
		customers = new Customers(1,"Shashi","shashi@gmail.com","Hyderabad");
		long customerId=1;
		//Mocking		
		when (bankService.getCustomerById(customerId)).thenReturn(customers);
		this.mockMvc.perform(get("/customers/{id}",customerId))
		.andExpect(status().isFound())
		.andExpect(MockMvcResultMatchers.jsonPath(".id").value(1))
		.andExpect(MockMvcResultMatchers.jsonPath(".customerName").value("Shashi"))
		.andExpect(MockMvcResultMatchers.jsonPath(".customerEmail").value("shashi@gmail.com"))
		.andExpect(MockMvcResultMatchers.jsonPath(".customerAddress").value("Hyderabad"))
		.andDo(print());
	}
	@Test
	@Order(3)
	public void test_addCustomers() throws Exception
	{
		customers = new Customers(3,"Niomi","cambell@gmail.com","USA");
		//Mocking		
		when (bankService.createNewCustomer(customers)).thenReturn(customers);
		ObjectMapper mapper = new ObjectMapper();
		String jsonBody = mapper.writeValueAsString(customers);
		this.mockMvc.perform(post("/addCustomers")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andDo(print());
	}
	@Test
	@Order(4)
	public void test_updateCustomer() throws Exception
	{
		customers = new Customers(3,"ryan","garcia@gmail.com","Mexico");
		long customerId=3;
		//Mocking		
		when (bankService.getCustomerById(customerId)).thenReturn(customers);
		when (bankService.updateCustomer(customers)).thenReturn(customers);
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonBody = mapper.writeValueAsString(customers);
		this.mockMvc.perform(put("/updatecustomers/{id}",customerId)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}
	@Test
	@Order(5)
	public void test_deleteCustomers() throws Exception
	{
		
		customers = new Customers(1,"shashi","shashi@gmail.com","Hyderabad");
		long customerId=1;
		//Mocking		
		when (bankService.getCustomerById(customerId)).thenReturn(customers);
		this.mockMvc.perform(delete("/deleteCustomers/{id}",customerId))
				.andExpect(status().isOk());
	}
	
}
