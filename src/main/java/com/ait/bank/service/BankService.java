package com.ait.bank.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.ait.bank.model.Customers;
import com.ait.bank.repository.BankRepository;

@Service
public class BankService {
	@Autowired
	private BankRepository bankRepository;
	
	public List<Customers> getCustomers() {
		List<Customers> customers= bankRepository.findAll();
		return customers;
	}
	public Customers getCustomerById(@PathVariable long customerId){
		List<Customers> customers = bankRepository.findAll();
		Customers customer = null;
		for(Customers d:customers)
		{
			if(d.getId()==customerId)
				customer=d;
		}
		return customer;
	
	}
	public Customers createNewCustomer(@RequestBody Customers customer)
	{
		customer.setId(getMaxId());
		bankRepository.save(customer);
		return customer;
	}
	
	public Customers updateCustomer(Customers customer)
	{
		bankRepository.save(customer);
	  		return customer;
	}
	public void deleteCustomer(Customers customer)
	{
		bankRepository.delete(customer);
	}
	public long getMaxId() 
	{
		// TODO Auto-generated method stub
		return bankRepository.findAll().size()+1;
	}

}
