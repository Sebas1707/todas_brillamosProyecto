package mx.cazv.todasbrillamos.view.screens.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBackIos
import androidx.compose.material.icons.automirrored.outlined.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import mx.cazv.todasbrillamos.ui.theme.BackgroundColor
import mx.cazv.todasbrillamos.ui.theme.ColorOfMostFertilePeriod
import mx.cazv.todasbrillamos.ui.theme.OvulationColor
import mx.cazv.todasbrillamos.ui.theme.PeriodColor
import mx.cazv.todasbrillamos.view.Routes
import mx.cazv.todasbrillamos.view.components.header.CustomTopBar
import mx.cazv.todasbrillamos.view.components.PinkButton
import mx.cazv.todasbrillamos.view.layouts.CustomLayout
import mx.cazv.todasbrillamos.view.layouts.MainLayout
import mx.cazv.todasbrillamos.view.screens.CalendarView
import mx.cazv.todasbrillamos.viewmodel.CalendarVM

/**
 * Archivo para mostrar el calendario del ciclo menstrual
 * @author Min Che Kim
 */

///**
// * Composable que muestra las etiquetas de los días de la semana.
// *
// * @param list La lista de etiquetas de los días de la semana.
// */
//@Composable
//fun DaysLabel(
//    list: List<String>,
//) {
//    Row (
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        for (day in list) {
//            Text(
//                text = day,
//                fontSize = 11.sp,
//                color = Color.Gray,
//                fontWeight = FontWeight.W500,
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .weight(1f)
//            )
//        }
//    }
//}
//
///**
// * Composable que muestra los días de la semana con sus respectivos rangos de periodo, periodo fértil y día de ovulación.
// *
// * @param list La lista de días de la semana.
// * @param periodRange El rango de días del periodo.
// * @param fertileRange El rango de días del periodo fértil.
// * @param ovulationDay El día de ovulación.
// */
//@Composable
//fun DaysOfTheWeek(
//    list: List<String>,
//    periodRange: Pair<Int, Int>? = null,
//    fertileRange: Pair<Int, Int>? = null,
//    ovulationDay: Int? = null
//) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            .fillMaxWidth()
//    ) {
//        list.forEachIndexed { index, day ->
//            // Verificar si el día actual está dentro del rango del periodo o del periodo fértil
//            val isInPeriodRange = periodRange != null && index in periodRange.first..periodRange.second
//            val isInFertileRange = fertileRange != null && index in fertileRange.first..fertileRange.second
//            val isOvulationDay = ovulationDay != null && index == ovulationDay
//
//            // Si estamos en el primer día del rango del periodo, crear un único Box con borde que abarque todo el rango
//            if (periodRange != null && index == periodRange.first) {
//                Box(
//                    modifier = Modifier
//                        .weight((periodRange.second - periodRange.first + 1).toFloat())
//                        .padding(horizontal = 4.dp)
//                        .border(2.dp, PeriodColor, RoundedCornerShape(8.dp))
//                ) {
//                    Row(modifier = Modifier.fillMaxWidth()) {
//                        list.subList(periodRange.first, periodRange.second + 1).forEachIndexed { subIndex, periodDay ->
//                            val currentIndex = periodRange.first + subIndex
//                            val isOvulationWithinPeriod = ovulationDay != null && currentIndex == ovulationDay
//
//                            Box(
//                                modifier = Modifier
//                                    .weight(1f)
//                                    .padding(8.dp),
//                                contentAlignment = Alignment.Center
//                            ) {
//                                if (isOvulationWithinPeriod) {
//                                    Box(
//                                        modifier = Modifier
//                                            .size(24.dp)
//                                            .background(
//                                                color = OvulationColor.copy(alpha = 0.5f),
//                                                shape = CircleShape
//                                            )
//                                    )
//                                }
//
//                                Text(
//                                    text = periodDay,
//                                    fontSize = 14.sp,
//                                    color = Color.Black,
//                                    fontWeight = FontWeight.W500,
//                                    textAlign = TextAlign.Center
//                                )
//                            }
//                        }
//                    }
//                }
//            }
//
//            // Si estamos en el primer día del rango fértil, crear un único Box con borde que abarque todo el rango
//            if (fertileRange != null && index == fertileRange.first) {
//                Box(
//                    modifier = Modifier
//                        .weight((fertileRange.second - fertileRange.first + 1).toFloat())
//                        .padding(horizontal = 4.dp)
//                        .border(2.dp, ColorOfMostFertilePeriod, RoundedCornerShape(8.dp))
//                ) {
//                    Row(modifier = Modifier.fillMaxWidth()) {
//                        list.subList(fertileRange.first, fertileRange.second + 1).forEachIndexed { subIndex, fertileDay ->
//                            val currentIndex = fertileRange.first + subIndex
//                            val isOvulationWithinFertile = ovulationDay != null && currentIndex == ovulationDay
//
//                            Box(
//                                modifier = Modifier
//                                    .weight(1f)
//                                    .padding(8.dp),
//                                contentAlignment = Alignment.Center
//                            ) {
//                                if (isOvulationWithinFertile) {
//                                    Box(
//                                        modifier = Modifier
//                                            .size(24.dp)
//                                            .background(
//                                                color = OvulationColor.copy(alpha = 0.5f),
//                                                shape = CircleShape
//                                            )
//                                    )
//                                }
//
//                                Text(
//                                    text = fertileDay,
//                                    fontSize = 14.sp,
//                                    color = Color.Black,
//                                    fontWeight = FontWeight.W500,
//                                    textAlign = TextAlign.Center
//                                )
//                            }
//                        }
//                    }
//                }
//            }
//
//            // Mostrar el resto de los días, si no están en un rango
//            if (!isInPeriodRange && !isInFertileRange) {
//                Box(
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(horizontal = 4.dp),
//                    contentAlignment = Alignment.Center
//                ) {
//                    if (isOvulationDay) {
//                        Box(
//                            modifier = Modifier
//                                .size(24.dp)
//                                .background(
//                                    color = OvulationColor.copy(alpha = 0.5f),
//                                    shape = CircleShape
//                                )
//                        )
//                    }
//
//                    Text(
//                        text = day,
//                        fontSize = 14.sp,
//                        color = Color.Black,
//                        fontWeight = FontWeight.W500,
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(8.dp)
//                    )
//                }
//            }
//        }
//    }
//}
//
///**
// * Composable que muestra el calendario del ciclo menstrual.
// */
//@Composable
//fun Calendar() {
//    Box (
//        modifier = Modifier
//            .padding(start = 20.dp, end = 20.dp)
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .offset(y = 10.dp)
//                .shadow(10.dp, shape = RoundedCornerShape(8.dp))
//                .background(Color.White, shape = RoundedCornerShape(8.dp))
//                .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 20.dp)
//        ) {
//            Column {
//                Row (
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier
//                        .padding(bottom = 15.dp)
//                ) {
//                    Icon(
//                        Icons.AutoMirrored.Outlined.ArrowBackIos,
//                        tint = Color.Gray,
//                        contentDescription = "Row",
//                        modifier = Modifier
//                            .size(12.dp)
//                    )
//
//                    Text("Septiembre 2024",
//                        fontSize = 14.sp,
//                        color = Color.Black,
//                        fontWeight = FontWeight.W500,
//                        textAlign = TextAlign.Center,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .weight(1f)
//                    )
//
//                    Icon(
//                        Icons.AutoMirrored.Outlined.ArrowForwardIos,
//                        tint = Color.Gray,
//                        contentDescription = "Row",
//                        modifier = Modifier
//                            .size(12.dp)
//                    )
//                }
//
//                DaysLabel(list = listOf("DOM", "LUN", "MAR", "MIE", "JUE", "VIE", "SAB"))
//
//                Spacer(modifier = Modifier.height(5.dp))
//
//                DaysOfTheWeek(list = listOf("1", "2", "3", "4", "5", "6", "7"), periodRange = Pair(1, 5))
//
//                Spacer(modifier = Modifier.height(5.dp))
//
//                DaysOfTheWeek(list = listOf("8", "9", "10", "11", "12", "13", "14"), fertileRange = Pair(6, 6))
//
//                Spacer(modifier = Modifier.height(5.dp))
//
//                DaysOfTheWeek(list = listOf("15", "16", "17", "18", "19", "20", "21"), fertileRange = Pair(0, 5), ovulationDay = 2)
//
//                Spacer(modifier = Modifier.height(5.dp))
//
//                DaysOfTheWeek(list = listOf("22", "23", "24", "25", "26", "27", "28"))
//
//                Spacer(modifier = Modifier.height(5.dp))
//
//                DaysOfTheWeek(list = listOf("29", "30", "31", "", "", "", ""))
//            }
//        }
//    }
//}

/**
 * Composable que muestra una leyenda con un color y un nombre.
 *
 * @param color El color de la leyenda.
 * @param name El nombre de la leyenda.
 */
@Composable
fun Group(color: Color, name: String) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(bottom = 5.dp)
    ) {
        Box(
            modifier = Modifier
                .size(15.dp)
                .clip(CircleShape)
                .background(color)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = name,
            fontSize = 15.sp,
            color = Color.Black,
            fontWeight = FontWeight.W400,
        )
    }
}

/**
 * Pantalla que muestra el ciclo menstrual del usuario con un calendario y leyendas.
 *
 * @param navController El NavHostController utilizado para la navegación.
 */
@Composable
fun YourCycle(navController: NavHostController, calendarVM: CalendarVM) {

    calendarVM.calculateCycle()

    val estado = calendarVM.state.collectAsState()

    var currentMonthDate by remember { mutableStateOf(estado.value.nextPeriodStartDate) }

    val nextPeriodStartDate = estado.value.nextPeriodStartDate
    val nextPeriodEndDate = estado.value.nextPeriodEndDate
    val ovulationDate = estado.value.ovulationDate
    val fertileDayStart = estado.value.fertileDayStart
    val fertileDayEnd = estado.value.fertileDayEnd

    val nextMonth = calendarVM.getNextMonth(currentMonthDate)
    val prevMonth = calendarVM.getPreviousMonth(currentMonthDate)

    val dates = calendarVM.generateCalendarDates(
        monthDate = currentMonthDate,
        nextPeriodStartDate = nextPeriodStartDate,
        nextPeriodEndDate = nextPeriodEndDate,
        ovulationDate = ovulationDate,
        fertileStartDate = fertileDayStart,
        fertileEndDate = fertileDayEnd
    )

    MainLayout(navController = navController) {
        Column (
//            modifier = Modifier
//                .fillMaxHeight()
//                .fillMaxWidth()
//                .background(BackgroundColor)
//                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Tu siguiente ciclo",
                fontSize = 29.sp,
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.W600,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BackgroundColor)
                    .padding(top = 30.dp, start = 10.dp, end = 10.dp, bottom = 30.dp)
            )

            CalendarView(
                month = currentMonthDate,
                date = dates,
                displayNext = true,
                displayPrev = true,
                onClickNext = { currentMonthDate = nextMonth },
                onClickPrev = { currentMonthDate = prevMonth },
                onClick = {  },
                startFromSunday = true,
                nextPeriodStartDate = nextPeriodStartDate,
                nextPeriodEndDate = nextPeriodEndDate,
                ovulationDate = ovulationDate,
                fertileDayStart = fertileDayStart,
                fertileDayEnd = fertileDayEnd,
                modifier = Modifier.fillMaxWidth()
            )


            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
            ) {
                Group(color = PeriodColor, name = "Periodo")
                Group(color = ColorOfMostFertilePeriod, name = "Periodo más fértil")
                Group(color = OvulationColor, name = "Ovulación")
            }

            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                PinkButton("Volver a calcular", onClick = {
                    navController.navigate(Routes.ROUTE_CALENDAR)
                    calendarVM.updateSelectedDate(0L)
                    calendarVM.updateSelectedNumber("0", "period")
                    calendarVM.updateSelectedNumber("0", "cycle")})
            }

            Spacer(modifier = Modifier.height(40.dp))
        }


//        Column (
////            modifier = Modifier
////                .fillMaxHeight()
////                .fillMaxWidth()
////                .background(BackgroundColor)
////                .verticalScroll(rememberScrollState())
//        ) {
//            Text(
//                text = "Tu siguiente ciclo",
//                fontSize = 29.sp,
//                textAlign = TextAlign.Center,
//                fontStyle = FontStyle.Italic,
//                fontWeight = FontWeight.W600,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(BackgroundColor)
//                    .padding(top = 30.dp, start = 10.dp, end = 10.dp, bottom = 30.dp)
//            )
//
//            Calendar()
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            Column (
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(30.dp)
//            ) {
//                Group(color = PeriodColor, name = "Periodo")
//                Group(color = ColorOfMostFertilePeriod, name = "Periodo más fértil")
//                Group(color = OvulationColor, name = "Ovulación")
//            }
//
//            Spacer(modifier = Modifier.height(15.dp))
//
//            Box (
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 20.dp, end = 20.dp)
//            ) {
//                PinkButton("Volver a calcular", onClick = { navController.navigate(Routes.ROUTE_CALENDAR) })
//            }
//        }
    }
}