package br.com.alura.orgs.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.alura.orgs.database.converter.Converter
import br.com.alura.orgs.database.dao.ProdutoDao
import br.com.alura.orgs.model.Produto

// Determina
@Database(entities = [Produto::class], version = 1)
@TypeConverters(Converter::class)
abstract  class AppDataBase : RoomDatabase(){
    abstract fun produtoDao(): ProdutoDao
}