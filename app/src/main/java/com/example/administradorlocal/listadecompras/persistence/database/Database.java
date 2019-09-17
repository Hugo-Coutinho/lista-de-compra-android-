package com.example.administradorlocal.listadecompras.persistence.database;

import android.arch.persistence.room.RoomDatabase;

import com.example.administradorlocal.listadecompras.persistence.entity.Product;
import com.example.administradorlocal.listadecompras.persistence.entity.ShoppingList;
import com.example.administradorlocal.listadecompras.persistence.dao.ProductDao;
import com.example.administradorlocal.listadecompras.persistence.dao.ShoppingListDao;

@android.arch.persistence.room.Database(entities = {Product.class, ShoppingList.class}, version = 4, exportSchema = false)
public abstract class Database extends RoomDatabase {

    public abstract ProductDao productDao();

    public abstract ShoppingListDao ShoppingListDao();
}
