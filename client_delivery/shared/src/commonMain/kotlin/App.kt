import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.ExperimentalResourceApi
import com.beepbeep.designSystem.App
@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    App()
//    MaterialTheme {
//        var greetingText by remember { mutableStateOf("Hello, World!") }
//        var showImage by remember { mutableStateOf(false) }
//        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//            Button(onClick = {
//                greetingText = "Hello, ${getPlatformName()}"
//                showImage = !showImage
//            }) {
//                Text(greetingText)
//            }
//            AnimatedVisibility(showImage) {
//                Image(
//                    painterResource("compose-multiplatform.xml"),
//                    null
//                )
//            }
//        }
//    }
}

expect fun getPlatformName(): String