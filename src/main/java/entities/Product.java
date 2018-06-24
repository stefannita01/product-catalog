package entities;

public class Product {
    private int id;
    private String name;
    private String brand;
    private String category;
    private float price;
    private int stock;
    private String imagePath;

    public Product(String name, String brand, String category, 
            float price, int stock, String imagePath) {
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.imagePath = imagePath;
    }
    
    public Product(int id, String name, String brand, String category, 
            float price, int stock, String imagePath) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public String getCategory() {
        return category;
    }
    
    public String getImagePath() {
        return imagePath;
    }
    
    public boolean equals(Product other) {
        if (this == other){
            return true;
        } else {
            return id == other.id;
        }
    }
}
