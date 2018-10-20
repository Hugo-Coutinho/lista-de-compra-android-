package com.example.administradorlocal.listadecompras.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.administradorlocal.listadecompras.entity.ShoppingList;

import java.util.List;

@Dao
public interface ShoppingListDao {

    @Insert
    void insertListProduct(ShoppingList product);

    @Query("SELECT * FROM ShoppingList WHERE id = :id")
    ShoppingList fetchSoppingListProductById(Integer id);

    @Query("SELECT * FROM ShoppingList")
    List<ShoppingList> findAll();

    @Update
    void update(ShoppingList p);

    @Delete
    void delete(ShoppingList p);



}
