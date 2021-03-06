package com.ptit.sqa_project_main.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "levels")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer min;

    @Column(nullable = false)
    private Integer max;

    @OneToMany(mappedBy = "level")
    private List<PriceLevel> priceLevels;

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

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public List<PriceLevel> getPriceLevels() {
        return priceLevels;
    }

    public void setPriceLevels(List<PriceLevel> priceLevels) {
        this.priceLevels = priceLevels;
    }
}
