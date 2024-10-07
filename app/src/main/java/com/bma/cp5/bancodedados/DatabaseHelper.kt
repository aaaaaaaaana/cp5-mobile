package com.bma.cp5.bancodedados

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper private constructor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private var INSTANCE: DatabaseHelper? = null

        fun getInstance(context: Context): DatabaseHelper {
            if (INSTANCE == null) {
                INSTANCE = DatabaseHelper(context)
            }
            return INSTANCE!!
        }

        private const val DATABASE_NAME = "dc_personagens.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "personagens"
        const val COLUMN_ID = "id"
        const val COLUMN_PERSONAGEM = "personagem"
        const val COLUMN_NOME = "nome"
        const val COLUMN_HV = "hv"
        const val COLUMN_PODERES = "poderes"
        const val COLUMN_MOTIVACAO = "motivacao"
        const val COLUMN_CURIOSIDADE = "curiosidade"
        const val COLUMN_FOTO = "foto"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PERSONAGEM + " TEXT, " +
                COLUMN_NOME + " TEXT, " +
                COLUMN_HV + " TEXT, " +
                COLUMN_PODERES + " TEXT, " +
                COLUMN_MOTIVACAO + " TEXT, " +
                COLUMN_CURIOSIDADE + " TEXT, " +
                COLUMN_FOTO + " TEXT" +
                ")"

        try {
            db.execSQL(createTableQuery)
            Log.i("db_info", "Tabela criada com sucesso")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("db_info", "Erro ao criar tabela")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        when (oldVersion) {
            1 -> {

                db.execSQL("ALTER TABLE $TABLE_NAME ADD COLUMN $COLUMN_FOTO TEXT")
                Log.i("db_info", "Coluna FOTO adicionada com sucesso")
            }

        }
    }
}