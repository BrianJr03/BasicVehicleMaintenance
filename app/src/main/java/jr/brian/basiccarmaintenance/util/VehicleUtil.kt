package jr.brian.basiccarmaintenance.util

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jr.brian.basiccarmaintenance.model.MyDataStore
import jr.brian.basiccarmaintenance.model.VehicleItem
import jr.brian.basiccarmaintenance.view.ui.theme.*
import jr.brian.basiccarmaintence.R
import kotlinx.coroutines.launch

@Composable
fun CurrentVehicle(
    defaultVehicle: String,
    dataStore: MyDataStore,
    color: Color = LightRed
) {
    val currentVehicle =
        dataStore.getCurrentVehicle.collectAsState("")
    val isShowingDialog = remember { mutableStateOf(false) }
    CurrentVehicleDialog(isShowing = isShowingDialog, dataStore = dataStore)
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = currentVehicle.value ?: defaultVehicle,
            color = TextWhite,
            style = MaterialTheme.typography.h2
        )
        Box(
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_settings),
                contentDescription = "Selected Vehicle",
                tint = Color.White,
                modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        isShowingDialog.value = true
                    }
            )
        }
    }
}


@Composable
fun VehicleItemsSection(vehicleItems: List<VehicleItem>, dataStore: MyDataStore) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Items",
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(15.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(vehicleItems.size) {
                VehicleItem(vehicleItem = vehicleItems[it], dataStore = dataStore)
            }
        }
    }
}

@Composable
private fun VehicleItem(
    vehicleItem: VehicleItem,
    dataStore: MyDataStore
) {
    val isShowingDialog = remember { mutableStateOf(false) }
    BoxWithConstraints(
        modifier = Modifier
            .padding(8.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(vehicleItem.darkColorStr.toULong()))
            .clickable { isShowingDialog.value = true }
    ) {
        InfoDialog(isShowing = isShowingDialog, dataStore = dataStore)

        val width = constraints.maxWidth
        val height = constraints.maxHeight

        // Medium Color Path
        val medColorPath1 = Offset(0f, -(height * 0.3f))
        val medColorPath2 = Offset(0.1f, height * -0.35f)
        val medColorPath3 = Offset(-1f, height * 0.05f)
        val medColorPath4 = Offset(0f, height * 0.7f)
        val medColorPath5 = Offset(0.4f, height.toFloat())

        val medColorPath = Path().apply {
            moveTo(medColorPath1.x, medColorPath1.y)
            standardQuadFromTo(medColorPath1, medColorPath2)
            standardQuadFromTo(medColorPath2, medColorPath3)
            standardQuadFromTo(medColorPath3, medColorPath4)
            standardQuadFromTo(medColorPath4, medColorPath5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        // Light colored path
        val lightPoint1 = Offset(0f, -(height * 0.35f))
        val lightPoint2 = Offset(width * -0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 1f, height * 0.97f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 0.4f, height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standardQuadFromTo(lightPoint1, lightPoint2)
            standardQuadFromTo(lightPoint2, lightPoint3)
            standardQuadFromTo(lightPoint3, lightPoint4)
            standardQuadFromTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(path = medColorPath, color = Color(vehicleItem.mediumColorStr.toULong()))
            drawPath(path = lightColoredPath, color = Color(vehicleItem.lightColorStr.toULong()))
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
        ) {
            Text(
                text = vehicleItem.title,
                color = DeepBlue,
                style = MaterialTheme.typography.h2,
                lineHeight = 26.sp,
            )
        }

        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CircularProgressBar(percentage = vehicleItem.usagePercentage)
                Spacer(modifier = Modifier.width(65.dp))
                if (vehicleItem.usagePercentage > .90f) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_error_24),
                        contentDescription = "Usage Warning",
                        tint = DeepBlue
                    )
                }
            }
        }
    }
}

@Suppress("unused")
@Composable
private fun VehicleSection(
    vehicles: List<String>,
    dataStore: MyDataStore
) {
    val currentVehicle =
        dataStore.getCurrentVehicle.collectAsState("")
    val scope = rememberCoroutineScope()
    var selectedVehicleIndex by remember {
        mutableStateOf(0)
    }
    LazyRow {
        items(vehicles.size) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedVehicleIndex = it
                        scope.launch {
                            dataStore.saveCurrentVehicle(vehicles[it])
                        }
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (vehicles.indexOf(currentVehicle.value) == it) ButtonBlue
                        else DarkerButtonBlue
                    )
                    .padding(15.dp)
            ) {
                Text(text = vehicles[it], color = TextWhite)
            }
        }
    }
}