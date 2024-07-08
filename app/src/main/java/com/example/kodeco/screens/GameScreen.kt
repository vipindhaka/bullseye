package com.example.kodeco.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kodeco.R
import com.example.kodeco.components.GameDetail
import com.example.kodeco.components.GamePrompt
import com.example.kodeco.components.ResultDialog
import com.example.kodeco.components.TargetSlider
import com.example.kodeco.ui.theme.KodecoTheme
import kotlin.math.abs
import kotlin.random.Random

@Composable
fun GameScreen(onNavigateToAbout:()->Unit){
    fun newTargetValue()= Random.nextInt(1,100)
    var alertIsVisible by rememberSaveable {
        mutableStateOf(value = false)
    }
    var sliderValue by rememberSaveable {
        mutableStateOf(0.5f)
    }
    var targetValue by rememberSaveable {
        mutableStateOf(newTargetValue())
    }
    var totalscore by rememberSaveable {
        mutableStateOf(0)
    }

    var gameRound by rememberSaveable {
        mutableStateOf(1)
    }
    val sliderToInt=(sliderValue*100).toInt()
    fun calculateDifference()=abs(sliderToInt-targetValue);



    fun startNewRound(){
        totalscore=0
        gameRound=1
        sliderValue=0.5f
        targetValue=newTargetValue()
    }

    fun alertTitle():Int{
        val difference=calculateDifference()
        val title:Int= when {
            difference==0 -> {
                R.string.alert_title1
            }
            difference<=5 -> {
                R.string.alert_title2
            }
            difference<=10 -> {
                R.string.alert_title3
            }
            else -> {
                R.string.alert_title4
            }
        }
        return title;
    }



    fun pointsForCurrentRound():Int{
        val max=100;
        val diff= calculateDifference()
        val additionalPoints:Int= when (diff) {
            0 -> {
                100
            }
            1 -> {
                50
            }
            else -> {
                0
            }
        }
        return max-diff+additionalPoints;
    }
    Box {
        Image(
            modifier=Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.background), contentDescription = stringResource(
                R.string.background_image
            ),
            contentScale = ContentScale.Crop

        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Spacer(modifier = Modifier.weight(.5f))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.weight(9f)
            ) {
                GamePrompt(targetValue = targetValue.toString())
                TargetSlider(
                    onValueChange = {
                        sliderValue = it;
                    },
                    value = sliderValue
                )
                Button(
                    onClick = {
                        alertIsVisible = true
                        totalscore += pointsForCurrentRound()

                    },
                    shape = MaterialTheme.shapes.medium,
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text(stringResource(R.string.hit_me_button_text))
                }
                GameDetail(
                    modifier = Modifier.fillMaxWidth(),
                    totalScore = totalscore,
                    gameRound = gameRound,
                    onStartOver = {
                        startNewRound()
                    },
                    onNavigateToAbout = onNavigateToAbout

                )

            }
            Spacer(modifier = Modifier.weight(.5f))
            if (alertIsVisible) {
                ResultDialog(
                    hideDialog = {
                        alertIsVisible = false;
                    },
                    sliderValue = sliderToInt,
                    points = pointsForCurrentRound(),
                    setUpNextRound = {
                        gameRound++;
                        targetValue = newTargetValue()
                    },
                    dialogTitle = alertTitle()
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.AUTOMOTIVE_1024p, widthDp = 864, heightDp = 432)
@Composable
fun GameScreenPreview() {
    KodecoTheme {
        GameScreen(onNavigateToAbout = {})
    }
}