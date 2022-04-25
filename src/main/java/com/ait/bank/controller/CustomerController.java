package com.ait.bank.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ait.bank.model.Customers;
import com.ait.bank.repository.BankRepository;
import com.ait.bank.service.BankService;



@RestController
public class CustomerController {
	@Autowired
	BankRepository bankRepository;
	@Autowired
	BankService bankService;

	// Get list of all the customers in the DB
	@GetMapping("/customers")
	public ResponseEntity<List<Customers>> getCustomers() {
		List<Customers> customers = bankService.getCustomers();
		return new ResponseEntity<List<Customers>>(customers,HttpStatus.FOUND);
	}
	
	
	// Get customer based on Id
	@GetMapping("/customers/{id}")
	public ResponseEntity<Customers> getCustomerById(@PathVariable(value = "id") long customerId) {
		try
		{
			Customers customers = bankService.getCustomerById(customerId);
			return new ResponseEntity<Customers>(customers,HttpStatus.FOUND);
					//ResponseEntity.ok().body(customer.get());
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	// Adding new customer to the DB
	@PostMapping("/addCustomers")
	public ResponseEntity<Customers> createNewCustomer(@RequestBody Customers customers){
		try 
		{
			customers = bankService.createNewCustomer(customers);
			return new ResponseEntity<Customers>(customers,HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
	}

	// Update customer details in the DB
	
	  @PutMapping("/updatecustomers/{id}") 
	  public ResponseEntity<Customers> updateCustomer(@PathVariable(value="id")long customerId, @RequestBody Customers customers)
	  {
		  try 
			{
				Customers checkCustomers = bankService.getCustomerById(customerId);
				checkCustomers.setCustomerName(customers.getCustomerName());
				checkCustomers.setCustomerEmail(customers.getCustomerEmail());
				checkCustomers.setCustomerAddress(customers.getCustomerAddress());
				Customers updated_customer = bankService.updateCustomer(customers);
				return new ResponseEntity<Customers>(updated_customer,HttpStatus.OK);
			}
		  catch(Exception e)
		  {
			  return new ResponseEntity<>(HttpStatus.CONFLICT);
		  }

	  }
	 
	// Deleting customer details from the DB
	@DeleteMapping("/deleteCustomers/{id}")
	public ResponseEntity<Customers> deleteCustomer(@PathVariable(value = "id")long customerId)
	{
		Customers customer = null;
		try
		{
			customer = bankService.getCustomerById(customerId);
			bankService.deleteCustomer(customer);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customers>(customer,HttpStatus.OK);
		
	}

}
