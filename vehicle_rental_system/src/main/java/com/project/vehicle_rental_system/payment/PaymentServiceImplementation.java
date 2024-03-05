
package com.project.vehicle_rental_system.payment;
import com.project.vehicle_rental_system.admin.AdminRepository;
import com.project.vehicle_rental_system.bank.Account;
import com.project.vehicle_rental_system.bank.BankRepository;
import com.project.vehicle_rental_system.customer.Customer;
import com.project.vehicle_rental_system.customer.CustomerRepository;
import com.project.vehicle_rental_system.payment.exceptions.RentPaymentException;
import com.project.vehicle_rental_system.payment.exceptions.ReturnPaymentException;
import com.project.vehicle_rental_system.vehicle.Vehicle;
import com.project.vehicle_rental_system.vehicle.VehicleRepository;
import com.project.vehicle_rental_system.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImplementation implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleService vehicleService;

    @Override
    public boolean rentPayment(TransactionDto transactionDto) throws RentPaymentException {
        Optional<Customer> customerOpt= customerRepository.findById(transactionDto.getCustomerId());
        if(customerOpt==null) throw new RentPaymentException("Customer is not found");
        List<Vehicle> vehicleOpt=vehicleRepository.findAll();
        List<Vehicle> activeVehicles=vehicleOpt.stream().filter(s->s.getIsAvailable()==Boolean.TRUE).collect(Collectors.toList());
        if(activeVehicles==null) throw new RentPaymentException("No active vehicles");
        Vehicle rentedVehicle=activeVehicles.get(0);
        Double vehicleRent=rentedVehicle.getRent();
        Integer id= transactionDto.getCustomerId();
        Customer customer=customerRepository.findById(id).get();
        Double customerBalance=customer.getCustomerAccount().getBankBalance();
        Account account =bankRepository.findById(1).get();
        Double adminBalance=account.getBankBalance();
        if (customerBalance < vehicleRent) {
            throw new RentPaymentException("Insufficient funds for rent payment");
        }
        // Perform payment transaction: Deduct rent from customer, add to admin
        customer.getCustomerAccount().setBankBalance(customerBalance - vehicleRent);
        account.setBankBalance(adminBalance + vehicleRent);

        // Update entities in the database
        customerRepository.save(customer);
        bankRepository.save(account);
       return true;
    }

    @Override
    public boolean returnPayment(TransactionDto transactionDto) throws ReturnPaymentException {
        Optional<Customer> customerOpt= customerRepository.findById(transactionDto.getCustomerId());
        if(customerOpt==null) throw new ReturnPaymentException("Customer is not found");
        List<Vehicle> vehicleOpt=vehicleRepository.findAll();
        List<Vehicle> activeVehicles=vehicleOpt.stream().filter(s->s.getIsAvailable()==Boolean.TRUE).collect(Collectors.toList());
        if(activeVehicles==null) throw new ReturnPaymentException("No active vehicles");
        Vehicle rentedVehicle=activeVehicles.get(0);
        Double vehicleRent=rentedVehicle.getRent();
        Integer id= transactionDto.getCustomerId();
        Customer customer=customerRepository.findById(id).get();
        Double customerBalance=customer.getCustomerAccount().getBankBalance();
        Account account =bankRepository.findById(1).get();
        Double adminBalance=account.getBankBalance();
        if (adminBalance < vehicleRent) {
            throw new ReturnPaymentException("Insufficient funds for return payment");
        }

        // Perform return payment transaction: Deduct rent from admin, add to customer
        account.setBankBalance(adminBalance - vehicleRent);
        customer.getCustomerAccount().setBankBalance(customerBalance + vehicleRent);

        // Update entities in the database
        bankRepository.save(account);
        customerRepository.save(customer);

        return true; // Payment successful
    }
//        if(transactionDto.AdminId.getAdminAccount().getBankBalance()<vehicleRent) throw new ReturnPaymentException("Cannot able to make a payment");
//        AdminId.getAdminAccount().setBankBalance(AdminId.getAdminAccount().getBankBalance()-vehicleRent);
//        CustomerId.getCustomerAccount().setBankBalance(CustomerId.getCustomerAccount().getBankBalance()+vehicleRent);

}


