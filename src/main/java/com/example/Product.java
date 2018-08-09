package com.example;

public class Product {
    @FieldAnnotation( required = true , reportName = "Name" , regex = "\\|?Name:(UnnecessarySuffixForOurLogic)([A-Za-z]+)\\|?" , capturingGroup = 2 )
    private String name;
    @FieldAnnotation( required = true , reportName = "Quantity" , regex = "\\|?Quantity:([0-9]+)\\|?")
    private long quantity;
    @FieldAnnotation( required = true , reportName = "Price" , regex = "\\|?Price:([0-9]+\\.[0-9]{2})\\|?")
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public double getTotal(){
        return price * quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
