package test.scyscanner.com.scyscannertest.module.flights

import test.scyscanner.com.scyscannertest.application.api.model.*

class FlightsResultConverted(val itinerary: List<Itinerary>,
                             val legs: Map<String, Leg>,
                             val places: Map<Int, Place>,
                             val carriers: Map<Int, Carrier>,
                             val agents: Map<Int, Agent>,
                             val currencies: List<Currency>)