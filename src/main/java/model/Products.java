package model;

import java.util.List;

public class Products {
    private int prodId;
    private String prodName;
    private String prodPic;
    private Category category;
    private String description;
    private int prodStock;
    private double price;
    private List<String> clientNames;
    
    public Products(int prodId, String prodName, String prodPic, Category category, String description, int prodStock, double price, List<String> clientNames) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.prodPic = prodPic;
        this.category = category;
        this.description = description;
        this.prodStock = prodStock;
        this.price = price;
        this.clientNames = clientNames;
    }

    public Products(int prodId, String prodName, String prodPic, Category category, String description, int prodStock, double price) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.prodPic = prodPic;
        this.category = category;
        this.description = description;
        this.prodStock = prodStock;
        this.price = price;
    }
    
    public Products(String prodName, String prodPic, Category category, String description, int prodStock, double price) {
        this.prodName = prodName;
        this.prodPic = prodPic;
        this.category = category;
        this.description = description;
        this.prodStock = prodStock;
        this.price = price;
    }

    // Getters and setters
    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdPic() {
        return prodPic;
    }

    public void setProdPic(String prodPic) {
        this.prodPic = prodPic;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProdStock() {
        return prodStock;
    }

    public void setProdStock(int prodStock) {
        this.prodStock = prodStock;
    }

	public List<String> getClientNames() {
		return clientNames;
	}

	public void setClientNames(List<String> clientNames) {
		this.clientNames = clientNames;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}