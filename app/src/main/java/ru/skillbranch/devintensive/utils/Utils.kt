package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        return when (fullName) {
            null -> "null" to "null"
            "" -> "null" to "null"
            " " -> "null" to "null"
            else -> {
                val parts = fullName.split(" ")
                val firstName = parser(parts[0])
                val lastName = if (parts.count() == 1) "null" else parser(parts[1])
                firstName to lastName
            }
        }
    }

    fun transliteration(payload:String, divider:String = " "): String {
        return ""
    }

    fun toInitials(firstName:String?, lastName:String?): String? {
        return ""
    }


    private fun parser(arg: String): String {
        return if (arg.isEmpty()) "null"
        else arg

    }

}