package com.valentinerutto.roomdbtutorial.ui

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinerutto.roomdbtutorial.data.local.PickuplineEntity
import com.valentinerutto.roomdbtutorial.random
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainView(
    modifier: Modifier = Modifier.fillMaxWidth(), lines: List<PickuplineEntity>, pageSize: Int
) {
//todo:works fine as long the new selected list is withing the array size of the previous one,
    //todo:find a way to make the page size mutable like live data
    //todo:it breaks when pressing the indicators. with this error
    println("size :init size: ${lines.size}")
    Carousel(modifier = modifier.fillMaxSize(), lines = lines, pageSize)

}

//https://www.sinasamaki.com/setting-up-viewpager-in-jetpack-compose/
//https://gist.github.com/sinasamaki/55693586ea97e0e425c22230c18a4784?ref=sinasamaki.com
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Carousel(modifier: Modifier, lines: List<PickuplineEntity>, pageSize: Int) {

    val pagerState = rememberPagerState(pageCount = { lines.size })

    val pageScale by remember {
        derivedStateOf {
            1f - (pagerState.currentPageOffsetFraction.absoluteValue) * .3f
        }
    }

    val bgColor = Color.Companion.random()
    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {


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

                LineItemComposable(
                    modifier = modifier.align(Alignment.Center), entity = lines[index]
                )

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
            .padding(6.dp)
            .background(Color.Companion.random())
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