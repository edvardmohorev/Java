package com.example.storage.classes

data class Files(
    val name: String?,
    val id:String,
    val url:String?,
    val parents: MutableList<String>? =null,
    val type:String
    )