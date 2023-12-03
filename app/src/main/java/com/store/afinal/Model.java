package com.store.afinal;

import com.google.firebase.database.Exclude;

public class Model {

    private String imageUrl;
    private String mKey;
    private String StorageKey;

    public Model(){

    }
    public Model(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Exclude
    public  String getKey() {
        return mKey;
    }
    @Exclude
    public void setKey(String key){
        mKey = key;
    }

    public String getStorageKey() {
        return StorageKey;
    }

    public void setStorageKey(String storageKey) {
        StorageKey = storageKey;
    }
}
