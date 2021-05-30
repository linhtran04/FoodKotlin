package com.marknkamau.FoodKotlin.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marknkamau.FoodKotlin.data.models.CartOptionEntity
import com.marknkamau.FoodKotlin.data.models.CartProductEntity

@Database(
    entities = [CartProductEntity::class, CartOptionEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}
