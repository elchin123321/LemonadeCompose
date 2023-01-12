package com.ei.android.lemonadecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ei.android.lemonadecompose.ui.theme.LemonadeComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeComposeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeWithTextAndImage() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (currentStep) {
            1 -> LemonTextAndImage(
                textLabelResourceId = R.string.lemon_tree_text,
                drawableResourceId = R.drawable.lemon_tree,
                contentDescriptionId = R.string.lemon_tree,
                onImageClick = {
                    currentStep = 2
                    squeezeCount = (2..4).random()
                }
            )

            2 -> LemonTextAndImage(
                textLabelResourceId = R.string.squeeze_text,
                drawableResourceId = R.drawable.lemon_squeeze,
                contentDescriptionId = R.string.lemon,
                onImageClick = {
                    squeezeCount--
                    if (squeezeCount == 0) {
                        currentStep = 3
                    }
                }
            )
            3 -> LemonTextAndImage(
                textLabelResourceId = R.string.drink_text,
                drawableResourceId = R.drawable.lemon_drink,
                contentDescriptionId = R.string.drink_text,
                onImageClick = {
                    currentStep = 4
                }
            )
            4 -> LemonTextAndImage(
                textLabelResourceId = R.string.start_again_text,
                drawableResourceId = R.drawable.lemon_restart,
                contentDescriptionId = R.string.empty_glass,
                onImageClick = {
                    currentStep = 1
                }
            )
        }
    }

}


@Composable
fun LemonTextAndImage(
    textLabelResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = stringResource(id = textLabelResourceId))
        Image(
            painter = painterResource(id = drawableResourceId),
            contentDescription = stringResource(id = contentDescriptionId),
            modifier = Modifier
                .clickable(onClick = onImageClick)
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun LemonadeApp() {
    LemonadeWithTextAndImage()
}

