package com.store.afinal;

import com.google.firebase.database.Exclude;

public class AdminModel {

    private String imageUrl;
    private String mKey;
    private String StorageKey;


    public AdminModel(){

    }
    public AdminModel(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getStorageKey() {
        return StorageKey;
    }

    public void setStorageKey(String storageKey) {
        StorageKey = storageKey;
    }
}
