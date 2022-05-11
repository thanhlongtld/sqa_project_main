package com.ptit.sqa_project_main.models;

import org.springframework.ui.Model;

public class MonthIncome {
    private String month;
    private int numOfWater;
    private int allMoney;
    private int income;
    private int debt;

    private String status;

    public MonthIncome(String month, int numOfWater, int allMoney, String status) {
        this.month = month;
        this.numOfWater = numOfWater;
        this.allMoney = allMoney;
        this.status = status;
    }

    public MonthIncome(String month, int numOfWater, int allMoney, int income, int debt) {
        this.month = month;
        this.numOfWater = numOfWater;
        this.allMoney = allMoney;
        this.income = income;
        this.debt = debt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getNumOfWater() {
        return numOfWater;
    }

    public void setNumOfWater(int numOfWater) {
        this.numOfWater = numOfWater;
    }

    public int getAllMoney() {
        return allMoney;
    }

    public void setAllMoney(int allMoney) {
        this.allMoney = allMoney;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }
}
