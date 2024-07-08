package com.example.kodeco.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.kodeco.R

@Composable
fun ResultDialog(
    hideDialog: () -> Unit,
    sliderValue:Int,
    points:Int,
    setUpNextRound:()->Unit,
    dialogTitle:Int,
    modifer: Modifier = Modifier
) {
    AlertDialog(onDismissRequest = {
        hideDialog()
        setUpNextRound()
    }, confirmButton = {
        TextButton(onClick = {
            hideDialog()
            setUpNextRound()
        }) {
            Text(text = stringResource(R.string.result_dialog_button_text))
        }

    },
        title = { Text(text = stringResource(dialogTitle)) },
        text = { Text(text = stringResource(R.string.result_dialog_message,sliderValue,points)) }
    )
}