package com.example.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BackgroundColor
import com.example.ui.theme.MainText
import com.example.ui.theme.Primary
import com.example.ui.theme.PrimaryDark
import com.example.ui.theme.SecondaryText
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onSplashFinished: () -> Unit) {
    // Automatically transition to the dashboard after 1.5 seconds
    LaunchedEffect(Unit) {
        delay(1500)
        onSplashFinished()
    }

    // Logo pulse/entry animation
    val infiniteTransition = rememberInfiniteTransition(label = "logo_pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    val rotateAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotate"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .testTag("splash_screen_container"),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(24.dp)
        ) {
            // Premium Geometric Invoicing Logo Mark
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    val w = size.width
                    val h = size.height

                    // Draw abstract premium overlapping invoice sheet and a shield check
                    // 1. Draw invoice sheet background shadow block
                    drawRoundRect(
                        color = Primary.copy(alpha = 0.15f),
                        topLeft = Offset(w * 0.15f, h * 0.1f),
                        size = Size(w * 0.7f, h * 0.8f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(12f, 12f)
                    )

                    // 2. Draw front invoice sheet
                    drawRoundRect(
                        color = Primary,
                        topLeft = Offset(w * 0.1f, h * 0.15f),
                        size = Size(w * 0.65f, h * 0.7f),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(10f, 10f)
                    )

                    // 3. Draw lines representing invoice items
                    drawLine(
                        color = Color.White,
                        start = Offset(w * 0.22f, h * 0.3f),
                        end = Offset(w * 0.55f, h * 0.3f),
                        strokeWidth = 4f,
                        cap = StrokeCap.Round
                    )
                    drawLine(
                        color = Color.White,
                        start = Offset(w * 0.22f, h * 0.45f),
                        end = Offset(w * 0.45f, h * 0.45f),
                        strokeWidth = 4f,
                        cap = StrokeCap.Round
                    )
                    drawLine(
                        color = Color.White,
                        start = Offset(w * 0.22f, h * 0.6f),
                        end = Offset(w * 0.35f, h * 0.6f),
                        strokeWidth = 4f,
                        cap = StrokeCap.Round
                    )

                    // 4. Draw overlapping secure check badge
                    drawCircle(
                        color = PrimaryDark,
                        radius = w * 0.22f,
                        center = Offset(w * 0.75f, h * 0.72f)
                    )

                    // Draw white checkmark inside badge
                    val checkPath = androidx.compose.ui.graphics.Path().apply {
                        moveTo(w * 0.66f, h * 0.72f)
                        lineTo(w * 0.72f, h * 0.78f)
                        lineTo(w * 0.84f, h * 0.64f)
                    }
                    drawPath(
                        path = checkPath,
                        color = Color.White,
                        style = Stroke(width = 4f, cap = StrokeCap.Round)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Brand Title
            Text(
                text = "Invoice-Gen",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MainText,
                letterSpacing = (-0.5).sp,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Subtitle / Tagline
            Text(
                text = "Invoices made effortless.",
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = SecondaryText,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Subtle Loading Animation
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp).testTag("splash_loading"),
                color = Primary,
                strokeWidth = 2.5.dp
            )
        }
    }
}
