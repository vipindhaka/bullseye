package com.example.kodeco.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kodeco.R

@Composable
fun GameDetail(
    modifier:Modifier=Modifier,totalScore:Int,gameRound:Int,
    onStartOver:()->Unit,
    onNavigateToAbout:()->Unit

){
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        FilledIconButton(onClick = {
            onStartOver()
        },
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            ),
            modifier = Modifier.size(50.dp)

        ) {
            Icon(Icons.Filled.Refresh,
                contentDescription = stringResource(id = R.string.start_over)
                )
        }
        GameInfo(label = stringResource(R.string.score_label),totalScore)
        GameInfo(label = stringResource(R.string.current_round_label), value = gameRound)
        FilledIconButton(onClick = {
                                   onNavigateToAbout()
        },
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            ),
            modifier = Modifier.size(50.dp)
            
        ) {
            Icon(imageVector = Icons.Filled.Info, contentDescription = stringResource(id = R.string.info))
        }
    }
}

@Composable
fun GameInfo(label:String,value:Int=0){
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 32.dp)

    ){
        Text(text = label)
        Text(text = value.toString(),
        style=MaterialTheme.typography.labelLarge.copy(fontSize = 20.sp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GameDetailPreview(){
    GameDetail(totalScore = 10, gameRound = 1, onStartOver = {}, onNavigateToAbout = {})
}