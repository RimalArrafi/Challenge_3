package com.example.challenge3.model;

public class Food {
    private String name;
    private Integer price;

    public Food(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public Integer getPrice() {
        return this.price;
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
        if (!(o instanceof Food)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Food c = (Food) o;

        // Compare the data members and return accordingly
        return c.getName().equals(this.name) && c.getPrice().equals(this.price);
    }
}
