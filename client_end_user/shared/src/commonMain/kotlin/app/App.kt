import androidx.compose.runtime.Composable
import app.di.AppModule.initKoin
import cafe.adriel.voyager.navigator.Navigator
import presentation.screens.HomeScreen

@Composable
fun App() {
    initKoin()
    Navigator(HomeScreen())
}