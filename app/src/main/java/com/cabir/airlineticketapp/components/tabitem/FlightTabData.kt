package com.cabir.airlineticketapp.components.tabitem

import java.util.Date

class FlightTabData(
    val label: String,
    val price: String,
    val date: Date,
    val isToday:Boolean = false
)