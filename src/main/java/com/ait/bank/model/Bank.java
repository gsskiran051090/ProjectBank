package com.ait.bank.model;

import javax.persistence.*;

@Entity
@Table(name = "bank")
public class Bank 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
	@Column(name = "bank_name")
    private String bankName;
	@Column(name = "account_type")
    private String accountType;
	@Column(name = "year")
    private int Year;

    
    public Bank() 
    {

	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public String getAccountType() {
		return accountType;
	}


	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}


	public int getYear() {
		return Year;
	}


	public void setYear(int year) {
		Year = year;
	}


	public Bank(String bankName, String accountType, int year) {
		super();
		this.bankName = bankName;
		this.accountType = accountType;
		Year = year;
	} 

}

