package com.beyonder.expense;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Expense extends PanacheEntity {
    public String titleName;
    public String author;
    public String description;
}
