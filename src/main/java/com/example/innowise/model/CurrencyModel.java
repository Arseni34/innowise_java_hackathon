package com.example.innowise.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="Currencies",
        uniqueConstraints={@UniqueConstraint(columnNames={"ID"})})
public class CurrencyModel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID", nullable=false, unique=true, length=11)
    private int id;
    @Column(name="date", length=20, nullable=true)
    String  date;

    @Column(name="NAME", length=20, nullable=true)
    String cur_Name;

    @Column(name="Currency", length=20, nullable=true)
    Double cur_OfficialRate;

    @Override
    public String toString() {
        return
                " Name: " + cur_Name + '\n' +
                " Price:  " + cur_OfficialRate +'\n'+
                '\n';
    }

}