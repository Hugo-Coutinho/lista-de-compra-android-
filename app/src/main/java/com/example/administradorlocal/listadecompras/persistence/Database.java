package com.example.administradorlocal.listadecompras.persistence;

import android.arch.persistence.room.RoomDatabase;

import com.example.administradorlocal.listadecompras.entity.Product;

@android.arch.persistence.room.Database(entities = {Product.class}, version = 3)
public abstract class Database extends RoomDatabase {

    public abstract ProductDao productDao();
}
