package com.getir.demo.readingIsGood.entities;


import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
public class Customer extends RepresentationModel<Customer> {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(unique = true)
    @NotNull
    @Size(max = 255)
    private String email;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 255)
    private String name;

    @OneToMany(mappedBy = "customer")
    private List<BookOrder> orders;

}
