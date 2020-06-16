package com.irontec.roomexample;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u000104H\u0014R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R \u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u0010X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0012\"\u0004\b\u001e\u0010\u0014R\u001a\u0010\u001f\u001a\u00020 X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020&X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020\u0010X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0012\"\u0004\b-\u0010\u0014R\u001a\u0010.\u001a\u00020\u0010X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0012\"\u0004\b0\u0010\u0014\u00a8\u00065"}, d2 = {"Lcom/irontec/roomexample/Convert;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "bank_name", "Landroid/widget/ListView;", "getBank_name", "()Landroid/widget/ListView;", "setBank_name", "(Landroid/widget/ListView;)V", "button", "Landroid/widget/Button;", "getButton", "()Landroid/widget/Button;", "setButton", "(Landroid/widget/Button;)V", "buy", "Landroid/widget/RadioButton;", "getBuy", "()Landroid/widget/RadioButton;", "setBuy", "(Landroid/widget/RadioButton;)V", "cos", "", "Lcom/irontec/roomexample/database/entities/Course;", "getCos", "()Ljava/util/List;", "setCos", "(Ljava/util/List;)V", "eur", "getEur", "setEur", "number", "Landroid/widget/EditText;", "getNumber", "()Landroid/widget/EditText;", "setNumber", "(Landroid/widget/EditText;)V", "res", "Landroid/widget/TextView;", "getRes", "()Landroid/widget/TextView;", "setRes", "(Landroid/widget/TextView;)V", "sell", "getSell", "setSell", "usd", "getUsd", "setUsd", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class Convert extends android.support.v7.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    public java.util.List<com.irontec.roomexample.database.entities.Course> cos;
    @org.jetbrains.annotations.NotNull()
    public android.widget.ListView bank_name;
    @org.jetbrains.annotations.NotNull()
    public android.widget.RadioButton sell;
    @org.jetbrains.annotations.NotNull()
    public android.widget.RadioButton buy;
    @org.jetbrains.annotations.NotNull()
    public android.widget.RadioButton usd;
    @org.jetbrains.annotations.NotNull()
    public android.widget.RadioButton eur;
    @org.jetbrains.annotations.NotNull()
    public android.widget.TextView res;
    @org.jetbrains.annotations.NotNull()
    public android.widget.Button button;
    @org.jetbrains.annotations.NotNull()
    public android.widget.EditText number;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.irontec.roomexample.database.entities.Course> getCos() {
        return null;
    }
    
    public final void setCos(@org.jetbrains.annotations.NotNull()
    java.util.List<com.irontec.roomexample.database.entities.Course> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.widget.ListView getBank_name() {
        return null;
    }
    
    public final void setBank_name(@org.jetbrains.annotations.NotNull()
    android.widget.ListView p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.widget.RadioButton getSell() {
        return null;
    }
    
    public final void setSell(@org.jetbrains.annotations.NotNull()
    android.widget.RadioButton p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.widget.RadioButton getBuy() {
        return null;
    }
    
    public final void setBuy(@org.jetbrains.annotations.NotNull()
    android.widget.RadioButton p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.widget.RadioButton getUsd() {
        return null;
    }
    
    public final void setUsd(@org.jetbrains.annotations.NotNull()
    android.widget.RadioButton p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.widget.RadioButton getEur() {
        return null;
    }
    
    public final void setEur(@org.jetbrains.annotations.NotNull()
    android.widget.RadioButton p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.widget.TextView getRes() {
        return null;
    }
    
    public final void setRes(@org.jetbrains.annotations.NotNull()
    android.widget.TextView p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.widget.Button getButton() {
        return null;
    }
    
    public final void setButton(@org.jetbrains.annotations.NotNull()
    android.widget.Button p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.widget.EditText getNumber() {
        return null;
    }
    
    public final void setNumber(@org.jetbrains.annotations.NotNull()
    android.widget.EditText p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public Convert() {
        super();
    }
}