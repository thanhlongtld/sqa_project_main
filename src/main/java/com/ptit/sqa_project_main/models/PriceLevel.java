package com.ptit.sqa_project_main.models;


import javax.persistence.*;

@Entity
@Table(name = "price_levels")
public class PriceLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer price;

//    @ManyToOne
//    @JoinColumn(name = "level_id")
//    private Level level;

}
