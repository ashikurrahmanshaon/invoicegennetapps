package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Receipt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.CreateBottomSheet
import com.example.ui.theme.*

enum class TabItem {
    Home,
    Invoices,
    Clients,
    More
}

@Composable
fun MainAppShell() {
    var currentTab by remember { mutableStateOf(TabItem.Home) }
    var showCreateSheet by remember { mutableStateOf(false) }

    // Centered responsive container
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .testTag("main_app_shell_container"),
        contentAlignment = Alignment.TopCenter
    ) {
        // Limit width to 500.dp to optimize for 390px-style mobile experience even on tablet
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .widthIn(max = 500.dp)
                .background(BackgroundColor)
        ) {
            Scaffold(
                bottomBar = {
                    CustomBottomNavBar(
                        currentTab = currentTab,
                        onTabSelected = { currentTab = it },
                        onCreateClick = { showCreateSheet = true }
                    )
                },
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    when (currentTab) {
                        TabItem.Home -> {
                            DashboardScreen(
                                onCreateInvoiceClick = { showCreateSheet = true },
                                onAddClientClick = { showCreateSheet = true },
                                onAddExpenseClick = { showCreateSheet = true },
                                onScanReceiptClick = { showCreateSheet = true }
                            )
                        }
                        TabItem.Invoices -> {
                            InvoicesScreen(
                                onCreateInvoiceClick = { showCreateSheet = true }
                            )
                        }
                        TabItem.Clients -> {
                            ClientsScreen(
                                onAddClientClick = { showCreateSheet = true }
                            )
                        }
                        TabItem.More -> {
                            MoreScreen()
                        }
                    }
                }
            }

            // Create Premium Bottom Sheet
            if (showCreateSheet) {
                CreateBottomSheet(
                    onDismissRequest = { showCreateSheet = false },
                    onOptionSelected = { option ->
                        // Real callbacks for when creation flow features are ready in next steps
                    }
                )
            }
        }
    }
}

@Composable
fun CustomBottomNavBar(
    currentTab: TabItem,
    onTabSelected: (TabItem) -> Unit,
    onCreateClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(16.dp, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .border(1.dp, BorderColor, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .testTag("custom_bottom_nav_bar"),
        color = SurfaceColor,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.navigationBars) // safe navigation space
                .padding(horizontal = 8.dp, vertical = 10.dp)
                .height(64.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            // Tab 1: Home
            NavBarItem(
                label = "Home",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home,
                isSelected = currentTab == TabItem.Home,
                onClick = { onTabSelected(TabItem.Home) },
                modifier = Modifier.weight(1f).testTag("nav_tab_home")
            )

            // Tab 2: Invoices
            NavBarItem(
                label = "Invoices",
                selectedIcon = Icons.Filled.Receipt,
                unselectedIcon = Icons.Outlined.Receipt,
                isSelected = currentTab == TabItem.Invoices,
                onClick = { onTabSelected(TabItem.Invoices) },
                modifier = Modifier.weight(1f).testTag("nav_tab_invoices")
            )

            // Tab 3: Prominent Center "+" Button
            Box(
                modifier = Modifier
                    .weight(1.2f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .shadow(4.dp, CircleShape)
                        .clip(CircleShape)
                        .background(Primary)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(color = Color.White.copy(alpha = 0.2f)),
                            onClick = onCreateClick
                        )
                        .testTag("nav_center_create_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = "Create menu",
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            // Tab 4: Clients
            NavBarItem(
                label = "Clients",
                selectedIcon = Icons.Filled.People,
                unselectedIcon = Icons.Outlined.People,
                isSelected = currentTab == TabItem.Clients,
                onClick = { onTabSelected(TabItem.Clients) },
                modifier = Modifier.weight(1f).testTag("nav_tab_clients")
            )

            // Tab 5: More
            NavBarItem(
                label = "More",
                selectedIcon = Icons.Filled.MoreHoriz,
                unselectedIcon = Icons.Outlined.MoreHoriz,
                isSelected = currentTab == TabItem.More,
                onClick = { onTabSelected(TabItem.More) },
                modifier = Modifier.weight(1f).testTag("nav_tab_more")
            )
        }
    }
}

@Composable
fun RowScope.NavBarItem(
    label: String,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(color = Primary.copy(alpha = 0.08f)),
                onClick = onClick
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = if (isSelected) selectedIcon else unselectedIcon,
            contentDescription = label,
            tint = if (isSelected) Primary else SecondaryText,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) Primary else SecondaryText
        )
    }
}
