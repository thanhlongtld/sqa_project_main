package com.ptit.sqa_project_main.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "type")
    private List<PriceLevel> priceLevels;

    @OneToOne(mappedBy = "type")
    private Location location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PriceLevel> getPriceLevels() {
        return priceLevels;
    }

    public void setPriceLevels(List<PriceLevel> priceLevels) {
        this.priceLevels = priceLevels;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
