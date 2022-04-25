package com.ait.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ait.bank.controller.CustomerController;
import com.ait.bank.model.Customers;
import com.ait.bank.service.BankService;


@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes= {MackitoTests.class})
public class MackitoTests 
{
	@Mock
	BankService bankService;
	
	@InjectMocks
	CustomerController customerController;
	
	List<Customers> myCustomers;
	Customers customer;
	
	//Testing controller methods
	
	@Test
	@Order(1)
	public void test_getAllCustomers()
	{
		myCustomers = new ArrayList<Customers>();
		myCustomers.add(new Customers(1,"shashi","shashi@gmail.com","Hyderabad"));
		myCustomers.add(new Customers(2,"ramana","ramana@gmail.com","India"));
		//Mocking		
		when (bankService.getCustomers()).thenReturn(myCustomers);
		ResponseEntity<List<Customers>>response= customerController.getCustomers();
		//checking the result comparing the status code with controllers response
		assertEquals(HttpStatus.FOUND,response.getStatusCode());
		//checking the number of records
		assertEquals(2,response.getBody().size());
	}
	@Test
	@Order(2)
	public void test_getCustomersById()
	{
		customer = new Customers(1,"Shashi","shashi@gmail.com","Hyderabad");
		long customerId=1;
		//Mocking		
		when (bankService.getCustomerById(customerId)).thenReturn(customer);
		ResponseEntity<Customers>response= customerController.getCustomerById(customerId);
		//checking the result comparing the status code with controllers response
		assertEquals(HttpStatus.FOUND,response.getStatusCode());
		assertEquals(customerId,response.getBody().getId());
	}
	@Test
	@Order(3)
	public void test_addCustomers()
	{
		customer = new Customers(3,"maharshi","maharshi@gmail.com","Rayalaseema");
		
		//Mocking		
		when (bankService.createNewCustomer(customer)).thenReturn(customer);
		ResponseEntity<Customers>response= customerController.createNewCustomer(customer);
		//checking the result comparing the status code with controllers response
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
		assertEquals(customer,response.getBody());
	}
	@Test
	@Order(4)
	public void test_updateCustomers()
	{
		
		customer = new Customers(3,"banx","banx@gmail.com","jersey");
		long customerId=3;
		//Mocking		
		when (bankService.getCustomerById(customerId)).thenReturn(customer);
		when (bankService.updateCustomer(customer)).thenReturn(customer);
		ResponseEntity<Customers>response= customerController.updateCustomer(customerId,customer);
		//checking the result comparing the status code with controllers response
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(3,response.getBody().getId());
		assertEquals("banx",response.getBody().getCustomerName());
		assertEquals("banx@gmail.com",response.getBody().getCustomerEmail());
		assertEquals("jersey",response.getBody().getCustomerAddress());
	}
	@Test
	@Order(5)
	public void test_deleteCustomers()
	{
		
		customer = new Customers(3,"Richa","richa@gmail.com","texas");
		long id=3;
		//Mocking		
		when (bankService.getCustomerById(id)).thenReturn(customer);
		ResponseEntity<Customers>response= customerController.deleteCustomer(id);
		//checking the result comparing the status code with controllers response
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(3,response.getBody().getId());
		assertEquals("Richa",response.getBody().getCustomerName());
		assertEquals("richa@gmail.com",response.getBody().getCustomerEmail());
		assertEquals("texas",response.getBody().getCustomerAddress());
	}

}
