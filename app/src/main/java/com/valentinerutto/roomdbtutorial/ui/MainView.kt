package com.valentinerutto.roomdbtutorial.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinerutto.roomdbtutorial.data.local.PickuplineEntity
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainView(
    modifier: Modifier = Modifier, lines: List<PickuplineEntity>
) {
    Carousel(modifier = modifier.fillMaxSize(), lines = lines)

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Carousel(modifier: Modifier, lines: List<PickuplineEntity>) {
    Box(modifier.fillMaxWidth()) {

        val pagerState = rememberPagerState(pageCount = { lines.size })

        HorizontalPager(state = pagerState, pageSize = PageSize.Fill) {
            LineItemComposable(modifier = modifier.align(Alignment.Center), entity = lines[it])
        }
        PageIndicators(pagerState = pagerState, Modifier.align(Alignment.BottomCenter))
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