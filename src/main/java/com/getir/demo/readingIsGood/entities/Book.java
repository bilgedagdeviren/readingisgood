package com.getir.demo.readingIsGood.entities;

import lombok.Data;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@ToString
public class Book extends RepresentationModel<Book> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 500)
    private String name;

    @NotBlank
    @Size(max = 500)
    private String author;

    @Min(1000)
    @Max(2099)
    private int year;

    @DecimalMin("0.01")
    @DecimalMax(""+Double.MAX_VALUE)
    private double price;

    @Min(0)
    @Max(Integer.MAX_VALUE)
    private int count;
}
