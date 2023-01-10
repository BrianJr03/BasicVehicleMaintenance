package jr.brian.basiccarmaintenance.util

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jr.brian.basiccarmaintenance.model.MyDataStore
import jr.brian.basiccarmaintenance.view.ui.theme.LightRed
import jr.brian.basiccarmaintenance.view.ui.theme.TextWhite
import kotlinx.coroutines.launch

@Composable
fun InfoDialog(isShowing: MutableState<Boolean>, dataStore: MyDataStore) {
    var avg by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    ShowDialog(
        title = "How many miles do you average a day?",
        content = {
            Column {
                Text(
                    "This will be used to calculate usage rates",
                    fontSize = 16.sp,
                    color = TextWhite
                )
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    value = avg,
                    onValueChange = { avg = it },
                    label = { Text(text = "Enter daily mileage average", color = TextWhite) },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Daily mileage average",
                            tint = TextWhite
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp, top = 10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = TextWhite,
                        focusedBorderColor = LightRed,
                        unfocusedBorderColor = TextWhite,
                        focusedLabelColor = LightRed,
                        cursorColor = TextWhite
                    )
                )
            }
        },
        confirmButton = {
            Button(
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = LightRed
                ),
                onClick = {
                    isShowing.value = false
                    scope.launch {
                        dataStore.saveDailyMileageAverage(avg.toFloat())
                    }
                }) {
                Text(text = "OK", color = Color.White)
            }
        },
        dismissButton = {
            Button(
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = LightRed
                ),
                onClick = {
                    isShowing.value = false
                }) {
                Text(text = "BACK", color = Color.White)
            }
        }, isShowing = isShowing
    )
}

@Composable
fun CurrentVehicleDialog(isShowing: MutableState<Boolean>, dataStore: MyDataStore) {
    var currentVehicle by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    ShowDialog(
        title = "What vehicle do you drive?",
        content = {
            Column {
                Text(
                    "Enter your make and model",
                    fontSize = 16.sp,
                    color = TextWhite
                )
                Spacer(modifier = Modifier.height(15.dp))
                OutlinedTextField(
                    value = currentVehicle,
                    onValueChange = { currentVehicle = it },
                    label = { Text(text = "Vehicle", color = TextWhite) },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "Vehicle name",
                            tint = TextWhite
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp, top = 10.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = TextWhite,
                        focusedBorderColor = LightRed,
                        unfocusedBorderColor = TextWhite,
                        focusedLabelColor = LightRed,
                        cursorColor = TextWhite
                    )
                )
            }
        },
        confirmButton = {
            Button(
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = LightRed
                ),
                onClick = {
                    isShowing.value = false
                    scope.launch {
                        dataStore.saveCurrentVehicle(currentVehicle)
                    }
                }) {
                Text(text = "OK", color = Color.White)
            }
        },
        dismissButton = {
            Button(
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = LightRed
                ),
                onClick = { isShowing.value = false }) {
                Text(text = "BACK", color = Color.White)
            }
        }, isShowing = isShowing
    )
}