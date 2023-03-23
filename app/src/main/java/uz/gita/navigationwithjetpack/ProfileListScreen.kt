package uz.gita.navigationwithjetpack

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.coil.rememberCoilPainter
import uz.gita.navigationwithjetpack.ui.theme.lightGreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileListScreen(userList: List<UserProfile>, navController: NavHostController?) {
    Scaffold(topBar = {
        AppBar(title = "User List", imageVector = Icons.Filled.Menu) {}
    }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 10.dp)
        ) {
            LazyColumn {
                items(userList) { user ->
                    ProfileCard(user = user) {
                        navController?.navigate("userDetails/${it.id}")
                    }
                }
            }
        }
    }
}


@Composable
fun AppBar(title: String, imageVector: ImageVector, iconClickAction: () -> Unit) {
    TopAppBar(
        title = { Text(text = title, color = Color.Black) },
        navigationIcon = {
            IconButton(onClick = iconClickAction) {
                Icon(imageVector = imageVector, contentDescription = null)
            }
        },
        elevation = 5.dp,
        backgroundColor = Color.White
    )
}

@Composable
fun ProfileCard(user: UserProfile, clickAction: (UserProfile) -> Unit) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
            .clickable { clickAction(user) },
        elevation = 8.dp,
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture(user, 70.dp)
            ProfileContent(user, Alignment.Start)
        }
    }
}

@Composable
fun ProfilePicture(user: UserProfile, dp: Dp) {
    Card(
        shape = CircleShape,
        border = BorderStroke(
            width = 1.dp,
            color = if (user.status) MaterialTheme.colors.lightGreen else Color.Red
        ),
        modifier = Modifier.padding(16.dp),
        elevation = 4.dp
    ) {
        Image(
            painter = rememberCoilPainter(request = user.pictureUrl),
            contentDescription = null,
            modifier = Modifier.size(dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ProfileContent(user: UserProfile, alignment: Alignment.Horizontal) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = alignment
    ) {
        CompositionLocalProvider(
            LocalContentAlpha provides if (user.status) ContentAlpha.high else ContentAlpha.medium
        ) {
            Text(text = user.name, style = MaterialTheme.typography.h6)
        }

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = if (user.status) "Active now" else "Offline",
                style = MaterialTheme.typography.h6
            )
        }
    }
}