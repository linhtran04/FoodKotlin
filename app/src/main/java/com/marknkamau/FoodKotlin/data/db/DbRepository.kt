package com.marknkamau.FoodKotlin.data.db

import com.marknkamau.FoodKotlin.data.models.AppProduct
import com.marknkamau.FoodKotlin.data.models.CartItem

interface DbRepository {
    suspend fun saveItemToCart(product: AppProduct, quantity: Int)

    suspend fun getCartItems(): List<CartItem>

    suspend fun deleteItemFromCart(item: CartItem)

    suspend fun clearCart()
}
