package com.cabir.airlineticketapp.ui.adapter

import com.cabir.airlineticketapp.data.model.airline.Airline


class FlightItem(
    val origin: String,
    val destination: String,
    val price: String,
    val departureTime: String,
    val arrivalTime: String,
    val marketingAirline: Airline,
    val operatingAirline: Airline,
    val currency: String,
    val baggage: Boolean = false,
    val baggageAllowance: Int = 0,
    val isDirect: Boolean = true
)