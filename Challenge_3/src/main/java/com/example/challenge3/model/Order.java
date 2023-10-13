package com.example.challenge3.model;

public class Order {
    private int quantity;
    private int finalPrice;

    public Order(int quantity, int finalPrice) {
        this.quantity = quantity;
        this.finalPrice = finalPrice;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getFinalPrice() {
        return this.finalPrice;
    }

    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /*
         * Check if o is an instance of Complex or not
         * "null instanceof [type]" also returns false
         */
        if (!(o instanceof Order)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Order c = (Order) o;

        // Compare the data members and return accordingly
        return c.getQuantity() == this.quantity && c.getFinalPrice() == this.finalPrice;
    }

}
