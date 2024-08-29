package mx.cazv.todasbrillamos.view.layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import mx.cazv.todasbrillamos.view.components.BottomBar
import mx.cazv.todasbrillamos.view.components.TopBar

@Composable
fun MainLayout(navController: NavHostController, content: @Composable () -> Unit) {
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            content()
        }
    }
}