package com.getir.demo.readingIsGood.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@Entity
public class BookOrder extends RepresentationModel<BookOrder> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @NotNull
    private Book book;

    @ManyToOne
    @JsonIgnoreProperties("orders")
    @NotNull
    private Customer customer;

    @Min(1)
    private int quantity;

    @NotNull
    private Date date;

}
