package com.akardas16.lazycardstacksample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akardas16.lazycardstacksample.ui.theme.LazyCardStackSampleTheme
import com.haroncode.lazycardstack.LazyCardStack
import com.haroncode.lazycardstack.items
import com.haroncode.lazycardstack.rememberLazyCardStackState
import com.haroncode.lazycardstack.swiper.SwipeDirection
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyCardStackSampleTheme {

                val cardStackState = rememberLazyCardStackState()
                val listModels by remember {
                    mutableStateOf(
                        listOf(
                            "https://hotpot.ai/images/site/ai/image_generator/art_maker/teaser_400.jpg",
                            "https://img.freepik.com/premium-photo/art-cat_919243-1734.jpg",
                            "https://images.nightcafe.studio/jobs/Fo2rexVOyZVuFiPqyVFb/Fo2rexVOyZVuFiPqyVFb--1--dt2sb_2x.jpg?tr=w-1600,c-at_max",
                            "https://hotpot.ai/images/site/ai/image_generator/art_maker/teaser_400.jpg",
                            "https://img.freepik.com/premium-photo/art-cat_919243-1734.jpg",
                            "https://images.nightcafe.studio/jobs/Fo2rexVOyZVuFiPqyVFb/Fo2rexVOyZVuFiPqyVFb--1--dt2sb_2x.jpg?tr=w-1600,c-at_max"
                        )
                    )
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = Color.White
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        LazyCardStack(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.75f)
                                .padding(horizontal = 16.dp),
                            onSwipedItem = { i, direction ->


                            },
                            state = cardStackState
                        ) {
                            items(
                                items = listModels,
                                key = { it.hashCode() }
                            ) { item ->

                                SwipeItem(modifier = Modifier.fillMaxSize(), url = item)

                            }

                            item(
                                key = { "loading" }
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxSize(),
                                    text = "Items finished. End of List",
                                    color = Color.White
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}


@Composable
private fun SwipeItem(
    modifier: Modifier,
    url: String,
) {

    Card(modifier, shape = RoundedCornerShape(24.dp)) {
        Box {
            CoilImage(
                modifier = Modifier
                    .fillMaxSize()
                   ,
                imageModel = { url }, // loading a network image using an URL.
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center
                )
            )

        }
    }
}

