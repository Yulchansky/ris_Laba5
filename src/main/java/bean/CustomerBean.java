package bean;

import entity.Customer;
import jakarta.faces.bean.ManagedBean;
import jakarta.inject.Inject;
import service.CustomerService;
import java.util.List;

@ManagedBean
public class CustomerBean {
    @Inject
    private CustomerService customerService;
    private String id;
    private String name;

    private String address;

    private String city;

    private Customer customer;
    private List<Customer> customers;
    private boolean isAdded;
    private boolean isDeleted;
    private boolean isEdited;


    public void addCustomer() {
        customerService.addCustomer(name, address, city);
        isAdded = true;
    }

    public void deleteCustomer() {
        this.customer = customerService.findById(Integer.parseInt(id));
        customerService.deleteCustomer(Integer.parseInt(id));
        isDeleted = true;
    }

    public void getCustomerById() {
        customer = customerService.findById(Integer.parseInt(id));
        if (customer != null) {
            id = String.valueOf(customer.getId());
            name = customer.getName();
            address = customer.getAddress();
            city = customer.getCity();
        }
    }


    public List<Customer> getCustomers() {
        if(customers == null) {
            customers = customerService.findAll();
        }
        return customers;
    }

    public void editCustomer() {
        Customer customer1 = customerService.findById(Integer.parseInt(id));
        if (customer1 != null) {
            customer = new Customer(Integer.parseInt(id), name, address, city);
            customerService.editCustomer(customer);
            isEdited = true;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public boolean isAdded() {
        return isAdded;
    }

    public void setAdded(boolean added) {
        isAdded = added;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isEdited() {
        return isEdited;
    }

    public void setEdited(boolean edited) {
        isEdited = edited;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
