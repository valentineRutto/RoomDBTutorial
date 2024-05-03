package com.valentinerutto.roomdbtutorial.ui

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.valentinerutto.roomdbtutorial.data.local.PickuplineEntity
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.min

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainView(
    modifier: Modifier = Modifier, lines: List<PickuplineEntity>
) {

    Carousel(modifier = modifier.fillMaxSize(), lines = lines)

}
//https://www.sinasamaki.com/setting-up-viewpager-in-jetpack-compose/
//https://gist.github.com/sinasamaki/55693586ea97e0e425c22230c18a4784?ref=sinasamaki.com
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Carousel(modifier: Modifier, lines: List<PickuplineEntity>) {
    val pagerState = rememberPagerState(pageCount = { lines.size })

    val pageScale by remember {
        derivedStateOf {
            1f - (pagerState.currentPageOffsetFraction.absoluteValue) * .3f
        }
    }

    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val offsetFromStart = pagerState.getOffsetFractionForPage(0).absoluteValue
        Box(modifier = Modifier
            .aspectRatio(1f)
            .offset { IntOffset(0, 150.dp.roundToPx()) }
            .scale(scaleX = .6f, scaleY = .24f)
            .scale(pageScale)
            .rotate(offsetFromStart * 90f)
            .blur(
                radius = 110.dp,
                edgeTreatment = BlurredEdgeTreatment.Unbounded,
            )
            .background(Color.Black.copy(alpha = .5f)))

        Box(modifier.fillMaxWidth()) {


            HorizontalPager(
                state = pagerState,
                pageSpacing = 16.dp,
                flingBehavior = PagerDefaults.flingBehavior(
                    state = pagerState, pagerSnapDistance = PagerSnapDistance.atMost(0)
                ),
                contentPadding = PaddingValues(horizontal = 32.dp),
                modifier = Modifier
                    .scale(1f, scaleY = pageScale)
                    .aspectRatio(1f),
            ) { index ->
                Box(modifier = Modifier
                    .aspectRatio(1f)
                    .graphicsLayer {
                        val pageOffset = pagerState.getOffsetFractionForPage(index)
                        val offScreenRight = pageOffset < 0f
                        val deg = 105f
                        val interpolated = FastOutLinearInEasing.transform(pageOffset.absoluteValue)
                        rotationY = min(interpolated * if (offScreenRight) deg else -deg, 90f)

                        transformOrigin = TransformOrigin(
                            pivotFractionX = if (offScreenRight) 0f else 1f, pivotFractionY = .5f
                        )
                    }
                    .drawWithContent {
                        val pageOffset = pagerState.getOffsetFractionForPage(index)

                        this.drawContent()
                        drawRect(
                            Color.Black.copy(
                                (pageOffset.absoluteValue * .7f)
                            )
                        )
                    }
                    .background(Color.LightGray), contentAlignment = Alignment.Center) {
                    LineItemComposable(
                        modifier = modifier.align(Alignment.Center), entity = lines[index]
                    )
                }
            }
            PageIndicators(pagerState = pagerState, Modifier.align(Alignment.BottomCenter))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageIndicators(pagerState: PagerState, modifier: Modifier) {
    val scope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .offset(y = -(16).dp)
            .fillMaxWidth(0.5f)
            .clip(RoundedCornerShape(100))
            .background(darkColorScheme().secondary)
            .padding(6.dp)
    ) {
        IconButton(onClick = {
            scope.launch {
                pagerState.animateScrollToPage(
                    pagerState.currentPage - 1
                )
            }
        }, modifier = Modifier.align(Alignment.CenterStart)) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Go Back"
            )
        }
        Text(text = " ${pagerState.currentPage}", modifier = Modifier.align(Alignment.Center))
        IconButton(onClick = {
            scope.launch {
                pagerState.animateScrollToPage(
                    pagerState.currentPage + 1
                )
            }
        }, modifier = Modifier.align(Alignment.CenterEnd)) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Go Forward"
            )
        }
    }

}


@Preview(name = "MainView")
@Composable
private fun PreviewMainView() {
    //MainView()
}