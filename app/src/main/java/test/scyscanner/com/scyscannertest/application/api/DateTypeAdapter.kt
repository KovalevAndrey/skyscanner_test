package test.scyscanner.com.scyscannertest.application.api

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.text.SimpleDateFormat
import java.util.*


class DateTypeAdapter : TypeAdapter<Date>() {

    private val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

    init {
        format.timeZone = TimeZone.getTimeZone("GMT")
    }

    override fun read(reader: JsonReader): Date {
        val dateString = reader.nextString()
        return format.parse(dateString)
    }

    override fun write(out: JsonWriter, date: Date?) {
        out.value(date.toString())
    }
}