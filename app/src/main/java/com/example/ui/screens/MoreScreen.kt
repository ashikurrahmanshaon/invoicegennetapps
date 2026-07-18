package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun MoreScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
            .testTag("more_screen")
    ) {
        // Title
        Text(
            text = "Settings",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MainText,
            letterSpacing = (-0.5).sp,
            modifier = Modifier.padding(top = 16.dp, bottom = 24.dp)
        )

        // General settings list grouped together
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, BorderColor, RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceColor)
        ) {
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                SettingsItem(
                    title = "Business Profile",
                    description = "Manage company settings, tax ID, and address",
                    icon = Icons.Outlined.BusinessCenter,
                    iconTint = Primary
                )
                HorizontalDivider(color = BorderColor, modifier = Modifier.padding(horizontal = 16.dp))
                SettingsItem(
                    title = "Invoice Design",
                    description = "Personalize colors, custom logos, and templates",
                    icon = Icons.Outlined.Palette,
                    iconTint = SuccessColor
                )
                HorizontalDivider(color = BorderColor, modifier = Modifier.padding(horizontal = 16.dp))
                SettingsItem(
                    title = "Payment Methods",
                    description = "Stripe, bank accounts, and credit card setup",
                    icon = Icons.Outlined.Payment,
                    iconTint = WarningColor
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Advanced settings list
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, BorderColor, RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = SurfaceColor)
        ) {
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                SettingsItem(
                    title = "Tax & Currencies",
                    description = "Set default tax rates, formats, and regional details",
                    icon = Icons.Outlined.Language,
                    iconTint = PrimaryDark
                )
                HorizontalDivider(color = BorderColor, modifier = Modifier.padding(horizontal = 16.dp))
                SettingsItem(
                    title = "Security & App Lock",
                    description = "Enable biometric face ID or 4-digit passcode lock",
                    icon = Icons.Outlined.Lock,
                    iconTint = ErrorColor
                )
                HorizontalDivider(color = BorderColor, modifier = Modifier.padding(horizontal = 16.dp))
                SettingsItem(
                    title = "Help & Support",
                    description = "Read comprehensive guides or start a live support ticket",
                    icon = Icons.Outlined.HelpOutline,
                    iconTint = SecondaryText
                )
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Application version branding info
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Invoice-Gen v1.0.0",
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = MainText.copy(alpha = 0.6f)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Premium Business Software.\n© 2026 Invoice-Gen Inc. All rights reserved.",
                fontSize = 11.sp,
                color = SecondaryText,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(120.dp)) // space for navigation bar
    }
}

@Composable
fun SettingsItem(
    title: String,
    description: String,
    icon: ImageVector,
    iconTint: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle settings click */ }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon circular background
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(iconTint.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = iconTint,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = MainText
            )
            Text(
                text = description,
                fontSize = 12.sp,
                color = SecondaryText,
                lineHeight = 16.sp
            )
        }

        Icon(
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = BorderColor,
            modifier = Modifier.size(20.dp)
        )
    }
}
