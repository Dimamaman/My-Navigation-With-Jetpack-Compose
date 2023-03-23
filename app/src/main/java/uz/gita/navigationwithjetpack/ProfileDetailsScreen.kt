package uz.gita.navigationwithjetpack

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileDetailsScreen(userId: Int, navController: NavHostController) {
    val user = userList.first { user ->
        user.id == userId
    }
    Scaffold(
        topBar = {
            AppBar(
                title = "User Detail",
                imageVector = Icons.Filled.ArrowBack
            ) { navController.navigateUp() }
        }) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                ProfilePicture(user = user, dp = 200.dp)
                ProfileContent(user = user, alignment = Alignment.CenterHorizontally)
            }
        }
    }
}