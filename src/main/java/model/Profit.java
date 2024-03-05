/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author admin
 */
public class Profit {
    private int id;
    private double sale;
    private double buy;
    private String fruitName;

    // Default constructor
    public Profit() {
    }

    // Parameterized constructor
    public Profit(int id, double sale, double buy, String fruitName) {
        this.id = id;
        this.sale = sale;
        this.buy = buy;
        this.fruitName = fruitName;
    }

    // Getter and Setter methods for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter methods for sale
    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    // Getter and Setter methods for buy
    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    // Getter and Setter methods for fruitName
    public String getFruitName3() {
        return fruitName;
    }

    public void setFruitName3(String fruitName) {
        this.fruitName = fruitName;
    }

    // toString method for easy printing of object details
    @Override
    public String toString() {
        return "Profit{" +
                "id=" + id +
                ", sale=" + sale +
                ", buy=" + buy +
                ", fruitName='" + fruitName + '\'' +
                '}';
    }
}
