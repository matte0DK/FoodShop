package be.intecbrussel.foodshop.app;

import be.intecbrussel.foodshop.model.Customer;
import be.intecbrussel.foodshop.service.CustomerRepository;

public class TestApp2 {
    public static void main(String[] args) {
        CustomerRepository cr = new CustomerRepository();
        Customer jeanJaque = new Customer("Jean Jaque", 2, "jean.jaque@msn.com", 10);
        Customer matteo = new Customer("Matteo", 3, "matteo.dekerpel@msn.com", 100000000);

        cr.writeCustomer(jeanJaque);
        cr.writeCustomer(matteo);
    }
}
