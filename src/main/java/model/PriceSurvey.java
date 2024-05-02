package model;

public class PriceSurvey {
    private int psId;
    private String location;
    private String product;
    private double price;
    private String barcode;
    
    // Constructors
    public PriceSurvey() {
    }

    public PriceSurvey(String location, String product, double price, String barcode) {
        this.location = location;
        this.product = product;
        this.price = price;
        this.barcode = barcode;
    }

    // Getters and setters
    public int getPsId() {
        return psId;
    }

    public void setPsId(int psId) {
        this.psId = psId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
