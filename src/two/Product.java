package two;

public class Product {
    private String title;
    private float price;
    private Category category;

    public Product(String productName, float price, Category category) {
        this.title = productName;
        this.price = price;
        this.category = category;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String productName) {
        this.title = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
