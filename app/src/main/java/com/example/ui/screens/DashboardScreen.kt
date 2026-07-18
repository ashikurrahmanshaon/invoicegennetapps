package com.example.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun DashboardScreen(
    onCreateInvoiceClick: () -> Unit,
    onAddClientClick: () -> Unit,
    onAddExpenseClick: () -> Unit,
    onScanReceiptClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .testTag("dashboard_screen")
    ) {
        // 1. Header Row
        HeaderSection()

        Spacer(modifier = Modifier.height(20.dp))

        // 2. Primary Outstanding Card with Empty Chart Visual
        OutstandingFinancialCard()

        Spacer(modifier = Modifier.height(16.dp))

        // 3. Compact Three Cards Row: PAID, PENDING, OVERDUE
        SummaryCardsSection()

        Spacer(modifier = Modifier.height(24.dp))

        // 4. Quick Actions
        QuickActionsSection(
            onNewInvoice = onCreateInvoiceClick,
            onAddClient = onAddClientClick,
            onAddExpense = onAddExpenseClick,
            onScanReceipt = onScanReceiptClick
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 5. Recent Invoices (Empty State)
        RecentInvoicesSection(onCreateInvoice = onCreateInvoiceClick)
        
        Spacer(modifier = Modifier.height(80.dp)) // Extra padding for bottom navigation
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Good morning, Ashikur",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MainText,
                letterSpacing = (-0.5).sp
            )
            Text(
                text = "Here’s your business overview",
                fontSize = 14.sp,
                color = SecondaryText
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Notification bell
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(SurfaceColor)
                    .border(1.dp, BorderColor, CircleShape)
                    .clickable { /* Handle notifications */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "Notifications",
                    tint = MainText,
                    modifier = Modifier.size(20.dp)
                )
                // Red badge point
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(ErrorColor)
                        .align(Alignment.TopEnd)
                        .offset(x = (-8).dp, y = 8.dp)
                )
            }

            // Circular business avatar
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Primary.copy(alpha = 0.1f))
                    .border(2.dp, Primary.copy(alpha = 0.5f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "A",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Primary
                )
            }
        }
    }
}

@Composable
fun OutstandingFinancialCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("outstanding_financial_card"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Primary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "TOTAL OUTSTANDING",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White.copy(alpha = 0.7f),
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "$0.00",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        letterSpacing = (-1).sp
                    )
                }

                // Date filter dropdown style chip
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White.copy(alpha = 0.15f))
                        .clickable { /* Toggle date filter */ }
                        .padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "This Month",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowDown,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Subtle empty chart visualization: Wave outline or clean dotted lines
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .drawBehind {
                        val w = size.width
                        val h = size.height

                        // Draw background horizontal grid dashed line
                        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                        drawLine(
                            color = Color.White.copy(alpha = 0.15f),
                            start = Offset(0f, h * 0.5f),
                            end = Offset(w, h * 0.5f),
                            strokeWidth = 2f,
                            pathEffect = pathEffect
                        )

                        // Draw subtle empty smooth wave path indicating $0 activity
                        val path = androidx.compose.ui.graphics.Path().apply {
                            moveTo(0f, h * 0.5f)
                            cubicTo(w * 0.25f, h * 0.5f, w * 0.25f, h * 0.5f, w * 0.5f, h * 0.5f)
                            cubicTo(w * 0.75f, h * 0.5f, w * 0.75f, h * 0.5f, w, h * 0.5f)
                        }
                        drawPath(
                            path = path,
                            color = Color.White.copy(alpha = 0.6f),
                            style = androidx.compose.ui.graphics.drawscope.Stroke(
                                width = 3f,
                                cap = StrokeCap.Round
                            )
                        )

                        // Draw a soft glowing point on the line
                        drawCircle(
                            color = Color.White,
                            radius = 6f,
                            center = Offset(w * 0.5f, h * 0.5f)
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No invoice activity this month",
                    color = Color.White.copy(alpha = 0.6f),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun SummaryCardsSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SummaryMiniCard(
            label = "PAID",
            amount = "$0.00",
            colorAccent = SuccessColor,
            modifier = Modifier.weight(1f)
        )
        SummaryMiniCard(
            label = "PENDING",
            amount = "$0.00",
            colorAccent = WarningColor,
            modifier = Modifier.weight(1f)
        )
        SummaryMiniCard(
            label = "OVERDUE",
            amount = "$0.00",
            colorAccent = ErrorColor,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun SummaryMiniCard(
    label: String,
    amount: String,
    colorAccent: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.border(1.dp, BorderColor, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Colored status indicator dot
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(colorAccent)
                )
                Text(
                    text = label,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = SecondaryText,
                    letterSpacing = 0.5.sp
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = amount,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MainText
            )
        }
    }
}

@Composable
fun QuickActionsSection(
    onNewInvoice: () -> Unit,
    onAddClient: () -> Unit,
    onAddExpense: () -> Unit,
    onScanReceipt: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "QUICK ACTIONS",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = SecondaryText,
            letterSpacing = 1.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            QuickActionButton(
                label = "New Invoice",
                icon = Icons.Outlined.Description,
                iconBg = Primary.copy(alpha = 0.1f),
                iconColor = Primary,
                onClick = onNewInvoice
            )
            QuickActionButton(
                label = "Add Client",
                icon = Icons.Outlined.PersonAdd,
                iconBg = SuccessColor.copy(alpha = 0.1f),
                iconColor = SuccessColor,
                onClick = onAddClient
            )
            QuickActionButton(
                label = "Add Expense",
                icon = Icons.AutoMirrored.Outlined.ReceiptLong,
                iconBg = ErrorColor.copy(alpha = 0.1f),
                iconColor = ErrorColor,
                onClick = onAddExpense
            )
            QuickActionButton(
                label = "Scan Receipt",
                icon = Icons.Outlined.QrCodeScanner,
                iconBg = WarningColor.copy(alpha = 0.1f),
                iconColor = WarningColor,
                onClick = onScanReceipt
            )
        }
    }
}

@Composable
fun QuickActionButton(
    label: String,
    icon: ImageVector,
    iconBg: Color,
    iconColor: Color,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(80.dp)
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(iconBg),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = iconColor,
                modifier = Modifier.size(22.dp)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = MainText,
            textAlign = TextAlign.Center,
            lineHeight = 14.sp
        )
    }
}

@Composable
fun RecentInvoicesSection(onCreateInvoice: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "RECENT INVOICES",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = SecondaryText,
                letterSpacing = 1.sp
            )
            Text(
                text = "See All",
                fontSize = 12.sp,
                color = Primary,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { /* Navigate to Invoices */ }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Beautiful Premium Empty State Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, BorderColor, RoundedCornerShape(16.dp))
                .testTag("recent_invoices_empty_state"),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Dashed decoration representing empty folder/invoices
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .drawBehind {
                            val w = size.width
                            val h = size.height
                            // Draw light grey rounded dashed rect
                            val pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 8f), 0f)
                            drawRoundRect(
                                color = BorderColor,
                                size = size,
                                cornerRadius = androidx.compose.ui.geometry.CornerRadius(16f, 16f),
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
                        tint = Primary.copy(alpha = 0.5f),
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "No invoices yet",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = MainText
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Create your first professional invoice and start getting paid.",
                    fontSize = 13.sp,
                    color = SecondaryText,
                    textAlign = TextAlign.Center,
                    lineHeight = 18.sp,
                    modifier = Modifier.padding(horizontal = 12.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onCreateInvoice,
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
                    modifier = Modifier.testTag("dashboard_create_invoice_button")
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Create Invoice",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
