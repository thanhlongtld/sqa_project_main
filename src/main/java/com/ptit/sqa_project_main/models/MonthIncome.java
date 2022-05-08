package com.ptit.sqa_project_main.models;

public class MonthIncome {
    private int month;
    private int numOfWater;
    private int allMoney;
    private int income;
    private int debt;

    public MonthIncome(int month, int numOfWater, int allMoney, int income, int debt) {
        this.month = month;
        this.numOfWater = numOfWater;
        this.allMoney = allMoney;
        this.income = income;
        this.debt = debt;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
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
