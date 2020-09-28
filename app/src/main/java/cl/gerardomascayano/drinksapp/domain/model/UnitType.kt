package cl.gerardomascayano.drinksapp.domain.model

enum class UnitType {
    ML, OZ;

    fun parseValue(value: Float): Float {
        return when (this) {
            ML -> value * 30
            OZ -> value / 30
        }
    }

    fun parseAbbreviation(): String {
        return when (this) {
            ML -> "ml"
            OZ -> "oz"
        }
    }

}