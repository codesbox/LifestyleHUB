package ru.yasdev.details.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.yasdev.details.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEventDetailsAlertDialog(
    openDialog: MutableState<Boolean>, title: MutableState<String?>, date: MutableState<String?>
) {

    var newTitle by remember {
        mutableStateOf("")
    }
    var newDate by remember {
        mutableStateOf("")
    }

    if (openDialog.value) {

        AlertDialog(onDismissRequest = {
            openDialog.value = false
        }) {
            Surface(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation
            ) {

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = stringResource(id = R.string.add_dialog),
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    OutlinedTextField(value = newTitle,
                        onValueChange = { newTitle = it },
                        label = { Text(text = stringResource(id = R.string.title)) })
                    Spacer(modifier = Modifier.height(24.dp))
                    OutlinedTextField(value = newDate,
                        onValueChange = { newDate = it },
                        label = { Text(text = stringResource(id = R.string.date)) })
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        TextButton(onClick = {
                            openDialog.value = false
                        }) {
                            Text(stringResource(id = R.string.cancel))
                        }
                        TextButton(onClick = {
                            title.value = newTitle
                            date.value = newDate
                            openDialog.value = false
                        }) {
                            Text(stringResource(id = R.string.add_dialog))
                        }
                    }
                }
            }
        }
    }
}