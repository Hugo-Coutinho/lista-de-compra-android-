package com.example.administradorlocal.listadecompras.persistence;

import android.arch.persistence.room.RoomDatabase;

import com.example.administradorlocal.listadecompras.entity.Product;
import com.example.administradorlocal.listadecompras.entity.ShoppingList;

@android.arch.persistence.room.Database(entities = {Product.class, ShoppingList.class}, version = 4)
public abstract class Database extends RoomDatabase {

    public abstract ProductDao productDao();

    public abstract ShoppingListDao ShoppingListDao();
}
