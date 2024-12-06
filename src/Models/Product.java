package Models;

import enumpackage.Category;
import enumpackage.Color;
import enumpackage.Size;

public class Product {

    private long id;
    private Category category;
    private String name;
    private double price;
    private Size size;
    private Color color;
   private  String ImageUrl;
    private int quantity;

    static long GenerateId = 1;

    public Product(){
        this.id =  GenerateId++;
    }

    public Product (Category category,String name , double price ,Size size , Color color , String ImageUrl , int quantity ){
          this.id = GenerateId++;
          this.category = category;
          this.name = name ;
          this.price = price ;
          this.size = size;
          this.color = color;
          this.ImageUrl = ImageUrl;
          this.quantity = quantity;

    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", size=" + size +
                ", color=" + color +
                ", ImageUrl='" + ImageUrl + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
