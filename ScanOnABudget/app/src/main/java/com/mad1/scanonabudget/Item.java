package com.mad1.scanonabudget;

public class Item {
    private int item_id;
    private float price;
    private String store, barcode, name, decription;

    public Item() {
    }

    public Item(int item_id, String store, String barcode, String name, String decription, int price) {
        this.item_id = item_id;
        this.store = store;
        this.barcode = barcode;
        this.name = name;
        this.decription = decription;
        this.price = price;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

