object Utils {

    fun stringLocalizationKey(fullName: String): String =
        fullName.substringAfter("strings").substringBeforeLast(".xml").toLowerCase().replace("_", "-")
}