package model;

public class OrderCount {
    int id;
    String name;
    int amount;
    float total;

    public String toString() {
        return "OrderCount [id=" + id + ", name=" + name + ", amount=" + amount + ", total=" + total +"]";
    }

    public OrderCount() {
        super();
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
