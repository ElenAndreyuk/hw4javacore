package two;

public class Order {
    private Customer customer;
    private Product product;
    private int amount;
    private float price;

    public Order(Customer customer, Product product, int amount, float price) {
        this.customer = customer;
        this.product = product;
        this.amount = amount;
        this.price = price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer.getPhone() +
                ", product=" + product.getTitle() +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
