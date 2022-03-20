package com.getir.demo.readingIsGood.entities;

import lombok.Data;

public interface Report {
    String getMonth();
    Long getOrdersCount();
    Long getBooksCount();
    Double getTotalPurchasedAmount();
}
