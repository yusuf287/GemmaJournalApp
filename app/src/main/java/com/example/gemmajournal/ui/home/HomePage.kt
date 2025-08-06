package com.example.gemmajournal.ui.home

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Define some colors for the old book look
val OldBookCoverBrown = Color(0xFF5D4037) // A dark, rich brown
val OldBookPageCream = Color(0xFFF5EFE0)  // A slightly aged cream/beige
val OldBookGoldAccent = Color(0xFFB08D57) // A muted gold for lettering

@Composable
fun HomePage(onNavigateToNewEntry: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite_transition")
    val animatedGlowColor by infiniteTransition.animateColor(
        initialValue = OldBookGoldAccent,
        targetValue = OldBookGoldAccent.copy(alpha = 0.5f),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "animated_glow_color"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(OldBookPageCream.copy(alpha = 0.8f)) // Background like aged paper
            .padding(32.dp), // Generous padding to center the book
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.85f) // Book width
                .aspectRatio(0.7f)  // Typical book cover aspect ratio (height > width)
                .clickable { onNavigateToNewEntry() },
            shape = RoundedCornerShape(topStart = 8.dp, topEnd = 4.dp, bottomEnd = 4.dp, bottomStart = 8.dp), // Subtle rounding for a cover
            elevation = CardDefaults.cardElevation(defaultElevation = 16.dp, pressedElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = OldBookCoverBrown)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // Decorative border with animation
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp, vertical = 20.dp) // Inset border
                        .border(2.dp, animatedGlowColor, RoundedCornerShape(4.dp)) // Use animated color here
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "My Life",
                        style = MaterialTheme.typography.displaySmall.copy( // Using displaySmall for impact
                            fontFamily = FontFamily.Serif, // Classic Serif font
                            fontWeight = FontWeight.Bold,
                            fontSize = 44.sp, // Larger font size for the title
                            textAlign = TextAlign.Center
                        ),
                        color = OldBookGoldAccent // Title color remains solid
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "A Chronicle Unfolds",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontFamily = FontFamily.Serif,
                            fontSize = 18.sp
                        ),
                        color = OldBookPageCream.copy(alpha = 0.9f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5EFE0) // Preview with a similar background
@Composable
fun HomePagePreview() {
    MaterialTheme { // Ensure your app's theme is applied for consistent previews
        HomePage(onNavigateToNewEntry = {})
    }
}
