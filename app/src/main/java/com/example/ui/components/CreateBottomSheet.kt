package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Inventory
import androidx.compose.material.icons.outlined.PersonAdd
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateBottomSheet(
    onDismissRequest: () -> Unit,
    onOptionSelected: (String) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        containerColor = SurfaceColor,
        dragHandle = { BottomSheetDefaults.DragHandle(color = BorderColor) },
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        tonalElevation = 8.dp,
        modifier = Modifier.testTag("create_bottom_sheet")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(start = 24.dp, end = 24.dp, bottom = 40.dp)
        ) {
            // Header
            Text(
                text = "Create New",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MainText
            )
            Text(
                text = "Select an action to continue",
                fontSize = 14.sp,
                color = SecondaryText,
                modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
            )

            // Sheet Options
            BottomSheetOptionItem(
                title = "New Invoice",
                description = "Generate a professional invoice in seconds",
                icon = Icons.Outlined.Description,
                iconBgColor = Primary.copy(alpha = 0.1f),
                iconColor = Primary,
                onClick = {
                    onOptionSelected("New Invoice")
                    onDismissRequest()
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            BottomSheetOptionItem(
                title = "Add Client",
                description = "Save client details to directory for quick lookup",
                icon = Icons.Outlined.PersonAdd,
                iconBgColor = SuccessColor.copy(alpha = 0.1f),
                iconColor = SuccessColor,
                onClick = {
                    onOptionSelected("Add Client")
                    onDismissRequest()
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            BottomSheetOptionItem(
                title = "Add Product or Service",
                description = "Save catalog items with pricing and unit types",
                icon = Icons.Outlined.Inventory,
                iconBgColor = WarningColor.copy(alpha = 0.1f),
                iconColor = WarningColor,
                onClick = {
                    onOptionSelected("Add Product or Service")
                    onDismissRequest()
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            BottomSheetOptionItem(
                title = "Add Expense",
                description = "Record receipt or expense for tax deductible reporting",
                icon = Icons.AutoMirrored.Outlined.ReceiptLong,
                iconBgColor = ErrorColor.copy(alpha = 0.1f),
                iconColor = ErrorColor,
                onClick = {
                    onOptionSelected("Add Expense")
                    onDismissRequest()
                }
            )
        }
    }
}

@Composable
fun BottomSheetOptionItem(
    title: String,
    description: String,
    icon: ImageVector,
    iconBgColor: Color,
    iconColor: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(BackgroundColor)
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon Circle
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(iconBgColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = iconColor,
                modifier = Modifier.size(22.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Content
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
                color = SecondaryText
            )
        }

        // Chevron
        Icon(
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = SecondaryText,
            modifier = Modifier.size(20.dp)
        )
    }
}
