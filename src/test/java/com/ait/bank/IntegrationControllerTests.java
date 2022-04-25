package com.ait.bank;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.ait.bank.model.Customers;


@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class IntegrationControllerTests {
	
	@Test
	@Order(1)
	void getCustomersIntegrationTest() throws JSONException 
	{
		String expected = "[\r\n"
				+ "    {\r\n"
				+ "        \"id\": 1,\r\n"
				+ "        \"customerName\": \"shashi\",\r\n"
				+ "        \"customerEmail\": \"shashi@gmail.com\",\r\n"
				+ "        \"customerAddress\": \"Hyderabad\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "        \"id\": 2,\r\n"
				+ "        \"customerName\": \"ravi\",\r\n"
				+ "        \"customerEmail\": \"kumar@gmail.com\",\r\n"
				+ "        \"customerAddress\": \"Nevada\"\r\n"
				+ "    }\r\n"
				+ "]";
		TestRestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity<String>response = restTemplate.getForEntity("http://localhost:8080/customers", String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	@Test
	@Order(2)
	void getCustomersByIdIntegrationTest() throws JSONException 
	{
		String expected =  "{\r\n"
				+ "        \"id\": 1,\r\n"
				+ "        \"customerName\": \"shashi\",\r\n"
				+ "        \"customerEmail\": \"shashi@gmail.com\",\r\n"
				+ "        \"customerAddress\": \"Hyderabad\"\r\n"
				+ "    }";
		TestRestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity<String>response = restTemplate.getForEntity("http://localhost:8080/customers/1", String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	@Test
	@Order(3)
	void addCustomersIntegrationTest() throws JSONException 
	{
		Customers customer = new Customers(3,"balaji","balaji@gmail.com","Jersey");
		String expected =  "{\r\n"
				+ "        \"id\": 3,\r\n"
				+ "        \"customerName\": \"balaji\",\r\n"
				+ "        \"customerEmail\": \"balaji@gmail.com\",\r\n"
				+ "        \"customerAddress\": \"Jersey\"\r\n"
				+ "    }";
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Customers> request = new HttpEntity<Customers>(customer,headers);
		ResponseEntity<String>response = restTemplate.postForEntity("http://localhost:8080/addCustomers", request,String.class );
		System.out.println(response.getBody());
		
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	@Test
	@Order(4)
	void updateCustomersIntegrationTest() throws JSONException 
	{
		Customers customer = new Customers(3,"balaji","balaji@gmail.com","Jersey");
		String expected =  "{\r\n"
				+ "        \"id\": 3,\r\n"
				+ "        \"customerName\": \"balaji\",\r\n"
				+ "        \"customerEmail\": \"balaji@gmail.com\",\r\n"
				+ "        \"customerAddress\": \"Jersey\"\r\n"
				+ "    }";
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Customers> request = new HttpEntity<Customers>(customer,headers);
		
		ResponseEntity<String>response = restTemplate.exchange("http://localhost:8080/updatecustomers/3",HttpMethod.PUT, request,String.class );
		System.out.println(response.getBody());
		
		assertEquals(HttpStatus.OK,response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	@Test
	@Order(5)
	void deleteCustomersIntegrationTest() throws JSONException 
	{
		Customers customer = new Customers(3,"balaji","balaji@gmail.com","Jersey");
		String expected =  "{\r\n"
				+ "        \"id\": 3,\r\n"
				+ "        \"customerName\": \"balaji\",\r\n"
				+ "        \"customerEmail\": \"balaji@gmail.com\",\r\n"
				+ "        \"customerAddress\": \"Jersey\"\r\n"
				+ "    }";
		TestRestTemplate restTemplate = new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Customers> request = new HttpEntity<Customers>(customer,headers);
		
		ResponseEntity<String>response = restTemplate.exchange("http://localhost:8080/deleteCustomers/3",HttpMethod.DELETE, request,String.class );
		System.out.println(response.getBody());
		
		assertEquals(HttpStatus.OK,response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	

}
