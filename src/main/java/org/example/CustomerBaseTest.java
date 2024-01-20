package org.example;


import org.example.CustomerBase;
import org.example.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerBaseTest {

    private CustomerBase customerBase;

    @BeforeEach
    public void setUp() {
        customerBase = CustomerBase.getInstance();
        customerBase.getCustomerList().clear();
    }

    @Test
    public void testSingletonInstance_NotNull() {
        assertNotNull(customerBase);
    }

    @Test
    public void testSingletonInstance() {
        CustomerBase instance1 = CustomerBase.getInstance();
        CustomerBase instance2 = CustomerBase.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void testGetCustomerList_Empty() {
        assertTrue(customerBase.getCustomerList().isEmpty());
    }

    @Test
    public void testAddCustomer_ShouldIncreaseListSize() {
        Customer customer = new Customer("ola", 1);
        customerBase.addCustomer(customer);
        assertEquals(1, customerBase.getCustomerList().size());
    }

    @Test
    public void testGetCustomer_ExistingCustomerID() {
        Customer customer = new Customer("ola", 1);
        customerBase.addCustomer(customer);
        int customerID = customer.getCustomerID();
        Optional<Customer> retrievedCustomer = customerBase.getCustomer(customerID);
        assertTrue(retrievedCustomer.isPresent());
        assertEquals(customer, retrievedCustomer.get());
    }

    @Test
    public void testGetCustomer_NonExistingCustomerID() {
        Optional<Customer> retrievedCustomer = customerBase.getCustomer(23);
        assertFalse(retrievedCustomer.isPresent());
    }

    @Test
    public void testRemoveCustomer_ExistingCustomerID() {
        Customer customer = new Customer("ola", 1);
        customerBase.addCustomer(customer);
        int customerID = customer.getCustomerID();
        customerBase.removeCustomer(customerID);
        Optional<Customer> retrievedCustomer = customerBase.getCustomer(customerID);
        assertFalse(retrievedCustomer.isPresent());
    }

    @Test
    public void testRemoveCustomer_NonExistingCustomerID() {
        Customer customer = new Customer("ola", 1);
        customerBase.addCustomer(customer);
        int originalSize = customerBase.getCustomerList().size();
        customerBase.removeCustomer(23);
        assertEquals(originalSize, customerBase.getCustomerList().size());
    }
}
