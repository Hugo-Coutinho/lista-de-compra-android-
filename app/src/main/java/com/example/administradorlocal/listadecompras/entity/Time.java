package com.example.administradorlocal.listadecompras.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Time {

    static SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

    @PrimaryKey
    private Integer id;
    @ColumnInfo(name = "calendar")
    private Date date;


    public Time() {
    }


    @Override
    public String toString() {
        return "Time{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return SDF.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
