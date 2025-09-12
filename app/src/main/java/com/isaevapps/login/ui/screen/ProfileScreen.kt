package com.isaevapps.login.ui.screen

import android.view.Menu
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.isaevapps.login.R
import com.isaevapps.login.ui.theme.LoginTheme


private val IconBg = Color(0xfff0f1f3)
private val PrimaryText = Color(0xff1b1c1e)
private val SecondaryText = Color(0xff8a8e95)

private val MidSheet = Color(0xfff3f4f6)


@Composable
fun ProfileScreen(
    onNotification: () -> Unit = {},
    onCalendar: () -> Unit = {},
    onGallery: () -> Unit = {},
    onPlaylist: () -> Unit = {},
    onShare: () -> Unit = {},
    onLogout: () -> Unit = {},
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MidSheet)
    ) {
        Image(
            painter = painterResource(R.drawable.arc_pic),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 400.dp)
            .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
            .background(Color.White)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 230.dp)
            .verticalScroll(scrollState)
    ) {
        Surface(
            shape = CircleShape,
            shadowElevation = 6.dp,
            color = Color.White,
            modifier = Modifier
                .size(96.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(CircleShape)
            )
        }
        Spacer(Modifier.height(16.dp))
        Text(
            "Alex Flores",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                color = PrimaryText
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            "alexflores@gmail.com",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = SecondaryText
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 2.dp)
        )
        Spacer(Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .navigationBarsPadding()
        ) {
            MenuItemRow("Notification", R.drawable.btn_1, onClick = onNotification)
            MenuItemRow("Calendar", R.drawable.btn_2, onClick = onCalendar)
            MenuItemRow("Gallery", R.drawable.btn_3, onClick = onGallery)
            MenuItemRow("My Playlist", R.drawable.btn_4, onClick = onPlaylist)
            MenuItemRow("Share", R.drawable.btn_5, onClick = onShare)
            MenuItemRow("Logout", R.drawable.btn_6, onClick = onLogout)
            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
private fun MenuItemRow(
    title: String,
    iconRes: Int,
    onClick: () -> Unit = {},
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            shape = CircleShape,
            tonalElevation = 6.dp,
            color = IconBg,
            modifier = Modifier.size(50.dp)
        ) {
            Image(painter = painterResource(iconRes), contentDescription = null)
        }
        Spacer(Modifier.width(14.dp))
        Text(
            title, fontSize = 18.sp, style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            ),
            modifier = Modifier.weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Icon(
            painter = painterResource(R.drawable.arrow),
            contentDescription = null,
            tint = SecondaryText
        )
    }
}

@Preview
@Composable
private fun MenuItemRowPreview() {
    LoginTheme {
        MenuItemRow("Notification", R.drawable.btn_1, onClick = {})
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    LoginTheme {
        ProfileScreen()
    }
}