package br.com.alura.orgs.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal


// class responsavel para converter o bigDacimal Room
class Converter {
    @TypeConverter
    fun deDouble(valor: Double?): BigDecimal{
       return valor?.let { BigDecimal(valor.toString()) }?: BigDecimal.ZERO
    }
    @TypeConverter
    fun bigDecimalParaDouble(valor: BigDecimal?): Double?{
        return valor?.let { valor.toDouble() }
    }
}