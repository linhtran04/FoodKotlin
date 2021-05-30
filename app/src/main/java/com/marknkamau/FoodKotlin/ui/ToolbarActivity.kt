package com.thuylinhtran.FoodKotlin.ui

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import com.marknjunge.core.data.local.PreferencesRepository
import com.thuylinhtran.FoodKotlin.R
import com.thuylinhtran.FoodKotlin.ui.about.AboutActivity
import com.thuylinhtran.FoodKotlin.ui.base.BaseActivity
import com.thuylinhtran.FoodKotlin.ui.cart.CartActivity
import com.thuylinhtran.FoodKotlin.ui.login.SignInActivity
import com.thuylinhtran.FoodKotlin.ui.profile.ProfileActivity
import org.koin.android.ext.android.inject

abstract class ToolbarActivity : BaseActivity() {

    private val preferencesRepository: PreferencesRepository by inject()

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.overflow_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        super.onPrepareOptionsMenu(menu)
        if (this is CartActivity) {
            menu?.findItem(R.id.menu_cart)?.isVisible = false
        }
        if (this is ProfileActivity) {
            menu?.findItem(R.id.menu_profile)?.isVisible = false
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_cart -> {
                startActivity(Intent(this, CartActivity::class.java))
                true
            }
            R.id.menu_profile -> {
                if (preferencesRepository.isSignedIn) {
                    startActivity(Intent(this, ProfileActivity::class.java))
                } else {
                    startActivity(Intent(this, SignInActivity::class.java))
                }
                true
            }
            R.id.menu_about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
