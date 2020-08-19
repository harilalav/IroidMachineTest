package com.example.iroid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("status")
    @Expose
    String status;
    @SerializedName("categories")
    @Expose
    String category;
    @SerializedName("image")
    @Expose
    String imageUrl;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @SerializedName("image")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public Category(String status, String category, String imageUrl) {
        this.status = status;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public Category() {
    }
}
