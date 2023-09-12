package di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import presentation.login.LoginScreenModel
import presentation.main.MainScreenModel

val ScreenModelModule = module {
    factoryOf(::LoginScreenModel)
    factoryOf(::MainScreenModel)
}

