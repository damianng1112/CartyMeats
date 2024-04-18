package model;

public class Feedbacks {
    private int feedbackId;
    private String title;
    private String description;
    private int clientId;
    private int productId;
    private String clientName;
    private String productName;

    // Constructor
    public Feedbacks(int feedbackId, String title, String description, String clientName, String productName) {
        this.feedbackId = feedbackId;
        this.title = title;
        this.description = description;
        this.clientName = clientName;
        this.productName = productName;
    }
    
    public Feedbacks(int feedbackId, String title, String description, int clientId, int productId) {
        this.feedbackId = feedbackId;
        this.title = title;
        this.description = description;
        this.clientId = clientId;
        this.productId = productId;
    }

    // Getters and Setters
    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientId(String clientName) {
        this.clientName = clientName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductId(String productName) {
        this.productName = productName;
    }
}
