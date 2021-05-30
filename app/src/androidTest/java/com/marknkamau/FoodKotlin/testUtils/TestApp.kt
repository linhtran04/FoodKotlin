package com.marknkamau.FoodKotlin.testUtils

import com.marknjunge.core.data.local.PreferencesRepository
import com.marknjunge.core.data.repository.*
import com.marknkamau.FoodKotlin.FoodKotlinApp
import com.marknkamau.FoodKotlin.data.db.DbRepository
import com.marknkamau.FoodKotlin.di.viewModelModule
import com.marknkamau.FoodKotlin.utils.NotificationHelper
import io.mockk.mockk
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class TestApp : FoodKotlinApp() {

    companion object {
        val mockPreferencesRepository = mockk<PreferencesRepository>()
        val mockNotificationHelper = mockk<NotificationHelper>()

        val mockAuthRepository = mockk<AuthRepository>()
        val mockProductsRepository = mockk<ProductsRepository>()
        val mockUsersRepository = mockk<UsersRepository>()
        val mockCartRepository = mockk<CartRepository>()
        val mockOrdersRepository = mockk<OrdersRepository>()
        val mockPaymentsRepository = mockk<PaymentsRepository>()

        val mockDbRepository = mockk<DbRepository>()
    }

    override fun onCreate() {
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String {
                return "Timber ${super.createStackElementTag(element)}.${element.methodName}"
            }
        })

        startKoin {
            modules(listOf(mockAppModule, mockRepositoriesModule, viewModelModule, mockDbModule))
        }
    }

    private val mockAppModule = module {
        single { mockPreferencesRepository }
        single { mockNotificationHelper }
    }

    private val mockRepositoriesModule = module {
        single { mockAuthRepository }
        single { mockProductsRepository }
        single { mockUsersRepository }
        single { mockCartRepository }
        single { mockOrdersRepository }
        single { mockPaymentsRepository }
    }

    private val mockDbModule = module {
        single { mockDbRepository }
    }

}