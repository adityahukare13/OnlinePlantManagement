package com.onm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.onm.entity.Order;
import com.onm.entity.Plant;
import com.onm.repositories.OrderRepository;
import com.onm.services.OrderServices;

@SpringBootTest
public class OnmOrderTests {
	@Autowired
	OrderServices oservice;
	@Autowired 
	OrderRepository orderRepo;
	
	@Test
	public void bookOrderTest() {
		Order o = new Order(2);
		orderRepo.save(o);
		assertEquals(2, o.getOrderQuantity());
	}
	
	@Test
	void viewOrderTest() {
		Order o = new Order(4);
		orderRepo.save(o);
		assertNotNull(orderRepo.findAll());
	}
	
	@Test
	void viewAllOrder() {
		Order o = new Order(3);
		orderRepo.save(o);
		assertNotNull(orderRepo.findAll());
	}
	
	@Test
	 void updateOrderTest() {
		Order o = new Order(3);
		orderRepo.save(o);
		assertNotNull(orderRepo.findAll());
	}
	
	@Test
	 void deleteOrderTest() {
		Order o = new Order(3);
		orderRepo.save(o);
		assertNotNull(orderRepo.findAll());
	}
}
