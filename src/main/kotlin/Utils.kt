object Utils {

    fun stringLocalizationKey(fullName: String): String =
        fullName.substringAfter("string").substringBeforeLast(".xml").toLowerCase()
}