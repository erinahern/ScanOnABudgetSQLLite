package com.mad1.scanonabudget;

public class ListTable {
    private int List_id;
    private float item_price;
    private String item_name, item_store;


    public ListTable() {
    }

    public ListTable(int List_id,float item_price, String item_name, String item_store) {
        this.item_price = item_price;
        this.item_name = item_name;
        this.item_store = item_store;
    }

    public int getList_id() {
        return List_id;
    }

    public void setList_id(int List_id) {
        this.List_id = List_id;
    }

    public float getItem_price() {
        return item_price;
    }

    public void setItem_price(float item_price) {
        this.item_price = item_price;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_store() {
        return item_store;
    }

    public void setItem_store(String item_store) {
        this.item_store = item_store;
    }
}
