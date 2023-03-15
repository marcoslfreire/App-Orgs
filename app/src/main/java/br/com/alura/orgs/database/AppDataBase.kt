package br.com.alura.orgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.alura.orgs.database.converter.Converter
import br.com.alura.orgs.database.dao.ProdutoDao
import br.com.alura.orgs.model.Produto
import java.security.AccessControlContext

// Determina
@Database(entities = [Produto::class], version = 1, exportSchema = true)
@TypeConverters(Converter::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun produtoDao(): ProdutoDao

    companion object {
        fun instanciaDB(context: Context): AppDataBase {
            return Room.databaseBuilder(
                context,
                AppDataBase::class.java, "orgs.db"
            ).allowMainThreadQueries()
                .build()
        }
    }
}