package ru.skillbranch.devintensive.extensions

import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}


fun Date.humanizeDiff(date: Date = Date()): String {
    val diffDate = date.time - this.time
    val isPositive = diffDate > 0
    return when {
        abs(diffDate / SECOND) <= 1 -> "только что"
        abs(diffDate / SECOND) <= 45 -> when (isPositive) {
            true -> "несколько секунд назад"
            false -> "через несколько секунд"
        }
        abs(diffDate / SECOND) <= 75 -> when (isPositive) {
            true -> "минуту назад"
            false -> "через минуту"
        }
        abs(diffDate / MINUTE) <= 45 -> when (isPositive) {
            true -> "${TimeUnits.MINUTE.plural((diffDate / MINUTE).toInt())} назад"
            false -> "через ${TimeUnits.MINUTE.plural((diffDate / MINUTE).toInt())}"
        }
        abs(diffDate / MINUTE) <= 75 -> when (isPositive) {
            true -> "час назад"
            false -> "через час"
        }
        abs(diffDate / HOUR) <= 22 ->
            when (isPositive) {
                true -> "${TimeUnits.HOUR.plural((diffDate / HOUR).toInt())} назад"
                false -> "через ${TimeUnits.HOUR.plural((diffDate / HOUR).toInt())}"
            }
        abs(diffDate / DAY) <= 360 ->
            when (isPositive) {
                true -> "${TimeUnits.DAY.plural((diffDate / DAY).toInt())} назад"
                false -> "через ${TimeUnits.DAY.plural((diffDate / DAY).toInt())}"
            }
        else -> when (isPositive) {
            true -> "более года назад"
            false -> "более чем через год"
        }
    }
}


enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(count: Int): String {
        val abs = abs(count)
        val last = abs % 10
        val mod = abs % 100
        return if (last == 1 && mod != 11)
            when (this) {
                SECOND -> "$last секунду"
                MINUTE -> "$last минуту"
                HOUR -> "$last час"
                DAY -> "$last день"
            }
        else if ((last < 5 && last != 0) && (mod < 10 || mod > 20)
        )
            when (this) {
                SECOND -> "$abs секунды"
                MINUTE -> "$abs минуты"
                HOUR -> "$abs часа"
                DAY -> "$abs дня"
            }
        else
            when (this) {
                SECOND -> "$abs секунд"
                MINUTE -> "$abs минут"
                HOUR -> "$abs часов"
                DAY -> "$abs дней"
            }
    }
}