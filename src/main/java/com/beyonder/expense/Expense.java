package com.beyonder.expense;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Expense extends PanacheEntity {
    public String title;
    public String author;
    public Double total;
    public String description;

}
