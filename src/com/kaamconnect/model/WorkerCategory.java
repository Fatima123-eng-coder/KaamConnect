package com.kaamconnect.model;

public class WorkerCategory {
    private int userId;
    private int categoryId;

    public WorkerCategory(int userId, int categoryId) {
        this.userId = userId;
        this.categoryId = categoryId;

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
