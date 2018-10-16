package com.example.administradorlocal.listadecompras.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.administradorlocal.listadecompras.entity.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insertProduct(Product product);

    @Query("SELECT * FROM Product WHERE id = :id")
    Product fetchProductById(Integer id);

    @Query("SELECT * FROM Product WHERE name = :name")
    Product fetchProductByName(String name);

    @Query("SELECT * FROM Product")
    List<Product> findAll();

    @Update
    void update(Product p);

    @Delete
    void delete(Product p);


}
