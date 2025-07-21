package com.wellBeing.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Habbit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;

 
}
