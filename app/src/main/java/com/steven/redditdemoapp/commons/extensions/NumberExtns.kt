package com.steven.redditdemoapp.commons.extensions

import java.text.DecimalFormat
import java.util.*

/**
 * Created by Steven Reyes on 09/11/2017
 */
fun Int.getDisplayScore(score: Int): String {
    var display: String
    if (score > 99999) {
        val scoreDivided = score / 1000.0
        var df : DecimalFormat = DecimalFormat("#")
        display = "${df.format(scoreDivided)}k"
    } else if (score > 9999) {
        val scoreDivided = score / 1000.0
        var df : DecimalFormat = DecimalFormat("#.0")
        display = "${df.format(scoreDivided)}k"
    } else
        display = "${score}"
    return (display)
}

fun Long.getRelativeTime(): String {
    val dateTime = Date(this * 1000)
    val sb = StringBuffer()
    val current = Calendar.getInstance().time
    var diffInSeconds = ((current.time - dateTime.time) / 1000).toInt()

    val sec = if (diffInSeconds >= 60) (diffInSeconds % 60).toInt() else diffInSeconds.toInt()
    diffInSeconds = diffInSeconds / 60
    val min = if (diffInSeconds >= 60) (diffInSeconds % 60).toInt() else diffInSeconds.toInt()
    diffInSeconds = diffInSeconds / 60
    val hrs = if (diffInSeconds >= 24) (diffInSeconds % 24).toInt() else diffInSeconds.toInt()
    diffInSeconds = diffInSeconds / 24
    val days = if (diffInSeconds >= 30) (diffInSeconds % 30).toInt() else diffInSeconds.toInt()
    diffInSeconds = diffInSeconds / 30
    var months = if (diffInSeconds >= 12) (diffInSeconds % 12).toInt() else diffInSeconds.toInt()
    diffInSeconds = diffInSeconds / 12
    val years = diffInSeconds.toInt()

    if (years > 0) {
        if (years == 1) {
            sb.append("a year")
        } else {
            sb.append("$years years")
        }
//        if (years <= 6 && months > 0) {
//            if (months == 1) {
//                sb.append(" and a month")
//            } else {
//                sb.append(" and $months months")
//            }
//        }
    } else if (months > 0) {
        if (months == 1) {
            sb.append("a month")
        } else {
            sb.append("$months months")
        }
//        if (months <= 6 && days > 0) {
//            if (days == 1) {
//                sb.append(" and a day")
//            } else {
//                sb.append(" and $days days")
//            }
//        }
    } else if (days > 0) {
        if (days == 1) {
            sb.append("a day")
        } else {
            sb.append("$days days")
        }
//        if (days <= 3 && hrs > 0) {
//            if (hrs == 1) {
//                sb.append(" and an hour")
//            } else {
//                sb.append(" and $hrs hours")
//            }
//        }
    } else if (hrs > 0) {
        if (hrs == 1) {
            sb.append("an hour")
        } else {
            sb.append("$hrs hours")
        }
//        if (min > 1) {
//            sb.append(" and $min minutes")
//        }
    } else if (min > 0) {
        if (min == 1) {
            sb.append("a minute")
        } else {
            sb.append("$min minutes")
        }
//        if (sec > 1) {
//            sb.append(" and $sec seconds")
//        }
    } else {
        if (sec <= 1) {
            sb.append("about a second")
        } else {
            sb.append("about $sec seconds")
        }
    }

    sb.append(" ago")

    return sb.toString()
}