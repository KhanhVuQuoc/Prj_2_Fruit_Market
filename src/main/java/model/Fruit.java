package model;

public class Fruit {
    private int id;
    private String Fruitname;
    private String ImgSrc;
    private double Price;
    private String Color;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getFruitName() {
        return Fruitname;
    }

    public void setFruitName(String Fruitname) {
        this.Fruitname = Fruitname;
    }

    public String getImgSrc() {
        return ImgSrc;
    }

    public void setImgSrc(String ImgSrc) {
        this.ImgSrc = ImgSrc;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }
    
    public Fruit() {
    }

    public Fruit(String Fruitname, double Price, String ImgSrc, String Color) {
        this.Fruitname = Fruitname;
        this.Price = Price;
        this.ImgSrc = ImgSrc;
        this.Color = Color;
    }
}
