package com.irontec.roomexample.database.entities;

import java.lang.System;

/**
 * * Created by axier on 7/2/18.
 */
@android.arch.persistence.room.Entity()
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0016\b\u0007\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\nR \u0010\b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR \u0010\t\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000eR\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R \u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000eR \u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000e\u00a8\u0006\u001b"}, d2 = {"Lcom/irontec/roomexample/database/entities/Course;", "", "uid", "", "name", "", "usd_buy", "usd_sell", "eur_buy", "eur_sell", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEur_buy", "()Ljava/lang/String;", "setEur_buy", "(Ljava/lang/String;)V", "getEur_sell", "setEur_sell", "getName", "setName", "getUid", "()I", "setUid", "(I)V", "getUsd_buy", "setUsd_buy", "getUsd_sell", "setUsd_sell", "app_debug"})
public final class Course {
    @android.arch.persistence.room.PrimaryKey()
    private int uid;
    @org.jetbrains.annotations.Nullable()
    @android.arch.persistence.room.ColumnInfo(name = "name")
    private java.lang.String name;
    @org.jetbrains.annotations.Nullable()
    @android.arch.persistence.room.ColumnInfo(name = "usd_buy")
    private java.lang.String usd_buy;
    @org.jetbrains.annotations.Nullable()
    @android.arch.persistence.room.ColumnInfo(name = "usd_sell")
    private java.lang.String usd_sell;
    @org.jetbrains.annotations.Nullable()
    @android.arch.persistence.room.ColumnInfo(name = "eur_buy")
    private java.lang.String eur_buy;
    @org.jetbrains.annotations.Nullable()
    @android.arch.persistence.room.ColumnInfo(name = "eur_sell")
    private java.lang.String eur_sell;
    
    public final int getUid() {
        return 0;
    }
    
    public final void setUid(int p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getName() {
        return null;
    }
    
    public final void setName(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getUsd_buy() {
        return null;
    }
    
    public final void setUsd_buy(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getUsd_sell() {
        return null;
    }
    
    public final void setUsd_sell(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getEur_buy() {
        return null;
    }
    
    public final void setEur_buy(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getEur_sell() {
        return null;
    }
    
    public final void setEur_sell(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public Course(int uid, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String usd_buy, @org.jetbrains.annotations.NotNull()
    java.lang.String usd_sell, @org.jetbrains.annotations.NotNull()
    java.lang.String eur_buy, @org.jetbrains.annotations.NotNull()
    java.lang.String eur_sell) {
        super();
    }
}