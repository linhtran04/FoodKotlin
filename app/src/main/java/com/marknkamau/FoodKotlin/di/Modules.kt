package com.marknkamau.FoodKotlin.di

import androidx.room.Room
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.marknjunge.core.data.local.PreferencesRepository
import com.marknjunge.core.data.network.service.FirebaseService
import com.marknjunge.core.data.network.service.GoogleSignInService
import com.marknkamau.FoodKotlin.BuildConfig
import com.marknkamau.FoodKotlin.data.db.AppDatabase
import com.marknkamau.FoodKotlin.data.db.DbRepository
import com.marknkamau.FoodKotlin.data.db.DbRepositoryImpl
import com.marknkamau.FoodKotlin.data.network.AppFirebaseService
import com.marknkamau.FoodKotlin.data.network.GoogleSignInServiceImpl
import com.marknkamau.FoodKotlin.data.preferences.PreferencesRepositoryImpl
import com.marknkamau.FoodKotlin.ui.addressBook.AddressBookViewModel
import com.marknkamau.FoodKotlin.ui.cart.CartViewModel
import com.marknkamau.FoodKotlin.ui.checkout.CheckoutViewModel
import com.marknkamau.FoodKotlin.ui.login.SignInViewModel
import com.marknkamau.FoodKotlin.ui.main.MainViewModel
import com.marknkamau.FoodKotlin.ui.orderDetail.OrderDetailViewModel
import com.marknkamau.FoodKotlin.ui.orders.OrdersViewModel
import com.marknkamau.FoodKotlin.ui.payCard.PayCardViewModel
import com.marknkamau.FoodKotlin.ui.payMpesa.PayMpesaViewModel
import com.marknkamau.FoodKotlin.ui.productDetails.ProductDetailsViewModel
import com.marknkamau.FoodKotlin.ui.profile.ProfileViewModel
import com.marknkamau.FoodKotlin.ui.signup.SignUpViewModel
import com.marknkamau.FoodKotlin.utils.NotificationHelper
import com.marknkamau.FoodKotlin.utils.NotificationHelperImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<PreferencesRepository> { PreferencesRepositoryImpl(androidContext()) }

    single<NotificationHelper> { NotificationHelperImpl(androidContext()) }

    single<GoogleSignInClient> {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(BuildConfig.googleClientId)
            .build()
        GoogleSignIn.getClient(androidContext(), gso)
    }

    single<GoogleSignInService> { GoogleSignInServiceImpl(get()) }

    single<FirebaseService> { AppFirebaseService() }
}

val dbModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "FoodKotlin-db")
            .fallbackToDestructiveMigrationFrom(1)
            .build()
    }
    single { get<AppDatabase>().cartDao() }
    single<DbRepository> { DbRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { ProductDetailsViewModel(get()) }
    viewModel { CartViewModel(get(), get(), get()) }
    viewModel { SignInViewModel(get(), get()) }
    viewModel { SignUpViewModel(get(), get()) }
    viewModel { ProfileViewModel(get(), get()) }
    viewModel { AddressBookViewModel(get()) }
    viewModel { CheckoutViewModel(get(), get(), get(), get()) }
    viewModel { OrdersViewModel(get()) }
    viewModel { OrderDetailViewModel(get(), get()) }
    viewModel { PayMpesaViewModel(get(), get()) }
    viewModel { PayCardViewModel(get()) }
}
