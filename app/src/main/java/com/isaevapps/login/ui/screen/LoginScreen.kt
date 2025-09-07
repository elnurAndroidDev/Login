package com.isaevapps.login.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.isaevapps.login.R
import com.isaevapps.login.ui.theme.LoginTheme

@Composable
fun LoginScreen(
    onEmailClick: () -> Unit = {},
    onNumberClick: () -> Unit = {},
    onGoogleClick: () -> Unit = {},
    onSignUpClick: () -> Unit = {},
) {
    val emailColor = Color(0xffff8a2e)
    val numberColor = Color(0xff9c27b0)
    val googleColor = Color(0xffe53935)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .systemBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(24.dp))
            Image(
                painter = painterResource(R.drawable.pic_2),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
                    .padding(top = 8.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Login for UILover",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 24.dp, bottom = 36.dp)
            )

            LoginIconButton(
                text = "Continue with Email",
                iconRes = R.drawable.email,
                container = emailColor,
                onClick = onEmailClick
            )
            Spacer(Modifier.height(24.dp))
            LoginIconButton(
                text = "Continue with Number",
                iconRes = R.drawable.phone,
                container = numberColor,
                onClick = onNumberClick
            )
            Spacer(Modifier.height(24.dp))
            LoginIconButton(
                text = "Continue with Google",
                iconRes = R.drawable.google,
                container = googleColor,
                onClick = onGoogleClick
            )
            Spacer(Modifier.height(24.dp))
            Spacer(Modifier.weight(1f))
        }
        val annotated = buildAnnotatedString {
            append("I am a new user?")
            pushStringAnnotation(tag = "signup", annotation = "signup")
            withStyle(
                SpanStyle(
                    color = Color(0xffff6a2e),
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(" Signup")
            }
            pop()
        }
        ClickableText(
            text = annotated,
            onClick = { offset ->
                annotated.getStringAnnotations(
                    tag = "signup",
                    start = offset,
                    end = offset
                ).firstOrNull()?.let {
                    onSignUpClick()
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
        )
    }
}

@Composable
private fun LoginIconButton(
    text: String,
    iconRes: Int,
    container: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = container,
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(iconRes), contentDescription = null,
                modifier = Modifier.size(36.dp)
            )
            Spacer(Modifier.width(12.dp))
            Text(text = text, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.width(200.dp))
        }
    }
}

@Preview(showSystemUi = false, showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginTheme {
        LoginScreen()
    }
}