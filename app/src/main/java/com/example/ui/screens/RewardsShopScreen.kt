package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.repository.CurriculumCatalog
import com.example.ui.theme.*
import com.example.ui.viewmodel.UiState

@Composable
fun RewardsShopScreen(
    state: UiState,
    onBack: () -> Unit,
    onBuyItem: (itemId: String, cost: Int) -> Unit
) {
    BackHandler { onBack() }
    val isUrdu = state.userProfile.languageCode == "ur"
    val avatars = CurriculumCatalog.sampleAvatars

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SaaSBackground)
    ) {
        // SaaS Header
        Surface(
            color = SaaSSurface,
            tonalElevation = 1.dp,
            border = androidx.compose.foundation.BorderStroke(1.dp, SaaSBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("shop_back_btn")) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = SaaSTextPrimary
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Column {
                        Text(
                            text = if (isUrdu) "انعامات اور اوتار مارکیٹ" else "Avatar & Rewards Store",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = SaaSTextPrimary
                        )
                        Text(
                            text = "Redeem stars earned from completed quests",
                            style = MaterialTheme.typography.labelSmall,
                            color = SaaSTextSecondary
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = SaaSSuccessContainer,
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFA7F3D0)),
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("⭐", fontSize = 13.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${state.userProfile.coins} Stars",
                            fontWeight = FontWeight.Bold,
                            color = SaaSOnSuccessContainer,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }

        // Grid of Avatars
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(avatars) { avatar ->
                val isEquipped = state.userProfile.avatarId == avatar.id
                val canAfford = state.userProfile.coins >= avatar.costCoins

                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = SaaSSurface),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        if (isEquipped) SaaSPrimary else SaaSBorder
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(SaaSPrimaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = when (avatar.stageBracket) {
                                    "prep" -> "🧒"
                                    "primary" -> "🎒"
                                    "middle" -> "⚡"
                                    "matric" -> "🧑‍🎓"
                                    else -> "💻"
                                },
                                fontSize = 28.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = avatar.name,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = SaaSTextPrimary
                        )

                        Text(
                            text = avatar.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = SaaSTextSecondary,
                            maxLines = 2,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        if (isEquipped) {
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = SaaSSuccessContainer,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(vertical = 6.dp),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(Icons.Default.Check, contentDescription = null, tint = SaaSSuccess, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Equipped", color = SaaSOnSuccessContainer, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        } else if (avatar.costCoins == 0) {
                            OutlinedButton(
                                onClick = { onBuyItem(avatar.id, 0) },
                                shape = RoundedCornerShape(6.dp),
                                modifier = Modifier.fillMaxWidth().testTag("equip_${avatar.id}")
                            ) {
                                Text("Equip", fontSize = 12.sp, color = SaaSPrimary)
                            }
                        } else {
                            Button(
                                onClick = { onBuyItem(avatar.id, avatar.costCoins) },
                                enabled = canAfford,
                                colors = ButtonDefaults.buttonColors(containerColor = if (canAfford) SaaSPrimary else Color.LightGray),
                                shape = RoundedCornerShape(6.dp),
                                modifier = Modifier.fillMaxWidth().testTag("buy_${avatar.id}")
                            ) {
                                Text("⭐ ${avatar.costCoins}", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}
