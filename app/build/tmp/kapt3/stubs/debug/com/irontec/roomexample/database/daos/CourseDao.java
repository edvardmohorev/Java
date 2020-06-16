package com.irontec.roomexample.database.daos;

import java.lang.System;

/**
 * * Created by axier on 7/2/18.
 */
@android.arch.persistence.room.Dao()
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0010\b\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0004H\'J\b\u0010\u0012\u001a\u00020\u0010H\'J\u0016\u0010\u0013\u001a\u00020\u00102\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\'J!\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\'\u00a2\u0006\u0002\u0010\u0019R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038gX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038gX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038gX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u0006R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038gX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\u0006R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038gX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u0006\u00a8\u0006\u001a"}, d2 = {"Lcom/irontec/roomexample/database/daos/CourseDao;", "", "all", "", "Lcom/irontec/roomexample/database/entities/Course;", "getAll", "()Ljava/util/List;", "eur_buy", "getEur_buy", "eur_sell", "getEur_sell", "usd_buy", "getUsd_buy", "usd_sell", "getUsd_sell", "delete", "", "client", "deleteAll", "insertAll", "cours", "loadAllByIds", "customersId", "", "", "([Ljava/lang/Integer;)Ljava/util/List;", "app_debug"})
public abstract interface CourseDao {
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "SELECT * FROM course")
    public abstract java.util.List<com.irontec.roomexample.database.entities.Course> getAll();
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "SELECT * FROM course WHERE usd_buy=(SELECT max(usd_buy) FROM course)")
    public abstract java.util.List<com.irontec.roomexample.database.entities.Course> getUsd_buy();
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "SELECT * FROM course WHERE usd_sell=(SELECT min(usd_sell) FROM course)")
    public abstract java.util.List<com.irontec.roomexample.database.entities.Course> getUsd_sell();
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "SELECT * FROM course WHERE eur_buy=(SELECT max(eur_buy) FROM course)")
    public abstract java.util.List<com.irontec.roomexample.database.entities.Course> getEur_buy();
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "SELECT * FROM course WHERE eur_sell=(SELECT min(eur_sell) FROM course)")
    public abstract java.util.List<com.irontec.roomexample.database.entities.Course> getEur_sell();
    
    @org.jetbrains.annotations.NotNull()
    @android.arch.persistence.room.Query(value = "SELECT * FROM course WHERE uid IN (:customersId)")
    public abstract java.util.List<com.irontec.roomexample.database.entities.Course> loadAllByIds(@org.jetbrains.annotations.NotNull()
    java.lang.Integer[] customersId);
    
    @android.arch.persistence.room.Query(value = "DELETE FROM course")
    public abstract void deleteAll();
    
    @android.arch.persistence.room.Insert()
    public abstract void insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.irontec.roomexample.database.entities.Course> cours);
    
    @android.arch.persistence.room.Delete()
    public abstract void delete(@org.jetbrains.annotations.NotNull()
    com.irontec.roomexample.database.entities.Course client);
}