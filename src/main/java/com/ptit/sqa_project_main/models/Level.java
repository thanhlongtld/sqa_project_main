package com.ptit.sqa_project_main.models;


import javax.persistence.*;

@Entity
@Table(name = "levels")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, columnDefinition = "UNSIGNED INT(11)")
    private Integer min;

    @Column(nullable = false, columnDefinition = "UNSIGNED INT(11)")
    private Integer max;
}
