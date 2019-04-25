package com.helpnet.tech.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object ParseUtil {

    private var formatDatetime = SimpleDateFormat("dd/MM/yyyy 'às' HH:mm", Locale.getDefault())
    private const val MillisPerHour: Long = 3600000

    fun formatDateString(dateString: String?): String {

        val date = convertToDate(dateString)

        return convertToLocalTimezone(date)
    }

    @Throws(ParseException::class)
    private fun convertToDate(iso8601string: String?): Date {
        var s = iso8601string?.replace("Z", "+00:00")
        try {
            s = s?.substring(0, 22) + s?.substring(23)  // to get rid of the ":"
        } catch (e: IndexOutOfBoundsException) {
            throw ParseException("Invalid length", 0)
        }

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())
        return simpleDateFormat.parse(s)
    }

    private fun convertToLocalTimezone(date: Date): String {
        val timezone = getTimezone()

        val tz = TimeZone.getDefault()
        val localDate = if (tz.inDaylightTime(date)) {
            val daylight = tz.dstSavings / MillisPerHour
            Date(date.time + daylight * MillisPerHour)
        } else {
            date
        }

        val diff = (TimeZone.getDefault().rawOffset - TimeZone.getTimeZone(timezone).rawOffset).toLong()
        localDate.time = localDate.time + diff
        val sdf = formatDatetime
        return sdf.format(localDate)
    }

    /**
     * For now, we just return a fixed timezone (America/Recife), but in the future, this should read the local timezone.
     */
    private fun getTimezone(): String = "GMT-3:00"
}