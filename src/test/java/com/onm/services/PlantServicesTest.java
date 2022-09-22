package com.onm.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.onm.entity.Customer;
import com.onm.entity.Plant;
import com.onm.expections.NotAdminException;
import com.onm.expections.ResourceNotFoundException;
import com.onm.repositories.CustomerRepository;
import com.onm.repositories.PlantRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PlantServices.class})
@ExtendWith(SpringExtension.class)
class PlantServicesTest {
    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private PlantRepository plantRepository;

    @Autowired
    private PlantServices plantServices;

    /**
     * Method under test: {@link PlantServices#addPlant(Plant, int)}
     */
    @Test
    void testAddPlant() {
        Customer customer = new Customer();
        customer.setCustomerAddress("42 Main St");
        customer.setCustomerEmail("jane.doe@example.org");
        customer.setCustomerId(123);
        customer.setCustomerMobile(1L);
        customer.setCustomerName("Customer Name");
        customer.setCustomerType("Customer Type");
        customer.setLoginStatus(true);
        customer.setPassword("iloveyou");
        customer.setUserName("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById((Integer) any())).thenReturn(ofResult);

        Plant plant = new Plant();
        plant.setPlantCost(10.0d);
        plant.setPlantDesccription("Plant Desccription");
        plant.setPlantId(123);
        plant.setPlantName("Plant Name");

        Plant plant1 = new Plant();
        plant1.setPlantCost(10.0d);
        plant1.setPlantDesccription("Plant Desccription");
        plant1.setPlantId(123);
        plant1.setPlantName("Plant Name");
        when(plantRepository.findByplantName((String) any())).thenReturn(plant);
        when(plantRepository.save((Plant) any())).thenReturn(plant1);

        Plant plant2 = new Plant();
        plant2.setPlantCost(10.0d);
        plant2.setPlantDesccription("Plant Desccription");
        plant2.setPlantId(123);
        plant2.setPlantName("Plant Name");
        assertFalse(plantServices.addPlant(plant2, 123));
        verify(customerRepository).findById((Integer) any());
        verify(plantRepository).findByplantName((String) any());
    }

    /**
     * Method under test: {@link PlantServices#addPlant(Plant, int)}
     */
    @Test
    void testAddPlant2() {
        Customer customer = new Customer();
        customer.setCustomerAddress("42 Main St");
        customer.setCustomerEmail("jane.doe@example.org");
        customer.setCustomerId(123);
        customer.setCustomerMobile(1L);
        customer.setCustomerName("Customer Name");
        customer.setCustomerType("Customer Type");
        customer.setLoginStatus(true);
        customer.setPassword("iloveyou");
        customer.setUserName("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById((Integer) any())).thenReturn(ofResult);
        when(plantRepository.findByplantName((String) any())).thenThrow(new NotAdminException("An error occurred"));
        when(plantRepository.save((Plant) any())).thenThrow(new NotAdminException("An error occurred"));

        Plant plant = new Plant();
        plant.setPlantCost(10.0d);
        plant.setPlantDesccription("Plant Desccription");
        plant.setPlantId(123);
        plant.setPlantName("Plant Name");
        assertThrows(NotAdminException.class, () -> plantServices.addPlant(plant, 123));
        verify(plantRepository).findByplantName((String) any());
    }

    /**
     * Method under test: {@link PlantServices#addPlant(Plant, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddPlant3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at com.onm.services.PlantServices.addPlant(PlantServices.java:27)
        //   In order to prevent addPlant(Plant, int)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   addPlant(Plant, int).
        //   See https://diff.blue/R013 to resolve this issue.

        when(customerRepository.findById((Integer) any())).thenReturn(Optional.empty());
        Customer customer = mock(Customer.class);
        when(customer.getCustomerType()).thenReturn("Customer Type");
        doNothing().when(customer).setCustomerAddress((String) any());
        doNothing().when(customer).setCustomerEmail((String) any());
        doNothing().when(customer).setCustomerId(anyInt());
        doNothing().when(customer).setCustomerMobile((Long) any());
        doNothing().when(customer).setCustomerName((String) any());
        doNothing().when(customer).setCustomerType((String) any());
        doNothing().when(customer).setLoginStatus(anyBoolean());
        doNothing().when(customer).setPassword((String) any());
        doNothing().when(customer).setUserName((String) any());
        customer.setCustomerAddress("42 Main St");
        customer.setCustomerEmail("jane.doe@example.org");
        customer.setCustomerId(123);
        customer.setCustomerMobile(1L);
        customer.setCustomerName("Customer Name");
        customer.setCustomerType("Customer Type");
        customer.setLoginStatus(true);
        customer.setPassword("iloveyou");
        customer.setUserName("janedoe");

        Plant plant = new Plant();
        plant.setPlantCost(10.0d);
        plant.setPlantDesccription("Plant Desccription");
        plant.setPlantId(123);
        plant.setPlantName("Plant Name");

        Plant plant1 = new Plant();
        plant1.setPlantCost(10.0d);
        plant1.setPlantDesccription("Plant Desccription");
        plant1.setPlantId(123);
        plant1.setPlantName("Plant Name");
        when(plantRepository.findByplantName((String) any())).thenReturn(plant);
        when(plantRepository.save((Plant) any())).thenReturn(plant1);

        Plant plant2 = new Plant();
        plant2.setPlantCost(10.0d);
        plant2.setPlantDesccription("Plant Desccription");
        plant2.setPlantId(123);
        plant2.setPlantName("Plant Name");
        plantServices.addPlant(plant2, 123);
    }

    /**
     * Method under test: {@link PlantServices#deletePlant(int, int)}
     */
    @Test
    void testDeletePlant() {
        Customer customer = new Customer();
        customer.setCustomerAddress("42 Main St");
        customer.setCustomerEmail("jane.doe@example.org");
        customer.setCustomerId(123);
        customer.setCustomerMobile(1L);
        customer.setCustomerName("Customer Name");
        customer.setCustomerType("Customer Type");
        customer.setLoginStatus(true);
        customer.setPassword("iloveyou");
        customer.setUserName("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById((Integer) any())).thenReturn(ofResult);

        Plant plant = new Plant();
        plant.setPlantCost(10.0d);
        plant.setPlantDesccription("Plant Desccription");
        plant.setPlantId(123);
        plant.setPlantName("Plant Name");
        Optional<Plant> ofResult1 = Optional.of(plant);
        doNothing().when(plantRepository).deleteById((Integer) any());
        when(plantRepository.findById((Integer) any())).thenReturn(ofResult1);
        plantServices.deletePlant(123, 123);
        verify(customerRepository).findById((Integer) any());
        verify(plantRepository).findById((Integer) any());
        verify(plantRepository).deleteById((Integer) any());
        assertEquals("User not found", plantServices.msgUNF);
        assertEquals("Access Denied", plantServices.msgAD);
    }

    /**
     * Method under test: {@link PlantServices#deletePlant(int, int)}
     */
    @Test
    void testDeletePlant2() {
        Customer customer = new Customer();
        customer.setCustomerAddress("42 Main St");
        customer.setCustomerEmail("jane.doe@example.org");
        customer.setCustomerId(123);
        customer.setCustomerMobile(1L);
        customer.setCustomerName("Customer Name");
        customer.setCustomerType("Customer Type");
        customer.setLoginStatus(true);
        customer.setPassword("iloveyou");
        customer.setUserName("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById((Integer) any())).thenReturn(ofResult);

        Plant plant = new Plant();
        plant.setPlantCost(10.0d);
        plant.setPlantDesccription("Plant Desccription");
        plant.setPlantId(123);
        plant.setPlantName("Plant Name");
        Optional<Plant> ofResult1 = Optional.of(plant);
        doThrow(new ResourceNotFoundException("An error occurred")).when(plantRepository).deleteById((Integer) any());
        when(plantRepository.findById((Integer) any())).thenReturn(ofResult1);
        assertThrows(ResourceNotFoundException.class, () -> plantServices.deletePlant(123, 123));
        verify(customerRepository).findById((Integer) any());
        verify(plantRepository).findById((Integer) any());
        verify(plantRepository).deleteById((Integer) any());
    }

    /**
     * Method under test: {@link PlantServices#deletePlant(int, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeletePlant3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at com.onm.services.PlantServices.deletePlant(PlantServices.java:43)
        //   In order to prevent deletePlant(int, int)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   deletePlant(int, int).
        //   See https://diff.blue/R013 to resolve this issue.

        when(customerRepository.findById((Integer) any())).thenReturn(Optional.empty());
        Customer customer = mock(Customer.class);
        when(customer.getCustomerType()).thenReturn("Customer Type");
        doNothing().when(customer).setCustomerAddress((String) any());
        doNothing().when(customer).setCustomerEmail((String) any());
        doNothing().when(customer).setCustomerId(anyInt());
        doNothing().when(customer).setCustomerMobile((Long) any());
        doNothing().when(customer).setCustomerName((String) any());
        doNothing().when(customer).setCustomerType((String) any());
        doNothing().when(customer).setLoginStatus(anyBoolean());
        doNothing().when(customer).setPassword((String) any());
        doNothing().when(customer).setUserName((String) any());
        customer.setCustomerAddress("42 Main St");
        customer.setCustomerEmail("jane.doe@example.org");
        customer.setCustomerId(123);
        customer.setCustomerMobile(1L);
        customer.setCustomerName("Customer Name");
        customer.setCustomerType("Customer Type");
        customer.setLoginStatus(true);
        customer.setPassword("iloveyou");
        customer.setUserName("janedoe");

        Plant plant = new Plant();
        plant.setPlantCost(10.0d);
        plant.setPlantDesccription("Plant Desccription");
        plant.setPlantId(123);
        plant.setPlantName("Plant Name");
        Optional<Plant> ofResult = Optional.of(plant);
        doNothing().when(plantRepository).deleteById((Integer) any());
        when(plantRepository.findById((Integer) any())).thenReturn(ofResult);
        plantServices.deletePlant(123, 123);
    }

    /**
     * Method under test: {@link PlantServices#deletePlant(int, int)}
     */
    @Test
    void testDeletePlant4() {
        Customer customer = mock(Customer.class);
        when(customer.getCustomerType()).thenReturn("Customer Type");
        doNothing().when(customer).setCustomerAddress((String) any());
        doNothing().when(customer).setCustomerEmail((String) any());
        doNothing().when(customer).setCustomerId(anyInt());
        doNothing().when(customer).setCustomerMobile((Long) any());
        doNothing().when(customer).setCustomerName((String) any());
        doNothing().when(customer).setCustomerType((String) any());
        doNothing().when(customer).setLoginStatus(anyBoolean());
        doNothing().when(customer).setPassword((String) any());
        doNothing().when(customer).setUserName((String) any());
        customer.setCustomerAddress("42 Main St");
        customer.setCustomerEmail("jane.doe@example.org");
        customer.setCustomerId(123);
        customer.setCustomerMobile(1L);
        customer.setCustomerName("Customer Name");
        customer.setCustomerType("Customer Type");
        customer.setLoginStatus(true);
        customer.setPassword("iloveyou");
        customer.setUserName("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById((Integer) any())).thenReturn(ofResult);
        doNothing().when(plantRepository).deleteById((Integer) any());
        when(plantRepository.findById((Integer) any())).thenReturn(Optional.empty());
        plantServices.deletePlant(123, 123);
        verify(customerRepository).findById((Integer) any());
        verify(customer).getCustomerType();
        verify(customer).setCustomerAddress((String) any());
        verify(customer).setCustomerEmail((String) any());
        verify(customer).setCustomerId(anyInt());
        verify(customer).setCustomerMobile((Long) any());
        verify(customer).setCustomerName((String) any());
        verify(customer).setCustomerType((String) any());
        verify(customer).setLoginStatus(anyBoolean());
        verify(customer).setPassword((String) any());
        verify(customer).setUserName((String) any());
        verify(plantRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link PlantServices#updatePlant(Plant, int)}
     */
    @Test
    void testUpdatePlant() {
        Customer customer = new Customer();
        customer.setCustomerAddress("42 Main St");
        customer.setCustomerEmail("jane.doe@example.org");
        customer.setCustomerId(123);
        customer.setCustomerMobile(1L);
        customer.setCustomerName("Customer Name");
        customer.setCustomerType("Customer Type");
        customer.setLoginStatus(true);
        customer.setPassword("iloveyou");
        customer.setUserName("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById((Integer) any())).thenReturn(ofResult);

        Plant plant = new Plant();
        plant.setPlantCost(10.0d);
        plant.setPlantDesccription("Plant Desccription");
        plant.setPlantId(123);
        plant.setPlantName("Plant Name");
        Optional<Plant> ofResult1 = Optional.of(plant);

        Plant plant1 = new Plant();
        plant1.setPlantCost(10.0d);
        plant1.setPlantDesccription("Plant Desccription");
        plant1.setPlantId(123);
        plant1.setPlantName("Plant Name");
        when(plantRepository.save((Plant) any())).thenReturn(plant1);
        when(plantRepository.findById((Integer) any())).thenReturn(ofResult1);

        Plant plant2 = new Plant();
        plant2.setPlantCost(10.0d);
        plant2.setPlantDesccription("Plant Desccription");
        plant2.setPlantId(123);
        plant2.setPlantName("Plant Name");
        Plant actualUpdatePlantResult = plantServices.updatePlant(plant2, 123);
        assertSame(plant, actualUpdatePlantResult);
        assertEquals(10.0d, actualUpdatePlantResult.getPlantCost());
        assertEquals("Plant Name", actualUpdatePlantResult.getPlantName());
        assertEquals("Plant Desccription", actualUpdatePlantResult.getPlantDesccription());
        verify(customerRepository).findById((Integer) any());
        verify(plantRepository).save((Plant) any());
        verify(plantRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link PlantServices#updatePlant(Plant, int)}
     */
    @Test
    void testUpdatePlant2() {
        Customer customer = new Customer();
        customer.setCustomerAddress("42 Main St");
        customer.setCustomerEmail("jane.doe@example.org");
        customer.setCustomerId(123);
        customer.setCustomerMobile(1L);
        customer.setCustomerName("Customer Name");
        customer.setCustomerType("Customer Type");
        customer.setLoginStatus(true);
        customer.setPassword("iloveyou");
        customer.setUserName("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById((Integer) any())).thenReturn(ofResult);

        Plant plant = new Plant();
        plant.setPlantCost(10.0d);
        plant.setPlantDesccription("Plant Desccription");
        plant.setPlantId(123);
        plant.setPlantName("Plant Name");
        Optional<Plant> ofResult1 = Optional.of(plant);
        when(plantRepository.save((Plant) any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        when(plantRepository.findById((Integer) any())).thenReturn(ofResult1);

        Plant plant1 = new Plant();
        plant1.setPlantCost(10.0d);
        plant1.setPlantDesccription("Plant Desccription");
        plant1.setPlantId(123);
        plant1.setPlantName("Plant Name");
        assertThrows(ResourceNotFoundException.class, () -> plantServices.updatePlant(plant1, 123));
        verify(customerRepository).findById((Integer) any());
        verify(plantRepository).save((Plant) any());
        verify(plantRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link PlantServices#updatePlant(Plant, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdatePlant3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at com.onm.services.PlantServices.updatePlant(PlantServices.java:54)
        //   In order to prevent updatePlant(Plant, int)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   updatePlant(Plant, int).
        //   See https://diff.blue/R013 to resolve this issue.

        when(customerRepository.findById((Integer) any())).thenReturn(Optional.empty());
        Customer customer = mock(Customer.class);
        when(customer.getCustomerType()).thenReturn("Customer Type");
        doNothing().when(customer).setCustomerAddress((String) any());
        doNothing().when(customer).setCustomerEmail((String) any());
        doNothing().when(customer).setCustomerId(anyInt());
        doNothing().when(customer).setCustomerMobile((Long) any());
        doNothing().when(customer).setCustomerName((String) any());
        doNothing().when(customer).setCustomerType((String) any());
        doNothing().when(customer).setLoginStatus(anyBoolean());
        doNothing().when(customer).setPassword((String) any());
        doNothing().when(customer).setUserName((String) any());
        customer.setCustomerAddress("42 Main St");
        customer.setCustomerEmail("jane.doe@example.org");
        customer.setCustomerId(123);
        customer.setCustomerMobile(1L);
        customer.setCustomerName("Customer Name");
        customer.setCustomerType("Customer Type");
        customer.setLoginStatus(true);
        customer.setPassword("iloveyou");
        customer.setUserName("janedoe");

        Plant plant = new Plant();
        plant.setPlantCost(10.0d);
        plant.setPlantDesccription("Plant Desccription");
        plant.setPlantId(123);
        plant.setPlantName("Plant Name");
        Optional<Plant> ofResult = Optional.of(plant);

        Plant plant1 = new Plant();
        plant1.setPlantCost(10.0d);
        plant1.setPlantDesccription("Plant Desccription");
        plant1.setPlantId(123);
        plant1.setPlantName("Plant Name");
        when(plantRepository.save((Plant) any())).thenReturn(plant1);
        when(plantRepository.findById((Integer) any())).thenReturn(ofResult);

        Plant plant2 = new Plant();
        plant2.setPlantCost(10.0d);
        plant2.setPlantDesccription("Plant Desccription");
        plant2.setPlantId(123);
        plant2.setPlantName("Plant Name");
        plantServices.updatePlant(plant2, 123);
    }

    /**
     * Method under test: {@link PlantServices#updatePlant(Plant, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdatePlant4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at com.onm.services.PlantServices.updatePlant(PlantServices.java:55)
        //   In order to prevent updatePlant(Plant, int)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   updatePlant(Plant, int).
        //   See https://diff.blue/R013 to resolve this issue.

        Customer customer = mock(Customer.class);
        when(customer.getCustomerType()).thenReturn("Customer Type");
        doNothing().when(customer).setCustomerAddress((String) any());
        doNothing().when(customer).setCustomerEmail((String) any());
        doNothing().when(customer).setCustomerId(anyInt());
        doNothing().when(customer).setCustomerMobile((Long) any());
        doNothing().when(customer).setCustomerName((String) any());
        doNothing().when(customer).setCustomerType((String) any());
        doNothing().when(customer).setLoginStatus(anyBoolean());
        doNothing().when(customer).setPassword((String) any());
        doNothing().when(customer).setUserName((String) any());
        customer.setCustomerAddress("42 Main St");
        customer.setCustomerEmail("jane.doe@example.org");
        customer.setCustomerId(123);
        customer.setCustomerMobile(1L);
        customer.setCustomerName("Customer Name");
        customer.setCustomerType("Customer Type");
        customer.setLoginStatus(true);
        customer.setPassword("iloveyou");
        customer.setUserName("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById((Integer) any())).thenReturn(ofResult);

        Plant plant = new Plant();
        plant.setPlantCost(10.0d);
        plant.setPlantDesccription("Plant Desccription");
        plant.setPlantId(123);
        plant.setPlantName("Plant Name");
        when(plantRepository.save((Plant) any())).thenReturn(plant);
        when(plantRepository.findById((Integer) any())).thenReturn(Optional.empty());

        Plant plant1 = new Plant();
        plant1.setPlantCost(10.0d);
        plant1.setPlantDesccription("Plant Desccription");
        plant1.setPlantId(123);
        plant1.setPlantName("Plant Name");
        plantServices.updatePlant(plant1, 123);
    }

    /**
     * Method under test: {@link PlantServices#viewPlant()}
     */
    @Test
    void testViewPlant() {
        ArrayList<Plant> plantList = new ArrayList<>();
        when(plantRepository.findAll()).thenReturn(plantList);
        List actualViewPlantResult = plantServices.viewPlant();
        assertSame(plantList, actualViewPlantResult);
        assertTrue(actualViewPlantResult.isEmpty());
        verify(plantRepository).findAll();
    }

    /**
     * Method under test: {@link PlantServices#viewPlant()}
     */
    @Test
    void testViewPlant2() {
        when(plantRepository.findAll()).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> plantServices.viewPlant());
        verify(plantRepository).findAll();
    }
}

