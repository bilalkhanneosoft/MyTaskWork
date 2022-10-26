package com.test.mytask.neosoft.repository;

import com.test.mytask.R

object DataSource {

    fun getAllImages() = mutableListOf<ListDataModel>().apply {
        add(ListDataModel(101, R.drawable.a1, ImageDataSource.getImages1()))
        add(ListDataModel(102, R.drawable.a2, ImageDataSource.getImages2()))
        add(ListDataModel(103, R.drawable.a3, ImageDataSource.getImages()))
        add(ListDataModel(104, R.drawable.a4, ImageDataSource.getImages1()))
        add(ListDataModel(105, R.drawable.a5, ImageDataSource.getImages2()))
        add(ListDataModel(106, R.drawable.a1, ImageDataSource.getImages()))
    }
}