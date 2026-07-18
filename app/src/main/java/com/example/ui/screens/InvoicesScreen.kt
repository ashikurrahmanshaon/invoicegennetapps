package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Receipt
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun InvoicesScreen(onCreateInvoiceClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(24.dp)
            .testTag("invoices_screen")
    ) {
        // Title
        Text(
            text = "Invoices",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MainText,
            letterSpacing = (-0.5).sp,
            modifier = Modifier.padding(top = 16.dp, bottom = 40.dp)
        )

        // Center Content Container
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Elegant Dashed Icon
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .drawBehind {
                        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                        drawRoundRect(
                            color = BorderColor,
                            size = size,
                            cornerRadius = androidx.compose.ui.geometry.CornerRadius(20f, 20f),
                            style = androidx.compose.ui.graphics.drawscope.Stroke(
                                width = 3f,
                                pathEffect = pathEffect
                            )
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Receipt,
                    contentDescription = null,
                    tint = Primary,
                    modifier = Modifier.size(36.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Text content
            Text(
                text = "Keep track of your sales",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MainText,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Create invoices, track statuses, and send professional PDF receipts to clients.",
                fontSize = 14.sp,
                color = SecondaryText,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Action Button
            Button(
                onClick = onCreateInvoiceClick,
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.dp),
                modifier = Modifier.testTag("invoices_create_invoice_button")
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Create Invoice",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        
        Spacer(modifier = Modifier.height(80.dp)) // space for navigation bar
    }
}
