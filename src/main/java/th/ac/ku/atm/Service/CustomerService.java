package th.ac.ku.atm.Service;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import th.ac.ku.atm.Model.Customer;
import th.ac.ku.atm.data.CustomerRespository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {

    //    private List<Customer> customerList;
    private CustomerRespository repository;

    //    @PostConstruct
//    public void postConstruct(){
//        this.customerList = new ArrayList<>();
//    }
    public CustomerService(CustomerRespository repository) {
        this.repository = repository;
    }

    public void createCustomer(Customer customer){
        String hashPin = hash(customer.getPin());
        customer.setPin(hashPin);
        repository.save(customer);
//        customerList.add(customer);
    }
    public List<Customer> getCustomers(){

//        return new ArrayList<>(this.customerList);
        return repository.findAll();
    }

    private String hash(String pin) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(pin,salt);
    }
    public Customer findCustomer(int id) {
//        for (Customer customer : customerList) {
//            if (customer.getId() == id)
//                return customer;
//        }
//        return null;
        try {
            return repository.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }

    }
    public Customer checkPin(Customer inputCustomer) {
        Customer storedCustomer = findCustomer(inputCustomer.getId());
        if (storedCustomer != null) {
            String hashPin = storedCustomer.getPin();

            if (BCrypt.checkpw(inputCustomer.getPin(), hashPin))
                return storedCustomer;
        }
        return null;
    }


}