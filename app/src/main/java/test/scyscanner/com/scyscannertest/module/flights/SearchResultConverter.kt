package test.scyscanner.com.scyscannertest.module.flights

import test.scyscanner.com.scyscannertest.application.api.model.*


interface SearchResultConverter {

    fun convertItems(result: FlightsSearchResult): FlightsResultConverted

}

class SearchResultConverterImpl : SearchResultConverter {

    override fun convertItems(result: FlightsSearchResult): FlightsResultConverted {
        val legsMap = mutableMapOf<String, Leg>()
        val legs = result.legs
        legs.forEach {
            legsMap[it.id] = it
        }
        val carriersMap = mutableMapOf<Int, Carrier>()
        val carriers = result.carriers
        carriers.forEach {
            carriersMap[it.id] = it
        }
        val placesMap = mutableMapOf<Int, Place>()
        val places = result.places
        places.forEach {
            placesMap[it.id] = it
        }
        val agentsMap = mutableMapOf<Int, Agent>()
        val agents = result.agents
        agents.forEach {
            agentsMap[it.id] = it
        }
        return FlightsResultConverted(result.itineraries, legsMap, placesMap, carriersMap, agentsMap, result.currencies)
    }

}