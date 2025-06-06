package com.stephenelf.gymder.ui.util

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.CollectionInfo
import androidx.compose.ui.semantics.CollectionItemInfo
import androidx.compose.ui.semantics.collectionInfo
import androidx.compose.ui.semantics.collectionItemInfo
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.spartapps.swipeablecards.state.rememberSwipeableCardsState
import com.spartapps.swipeablecards.ui.SwipeableCardDirection
import com.spartapps.swipeablecards.ui.lazy.LazySwipeableCards
import com.spartapps.swipeablecards.ui.lazy.items
import com.stephenelf.gymder.data.model.Gym

@Composable
fun GymSwipableCards(
    data: () -> List<Gym>, handleLike: () -> Unit,
    handleDislike: () -> Unit, modifier: Modifier = Modifier
) {

    val gymList = data()

    val state = rememberSwipeableCardsState(itemCount = { gymList.size }, initialCardIndex = 0)

    LazySwipeableCards(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .offset(y = 8.dp)
            .semantics {
                collectionInfo = CollectionInfo(
                    rowCount = gymList.count(),
                    columnCount = 1
                )
            },
        state = state,
        onSwipe = { profile, direction ->
            when (direction) {
                SwipeableCardDirection.Right -> handleLike()
                SwipeableCardDirection.Left -> handleDislike()
            }
        }
    ) {
        items(gymList) { gym, index, offset ->
            GymCardItem(
                modifier,
                index,
                gym.facility_title,
                gym.community_center,
                gym.address11,
                gym.location,
                offset
            )


        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GymCardItem(
    modifier: Modifier = Modifier,
    index: Int,
    text1: String,
    text2: String,
    text3: String,
    text4: String,
    offset: Offset,
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .semantics {
                collectionItemInfo =
                    CollectionItemInfo(index, 0, 0, 0)
            },
        border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.outline.copy(alpha = 0.6f)),
    ) {
        Box {
            GlideImage(
                model = "https://picsum.photos/200/300?random=${index}",
                contentDescription = "Lorem ipsum gym image ${index}",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomStart
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                ) {
                    Box(
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color.Blue)
                                )
                            )
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Blue)
                    ) {
                        Column(
                            modifier = Modifier.padding(15.dp),
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(
                                text = text1,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                style = MaterialTheme.typography.titleLarge,
                            )
                            Text(
                                text = text2,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                style = MaterialTheme.typography.bodyLarge,
                            )
                            Text(
                                text = text3,
                                fontSize = 14.sp,
                                color = Color.White.copy(alpha = 0.8f),
                                style = MaterialTheme.typography.bodyMedium,
                            )

                            Text(
                                text = text4,
                                fontSize = 15.sp,
                                color = Color.White,
                                style = MaterialTheme.typography.bodySmall,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun GymCardItemPreview() {
    GymCardItem(
        index = 0,
        text1 = "Test Title",
        text2 = "Test Content",
        text3 = "Test Content",
        text4 = "Test Content",
        offset = Offset.Zero
    )
}