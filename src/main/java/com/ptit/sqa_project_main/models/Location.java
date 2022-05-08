package com.ptit.sqa_project_main.models;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "locations")
public class Location {
    private Integer id;
    private String address;
    
}
