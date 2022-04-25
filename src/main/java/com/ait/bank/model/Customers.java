package com.ait.bank.model;

import javax.persistence.*;

@Entity
@Table(name="customers")
public class Customers 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "customer_email")
    private String customerEmail;
    @Column(name = "customer_address")
    private String customerAddress;

	
	public long getId() 
	{
		return id;
	}
	public void setId(long id) 
	{
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	
	public Customers() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Customers [id=" + id + ", customerName=" + customerName + ", customerEmail=" + customerEmail
				+ ", customerAddress=" + customerAddress + "]";
	}
	public Customers(long id, String customerName, String customerEmail, String customerAddress) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
	}
	
	
}