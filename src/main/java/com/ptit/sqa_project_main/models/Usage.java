package com.ptit.sqa_project_main.models;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "usages")
public class Usage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer month;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer totalCBM;

    @Column(nullable = false)
    private Integer recentUsedCBM;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column()
    private Date createdAt;

    @OneToOne(mappedBy = "usage")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getTotalCBM() {
        return totalCBM;
    }

    public void setTotalCBM(Integer totalCBM) {
        this.totalCBM = totalCBM;
    }

    public Integer getRecentUsedCBM() {
        return recentUsedCBM;
    }

    public void setRecentUsedCBM(Integer recentUsedCBM) {
        this.recentUsedCBM = recentUsedCBM;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
