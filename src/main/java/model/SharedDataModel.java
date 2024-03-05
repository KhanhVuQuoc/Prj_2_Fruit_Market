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
public class SharedDataModel {
    private int id;
    private int amount1;
    private String fruitName;
    private String fruitPrice;
    private String imageUrls;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getAmount1() {
        return amount1;
    }

    public void setAmount1(int amount1) {
        this.amount1 = amount1;
    }

    public String getFruitName2() {
        return fruitName;
    }

    public void setFruitName2(String fruitName) {
        this.fruitName = fruitName;
    }

    public String getFruitPrice() {
        return fruitPrice;
    }

    public void setFruitPrice(String fruitPrice) {
        this.fruitPrice = fruitPrice;
    }

    public String getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }
}
