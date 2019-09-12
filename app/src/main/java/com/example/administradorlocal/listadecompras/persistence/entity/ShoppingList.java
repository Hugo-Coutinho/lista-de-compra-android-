package com.example.administradorlocal.listadecompras.persistence.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "ShoppingList")
public class ShoppingList {


    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "image")
    private String image;
    @ColumnInfo(name = "date")
    private long date;

    @Ignore
    public ShoppingList() {
    }

    public ShoppingList(String name, String image, long date) {
        this.name = name;
        this.image = image;
        this.date = date;
    }

    @Override
    public String toString() {
        return "ShoppingList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", date=" + date +
                '}';
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
