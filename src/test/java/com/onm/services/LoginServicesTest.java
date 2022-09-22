package com.onm.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.onm.entity.Customer;
import com.onm.expections.ResourceAlreadyExistException;
import com.onm.expections.ResourceNotFoundException;
import com.onm.repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {LoginServices.class})
@ExtendWith(SpringExtension.class)
class LoginServicesTest {
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private LoginServices loginServices;

    /**
     * Method under test: {@link LoginServices#register(Customer)}
     */
    @Test
    void testRegister() {
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

        Customer customer1 = new Customer();
        customer1.setCustomerAddress("42 Main St");
        customer1.setCustomerEmail("jane.doe@example.org");
        customer1.setCustomerId(123);
        customer1.setCustomerMobile(1L);
        customer1.setCustomerName("Customer Name");
        customer1.setCustomerType("Customer Type");
        customer1.setLoginStatus(true);
        customer1.setPassword("iloveyou");
        customer1.setUserName("janedoe");
        when(customerRepository.findByCustomerEmail((String) any())).thenReturn(customer);
        when(customerRepository.save((Customer) any())).thenReturn(customer1);

        Customer customer2 = new Customer();
        customer2.setCustomerAddress("42 Main St");
        customer2.setCustomerEmail("jane.doe@example.org");
        customer2.setCustomerId(123);
        customer2.setCustomerMobile(1L);
        customer2.setCustomerName("Customer Name");
        customer2.setCustomerType("Customer Type");
        customer2.setLoginStatus(true);
        customer2.setPassword("iloveyou");
        customer2.setUserName("janedoe");
        assertThrows(ResourceAlreadyExistException.class, () -> loginServices.register(customer2));
        verify(customerRepository).findByCustomerEmail((String) any());
    }

    /**
     * Method under test: {@link LoginServices#register(Customer)}
     */
    @Test
    void testRegister2() {
        when(customerRepository.findByCustomerEmail((String) any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));
        when(customerRepository.save((Customer) any())).thenThrow(new ResourceNotFoundException("An error occurred"));

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
        assertThrows(ResourceNotFoundException.class, () -> loginServices.register(customer));
        verify(customerRepository).findByCustomerEmail((String) any());
    }

    /**
     * Method under test: {@link LoginServices#login(Customer)}
     */
    @Test
    void testLogin() {
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

        Customer customer1 = new Customer();
        customer1.setCustomerAddress("42 Main St");
        customer1.setCustomerEmail("jane.doe@example.org");
        customer1.setCustomerId(123);
        customer1.setCustomerMobile(1L);
        customer1.setCustomerName("Customer Name");
        customer1.setCustomerType("Customer Type");
        customer1.setLoginStatus(true);
        customer1.setPassword("iloveyou");
        customer1.setUserName("janedoe");
        when(customerRepository.save((Customer) any())).thenReturn(customer1);
        when(customerRepository.findByCustomerEmail((String) any())).thenReturn(customer);

        Customer customer2 = new Customer();
        customer2.setCustomerAddress("42 Main St");
        customer2.setCustomerEmail("jane.doe@example.org");
        customer2.setCustomerId(123);
        customer2.setCustomerMobile(1L);
        customer2.setCustomerName("Customer Name");
        customer2.setCustomerType("Customer Type");
        customer2.setLoginStatus(true);
        customer2.setPassword("iloveyou");
        customer2.setUserName("janedoe");
        Customer actualLoginResult = loginServices.login(customer2);
        assertSame(customer, actualLoginResult);
        assertTrue(actualLoginResult.isLoginStatus());
        verify(customerRepository).findByCustomerEmail((String) any());
        verify(customerRepository).save((Customer) any());
    }

    /**
     * Method under test: {@link LoginServices#login(Customer)}
     */
    @Test
    void testLogin2() {
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
        when(customerRepository.save((Customer) any())).thenThrow(new ResourceAlreadyExistException("An error occurred"));
        when(customerRepository.findByCustomerEmail((String) any())).thenReturn(customer);

        Customer customer1 = new Customer();
        customer1.setCustomerAddress("42 Main St");
        customer1.setCustomerEmail("jane.doe@example.org");
        customer1.setCustomerId(123);
        customer1.setCustomerMobile(1L);
        customer1.setCustomerName("Customer Name");
        customer1.setCustomerType("Customer Type");
        customer1.setLoginStatus(true);
        customer1.setPassword("iloveyou");
        customer1.setUserName("janedoe");
        assertThrows(ResourceAlreadyExistException.class, () -> loginServices.login(customer1));
        verify(customerRepository).findByCustomerEmail((String) any());
        verify(customerRepository).save((Customer) any());
    }

    /**
     * Method under test: {@link LoginServices#login(Customer)}
     */
    @Test
    void testLogin3() {
        Customer customer = mock(Customer.class);
        when(customer.getPassword()).thenReturn("foo");
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

        Customer customer1 = new Customer();
        customer1.setCustomerAddress("42 Main St");
        customer1.setCustomerEmail("jane.doe@example.org");
        customer1.setCustomerId(123);
        customer1.setCustomerMobile(1L);
        customer1.setCustomerName("Customer Name");
        customer1.setCustomerType("Customer Type");
        customer1.setLoginStatus(true);
        customer1.setPassword("iloveyou");
        customer1.setUserName("janedoe");
        when(customerRepository.save((Customer) any())).thenReturn(customer1);
        when(customerRepository.findByCustomerEmail((String) any())).thenReturn(customer);

        Customer customer2 = new Customer();
        customer2.setCustomerAddress("42 Main St");
        customer2.setCustomerEmail("jane.doe@example.org");
        customer2.setCustomerId(123);
        customer2.setCustomerMobile(1L);
        customer2.setCustomerName("Customer Name");
        customer2.setCustomerType("Customer Type");
        customer2.setLoginStatus(true);
        customer2.setPassword("iloveyou");
        customer2.setUserName("janedoe");
        assertThrows(ResourceNotFoundException.class, () -> loginServices.login(customer2));
        verify(customerRepository).findByCustomerEmail((String) any());
        verify(customer).getPassword();
        verify(customer).setCustomerAddress((String) any());
        verify(customer).setCustomerEmail((String) any());
        verify(customer).setCustomerId(anyInt());
        verify(customer).setCustomerMobile((Long) any());
        verify(customer).setCustomerName((String) any());
        verify(customer).setCustomerType((String) any());
        verify(customer).setLoginStatus(anyBoolean());
        verify(customer).setPassword((String) any());
        verify(customer).setUserName((String) any());
    }

    /**
     * Method under test: {@link LoginServices#updateCustomer(Customer)}
     */
    @Test
    void testUpdateCustomer() {
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

        Customer customer1 = new Customer();
        customer1.setCustomerAddress("42 Main St");
        customer1.setCustomerEmail("jane.doe@example.org");
        customer1.setCustomerId(123);
        customer1.setCustomerMobile(1L);
        customer1.setCustomerName("Customer Name");
        customer1.setCustomerType("Customer Type");
        customer1.setLoginStatus(true);
        customer1.setPassword("iloveyou");
        customer1.setUserName("janedoe");
        when(customerRepository.save((Customer) any())).thenReturn(customer1);
        when(customerRepository.findById((Integer) any())).thenReturn(ofResult);

        Customer customer2 = new Customer();
        customer2.setCustomerAddress("42 Main St");
        customer2.setCustomerEmail("jane.doe@example.org");
        customer2.setCustomerId(123);
        customer2.setCustomerMobile(1L);
        customer2.setCustomerName("Customer Name");
        customer2.setCustomerType("Customer Type");
        customer2.setLoginStatus(true);
        customer2.setPassword("iloveyou");
        customer2.setUserName("janedoe");
        loginServices.updateCustomer(customer2);
        verify(customerRepository).save((Customer) any());
        verify(customerRepository, atLeast(1)).findById((Integer) any());
    }

    /**
     * Method under test: {@link LoginServices#updateCustomer(Customer)}
     */
    @Test
    void testUpdateCustomer2() {
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
        when(customerRepository.save((Customer) any())).thenThrow(new ResourceAlreadyExistException("An error occurred"));
        when(customerRepository.findById((Integer) any())).thenReturn(ofResult);

        Customer customer1 = new Customer();
        customer1.setCustomerAddress("42 Main St");
        customer1.setCustomerEmail("jane.doe@example.org");
        customer1.setCustomerId(123);
        customer1.setCustomerMobile(1L);
        customer1.setCustomerName("Customer Name");
        customer1.setCustomerType("Customer Type");
        customer1.setLoginStatus(true);
        customer1.setPassword("iloveyou");
        customer1.setUserName("janedoe");
        assertThrows(ResourceAlreadyExistException.class, () -> loginServices.updateCustomer(customer1));
        verify(customerRepository).save((Customer) any());
        verify(customerRepository, atLeast(1)).findById((Integer) any());
    }

    /**
     * Method under test: {@link LoginServices#updateCustomer(Customer)}
     */
    @Test
    void testUpdateCustomer3() {
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
        when(customerRepository.save((Customer) any())).thenReturn(customer);
        when(customerRepository.findById((Integer) any())).thenReturn(Optional.empty());

        Customer customer1 = new Customer();
        customer1.setCustomerAddress("42 Main St");
        customer1.setCustomerEmail("jane.doe@example.org");
        customer1.setCustomerId(123);
        customer1.setCustomerMobile(1L);
        customer1.setCustomerName("Customer Name");
        customer1.setCustomerType("Customer Type");
        customer1.setLoginStatus(true);
        customer1.setPassword("iloveyou");
        customer1.setUserName("janedoe");
        loginServices.updateCustomer(customer1);
        verify(customerRepository).findById((Integer) any());
        assertEquals("42 Main St", customer1.getCustomerAddress());
        assertTrue(customer1.isLoginStatus());
        assertEquals("janedoe", customer1.getUserName());
        assertEquals("iloveyou", customer1.getPassword());
        assertEquals("Customer Type", customer1.getCustomerType());
        assertEquals("Customer Name", customer1.getCustomerName());
        assertEquals(1L, customer1.getCustomerMobile().longValue());
        assertEquals(123, customer1.getCustomerId());
        assertEquals("jane.doe@example.org", customer1.getCustomerEmail());
    }

    /**
     * Method under test: {@link LoginServices#viewCustomerList()}
     */
    @Test
    void testViewCustomerList() {
        ArrayList<Customer> customerList = new ArrayList<>();
        when(customerRepository.findAll()).thenReturn(customerList);
        List actualViewCustomerListResult = loginServices.viewCustomerList();
        assertSame(customerList, actualViewCustomerListResult);
        assertTrue(actualViewCustomerListResult.isEmpty());
        verify(customerRepository).findAll();
    }

    /**
     * Method under test: {@link LoginServices#viewCustomerList()}
     */
    @Test
    void testViewCustomerList2() {
        when(customerRepository.findAll()).thenThrow(new ResourceAlreadyExistException("An error occurred"));
        assertThrows(ResourceAlreadyExistException.class, () -> loginServices.viewCustomerList());
        verify(customerRepository).findAll();
    }
}

