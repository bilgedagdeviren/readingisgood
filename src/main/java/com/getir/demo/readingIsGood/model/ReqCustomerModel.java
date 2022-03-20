package com.getir.demo.readingIsGood.model;

import com.getir.demo.readingIsGood.entities.Customer;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.*;

@Data
@ToString
public class ReqCustomerModel {
    @NotBlank(message = "Email Can't be blank")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Name should not be blank")
    private String name;

    public Customer toCustomerEntity() {
        Customer customer = new Customer();
        customer.setEmail(this.email);
        customer.setName(this.name);
        return customer;
    }

}