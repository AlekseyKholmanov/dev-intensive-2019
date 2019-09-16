package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        return if (fullName.isNullOrBlank())
            null to null
        else {
            val parts = fullName.split(" ")
            val firstName = parser(parts[0])
            val lastName = if (parts.count() == 1) null else parser(parts[1])
            firstName to lastName
        }
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var translit = ""
        for (char in payload) {
            val c = char.toString().toLowerCase()
            translit +=
                if (char == ' ')
                    divider
                else if (c in dict) {
                    val tchar = dict[c]
                    if (char.isUpperCase())
                        tchar?.toUpperCase()
                    else
                        tchar
                } else {
                    char
                }
        }
        return translit
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val f = firstName?.trim()?.firstOrNull()
        val l = lastName?.trim()?.firstOrNull()
        return if (f == null && l == null) null else "${f ?: ""}${l ?: ""}".toUpperCase()
    }


    private fun parser(arg: String): String? {
        return if (arg.isEmpty()) null
        else arg
    }
}
