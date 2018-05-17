package test.scyscanner.com.scyscannertest.module.flights

import java.text.SimpleDateFormat
import java.util.*

interface JourneyDatesProvider {

    fun provideOutboundDate(): String

    fun provideOutboundDateShort(): String

    fun provideInboundDate(): String

    fun provideInboundDateShort(): String
}

class JourneyDatesProviderImpl : JourneyDatesProvider {

    private val calendar = Calendar.getInstance()
    private val yyyyMMdd = SimpleDateFormat("yyyy-MM-dd")
    private val mmDD = SimpleDateFormat("MMM d")
    private val date : Date
    private val dateNext : Date

    init {
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            calendar.add(Calendar.DATE, 1)
        }
        date = calendar.time
        calendar.add(Calendar.DATE, 1)
        dateNext = calendar.time
    }

    override fun provideOutboundDate() = yyyyMMdd.format(date)

    override fun provideOutboundDateShort() = mmDD.format(date)

    override fun provideInboundDate() = yyyyMMdd.format(dateNext)

    override fun provideInboundDateShort() = mmDD.format(dateNext)


}