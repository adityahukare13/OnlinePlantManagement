package com.onm.services;

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

import com.onm.entity.Coupan;
import com.onm.entity.Customer;
import com.onm.expections.NotAdminException;
import com.onm.repositories.CoupanRepository;
import com.onm.repositories.CustomerRepository;

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

@ContextConfiguration(classes = {CoupanServices.class})
@ExtendWith(SpringExtension.class)
class CoupanServicesTest {
    @MockBean
    private CoupanRepository coupanRepository;

    @Autowired
    private CoupanServices coupanServices;

    @MockBean
    private CustomerRepository customerRepository;

    /**
     * Method under test: {@link CoupanServices#addCoupan(Coupan, int)}
     */
    @Test
    void testAddCoupan() {
        Coupan coupan = new Coupan();
        coupan.setCoupanDescription("Coupan Description");
        coupan.setCoupanId(123);
        coupan.setCoupanName("Coupan Name");
        coupan.setCoupanType("Coupan Type");
        coupan.setCouponValue(10.0d);
        when(coupanRepository.save((Coupan) any())).thenReturn(coupan);

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

        Coupan coupan1 = new Coupan();
        coupan1.setCoupanDescription("Coupan Description");
        coupan1.setCoupanId(123);
        coupan1.setCoupanName("Coupan Name");
        coupan1.setCoupanType("Coupan Type");
        coupan1.setCouponValue(10.0d);
        assertTrue(coupanServices.addCoupan(coupan1, 123));
        verify(coupanRepository).save((Coupan) any());
        verify(customerRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link CoupanServices#addCoupan(Coupan, int)}
     */
    @Test
    void testAddCoupan2() {
        when(coupanRepository.save((Coupan) any())).thenThrow(new NotAdminException("An error occurred"));

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

        Coupan coupan = new Coupan();
        coupan.setCoupanDescription("Coupan Description");
        coupan.setCoupanId(123);
        coupan.setCoupanName("Coupan Name");
        coupan.setCoupanType("Coupan Type");
        coupan.setCouponValue(10.0d);
        assertThrows(NotAdminException.class, () -> coupanServices.addCoupan(coupan, 123));
        verify(coupanRepository).save((Coupan) any());
        verify(customerRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link CoupanServices#addCoupan(Coupan, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddCoupan3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:148)
        //       at com.onm.services.CoupanServices.addCoupan(CoupanServices.java:24)
        //   In order to prevent addCoupan(Coupan, int)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   addCoupan(Coupan, int).
        //   See https://diff.blue/R013 to resolve this issue.

        Coupan coupan = new Coupan();
        coupan.setCoupanDescription("Coupan Description");
        coupan.setCoupanId(123);
        coupan.setCoupanName("Coupan Name");
        coupan.setCoupanType("Coupan Type");
        coupan.setCouponValue(10.0d);
        when(coupanRepository.save((Coupan) any())).thenReturn(coupan);
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

        Coupan coupan1 = new Coupan();
        coupan1.setCoupanDescription("Coupan Description");
        coupan1.setCoupanId(123);
        coupan1.setCoupanName("Coupan Name");
        coupan1.setCoupanType("Coupan Type");
        coupan1.setCouponValue(10.0d);
        coupanServices.addCoupan(coupan1, 123);
    }

    /**
     * Method under test: {@link CoupanServices#viewCoupan()}
     */
    @Test
    void testViewCoupan() {
        ArrayList<Coupan> coupanList = new ArrayList<>();
        when(coupanRepository.findAll()).thenReturn(coupanList);
        List actualViewCoupanResult = coupanServices.viewCoupan();
        assertSame(coupanList, actualViewCoupanResult);
        assertTrue(actualViewCoupanResult.isEmpty());
        verify(coupanRepository).findAll();
    }

    /**
     * Method under test: {@link CoupanServices#viewCoupan()}
     */
    @Test
    void testViewCoupan2() {
        when(coupanRepository.findAll()).thenThrow(new NotAdminException("An error occurred"));
        assertThrows(NotAdminException.class, () -> coupanServices.viewCoupan());
        verify(coupanRepository).findAll();
    }

    /**
     * Method under test: {@link CoupanServices#deleteCoupan(int)}
     */
    @Test
    void testDeleteCoupan() {
        Coupan coupan = new Coupan();
        coupan.setCoupanDescription("Coupan Description");
        coupan.setCoupanId(123);
        coupan.setCoupanName("Coupan Name");
        coupan.setCoupanType("Coupan Type");
        coupan.setCouponValue(10.0d);
        Optional<Coupan> ofResult = Optional.of(coupan);
        doNothing().when(coupanRepository).deleteById((Integer) any());
        when(coupanRepository.findById((Integer) any())).thenReturn(ofResult);
        coupanServices.deleteCoupan(123);
        verify(coupanRepository).findById((Integer) any());
        verify(coupanRepository).deleteById((Integer) any());
    }

    /**
     * Method under test: {@link CoupanServices#deleteCoupan(int)}
     */
    @Test
    void testDeleteCoupan2() {
        Coupan coupan = new Coupan();
        coupan.setCoupanDescription("Coupan Description");
        coupan.setCoupanId(123);
        coupan.setCoupanName("Coupan Name");
        coupan.setCoupanType("Coupan Type");
        coupan.setCouponValue(10.0d);
        Optional<Coupan> ofResult = Optional.of(coupan);
        doThrow(new NotAdminException("An error occurred")).when(coupanRepository).deleteById((Integer) any());
        when(coupanRepository.findById((Integer) any())).thenReturn(ofResult);
        assertThrows(NotAdminException.class, () -> coupanServices.deleteCoupan(123));
        verify(coupanRepository).findById((Integer) any());
        verify(coupanRepository).deleteById((Integer) any());
    }

    /**
     * Method under test: {@link CoupanServices#deleteCoupan(int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteCoupan3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.onm.services.CoupanServices.deleteCoupan(CoupanServices.java:42)
        //   In order to prevent deleteCoupan(int)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   deleteCoupan(int).
        //   See https://diff.blue/R013 to resolve this issue.

        doNothing().when(coupanRepository).deleteById((Integer) any());
        when(coupanRepository.findById((Integer) any())).thenReturn(null);
        coupanServices.deleteCoupan(123);
    }
}

