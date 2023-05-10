package service;

import entity.Customer;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.StoredProcedureQuery;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class CustomerService {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public CustomerService() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("Customer");
        this.entityManager = entityManagerFactory.createEntityManager();
    }


    public Customer findById(int id) {
        return entityManager.find(Customer.class, id);
    }


    public List<Customer> findAll(){
        List<Customer> customers = entityManager.createQuery("select c from Customer c").getResultList();
        return customers;
    }

    public void editCustomer(Customer newCustomer) {
        entityManager.getTransaction().begin();
        Customer customer = entityManager.find(Customer.class, newCustomer.getId());
        customer.setName(newCustomer.getName());
        customer.setAddress(newCustomer.getAddress());
        customer.setCity(newCustomer.getCity());
        entityManager.merge(customer);
        entityManager.getTransaction().commit();
    }

    public void addCustomer(String name, String address, String city) {
        entityManager.getTransaction().begin();
        entityManager.persist(new Customer(name, address, city));
        entityManager.getTransaction().commit();
    }

    public void deleteCustomer(int id) {
        entityManager.getTransaction().begin();
        Customer customer = entityManager.find(Customer.class, id);
        if (customer != null) {
            entityManager.remove(customer);
        }
        entityManager.getTransaction().commit();
    }

}
