package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerBase {
        private static CustomerBase customerBase;

        private final List<Customer> customerList= new ArrayList<>();

        private CustomerBase(){

        }
        public static CustomerBase getInstance() {
            if (customerBase == null) {
                customerBase = new CustomerBase();
            }
            return customerBase;
        }
        public List<Customer> getCustomerList(){
            return customerList;
        }
        public Optional<Customer> getCustomer(int customerID){
            return customerList.stream()
                    .filter(customer->Integer.valueOf(customer.getCustomerID()).equals(customerID))
                    .findFirst();
        }
        public void addCustomer(Customer customer){
            customerList.add(customer);
        }
        public void removeCustomer(int customerID){
            customerList.removeIf(customer->customer!=null&Integer.valueOf(customer.getCustomerID()).equals(customerID));

        }


    }

