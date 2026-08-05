package com.kaamconnect.model;

public class ServiceCategory {
    private int categoryId;
    private String categoryName;
    private String description;
    private String iconPath;

    public ServiceCategory(int categoryId, String iconPath, String categoryName, String description) {
        this.categoryId = categoryId;
        this.iconPath = iconPath;
        this.categoryName = categoryName;
        this.description = description;
    }

    public ServiceCategory() {

    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }
}
