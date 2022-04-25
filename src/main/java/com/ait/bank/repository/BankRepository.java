package com.ait.bank.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ait.bank.model.Customers;

@Repository
public interface BankRepository extends JpaRepository<Customers,Long> 
{

}
