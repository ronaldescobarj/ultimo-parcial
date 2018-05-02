package com.ucbcba.demo.entities;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import java.util.List;

//this is the class category
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String name;

}
