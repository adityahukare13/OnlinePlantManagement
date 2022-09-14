package com.onm;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.onm.entity.Plant;
import com.onm.repositories.PlantRepository;
import com.onm.services.PlantServices;

@SpringBootTest
public class OnmPlantTests {
	@Autowired
	PlantRepository pRepo;
	@Autowired
	PlantServices pservices;
	@Test
	void addTest() {
		Plant p= new Plant("Lotus","flowerPlant",250);
		pRepo.save(p);
		assertNotNull(pRepo.findAll());
	
	}
	
	
	@Test
	 void updateTest() {
		Plant p2= new Plant("Rose","flowerPlant",250);
		pRepo.save(p2);
		assertNotNull(pRepo.findAll());
	}
	
	
	@Test
	 void viewTest() {
		Plant p3= new Plant();
		assertNotNull(pRepo.findAll());
	}

	@Test
	 void deleteTest() {
		Plant p4= new Plant();
		pRepo.delete(p4);
		assertNotNull(pRepo.findAll());

	}
}
