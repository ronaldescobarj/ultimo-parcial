package com.ucbcba.demo.entities;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialBlob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Set;

@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private SerialBlob photo;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SerialBlob getPhoto() {
        return photo;
    }

    public void setPhoto(SerialBlob photo) {
        this.photo = photo;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}
