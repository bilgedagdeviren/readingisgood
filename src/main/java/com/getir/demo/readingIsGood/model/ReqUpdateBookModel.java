package com.getir.demo.readingIsGood.model;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@ToString
public class ReqUpdateBookModel {
    @Min(0)
    @Max(1000)
    private int quantity;
}
