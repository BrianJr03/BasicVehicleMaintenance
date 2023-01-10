package jr.brian.basiccarmaintenance.view.ui.pages

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jr.brian.basiccarmaintenance.model.local.BottomMenuContent
import jr.brian.basiccarmaintenance.model.local.VehicleItem
import jr.brian.basiccarmaintenance.standardQuadFromTo
import jr.brian.basiccarmaintenance.util.MyDataStore
import jr.brian.basiccarmaintenance.view.ui.theme.*
import jr.brian.basiccarmaintence.R
import kotlinx.coroutines.launch

@Composable
fun HomePage() {
    val context = LocalContext.current
    val dataStore = MyDataStore(context)
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {
        val vehicles = listOf("Jeep Cherokee '14", "Vestar Mini")
        Column {
            InfoSection()
            VehicleSection(
                vehicles = vehicles,
                dataStore = dataStore
            )
            CurrentVehicle(
                defaultVehicle = vehicles[0],
                dataStore = dataStore
            )
            VehicleItemsSection(
                vehicleItems = listOf(
                    VehicleItem(
                        title = "Motor Oil Usage",
                        circularProgressBar = { CircularProgressBar(percentage = .15f) },
                        lightColor = BlueViolet1,
                        mediumColor = BlueViolet2,
                        darkColor = BlueViolet3
                    ),
                    VehicleItem(
                        title = "Coolant Usage",
                        circularProgressBar = { CircularProgressBar(percentage = .77f) },
                        lightColor = LightGreen1,
                        mediumColor = LightGreen2,
                        darkColor = LightGreen3
                    ),
                    VehicleItem(
                        title = "Brake Fluid Usage",
                        circularProgressBar = { CircularProgressBar(percentage = .25f) },
                        lightColor = OrangeYellow1,
                        mediumColor = OrangeYellow2,
                        darkColor = OrangeYellow3
                    ),
                    VehicleItem(
                        title = "Power Steering Usage",
                        circularProgressBar = { CircularProgressBar(percentage = .2f) },
                        lightColor = Beige1,
                        mediumColor = Beige2,
                        darkColor = Beige3
                    ),
                    VehicleItem(
                        title = "Wipe Blades Usage",
                        circularProgressBar = { CircularProgressBar(percentage = .3f) },
                        lightColor = LightGreen1,
                        mediumColor = LightGreen2,
                        darkColor = LightGreen3
                    ),
                    VehicleItem(
                        title = "Brake Pads Usage",
                        circularProgressBar = { CircularProgressBar(percentage = 1f) },
                        lightColor = OrangeYellow1,
                        mediumColor = OrangeYellow2,
                        darkColor = OrangeYellow3
                    )
                )
            )
        }
        BottomMenu(
            items = listOf(
                BottomMenuContent(
                    title = "Home",
                    R.drawable.ic_home_2
                ),
                BottomMenuContent(
                    title = "Settings",
                    R.drawable.ic_settings
                )
            ), modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun InfoSection() {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Basic Vehicle Maintenance",
                style = MaterialTheme.typography.h2
            )
        }
    }
}

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
                            dataStore.saveCurrentVehicleIndex(vehicles[it])
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

@Composable
private fun CurrentVehicle(
    defaultVehicle: String,
    dataStore: MyDataStore,
    color: Color = LightRed
) {
    val currentVehicle =
        dataStore.getCurrentVehicle.collectAsState("")
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
                modifier = Modifier.size(25.dp)
            )
        }
    }
}

@Composable
private fun VehicleItemsSection(vehicleItems: List<VehicleItem>) {
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
                VehicleItem(vehicleItem = vehicleItems[it])
            }
        }
    }
}

@Composable
private fun VehicleItem(
    vehicleItem: VehicleItem,
) {
    BoxWithConstraints(
        modifier = Modifier
            .padding(8.5.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(10.dp))
            .background(vehicleItem.darkColor)
    ) {
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
            drawPath(path = medColorPath, color = vehicleItem.mediumColor)
            drawPath(path = lightColoredPath, color = vehicleItem.lightColor)
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
            Spacer(modifier = Modifier.height(50.dp))
            vehicleItem.circularProgressBar()
        }
    }
}

@Composable
private fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inActiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inActiveTextColor = inActiveTextColor
            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
private fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inActiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable {
            onItemClick()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeHighlightColor else Color.Transparent)
        ) {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = item.title,
                tint = if (isSelected) activeTextColor else inActiveTextColor,
                modifier = Modifier.size(30.dp)
            )
        }
        Text(
            text = item.title,
            color = if (isSelected) activeTextColor else inActiveTextColor
        )
    }
}

@Composable
fun CircularProgressBar(
    percentage: Float,
    fontSize: TextUnit = 18.sp,
    radius: Dp = 27.dp,
    color: Color = DeepBlue,
    strokeWidth: Dp = 3.dp,
    animationDuration: Int = 1000, // Milliseconds
    animationDelay: Int = 0
) {
    var hasAnimationPlayed by remember {
        mutableStateOf(false)
    }

    val currentPercentage = animateFloatAsState(
        targetValue = if (hasAnimationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        )
    )

    LaunchedEffect(key1 = true) { hasAnimationPlayed = true }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        Canvas(
            modifier = Modifier
                .size(radius * 2f)
                .align(Alignment.BottomCenter)
        ) {
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * currentPercentage.value,
                useCenter = false,
                style = Stroke(
                    width = strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
        Text(
            text = "${(currentPercentage.value * 100).toInt()} %",
            color = color,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}
