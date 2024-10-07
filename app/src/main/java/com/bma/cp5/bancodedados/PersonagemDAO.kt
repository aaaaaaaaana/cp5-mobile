package com.bma.cp5.bancodedados

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.bma.cp5.model.Personagem
import java.lang.Exception

class PersonagemDAO(context: Context) : IPersonagemDAO {
    private val dbHelper = DatabaseHelper.getInstance(context)

    override fun salvar(personagem: Personagem): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues()

        values.put(DatabaseHelper.COLUMN_PERSONAGEM, personagem.nome)
        values.put(DatabaseHelper.COLUMN_NOME, personagem.nomeReal)
        values.put(DatabaseHelper.COLUMN_HV, personagem.hv)
        values.put(DatabaseHelper.COLUMN_PODERES, personagem.poderes)
        values.put(DatabaseHelper.COLUMN_MOTIVACAO, personagem.motivacao)
        values.put(DatabaseHelper.COLUMN_CURIOSIDADE, personagem.curiosidade)
        values.put(DatabaseHelper.COLUMN_FOTO, personagem.foto) // Adiciona a coluna FOTO

        try {
            db.insert(DatabaseHelper.TABLE_NAME, null, values)
            Log.i("db_info", "Personagem salvo")
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("db_info", "Erro ao salvar personagem")
            return false
        } finally {
            db.close()
        }
    }

    override fun atualizar(personagem: Personagem): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues()

        values.put(DatabaseHelper.COLUMN_NOME, personagem.nomeReal)
        values.put(DatabaseHelper.COLUMN_HV, personagem.hv)
        values.put(DatabaseHelper.COLUMN_PODERES, personagem.poderes)
        values.put(DatabaseHelper.COLUMN_MOTIVACAO, personagem.motivacao)
        values.put(DatabaseHelper.COLUMN_CURIOSIDADE, personagem.curiosidade)
        values.put(DatabaseHelper.COLUMN_FOTO, personagem.foto)

        try {
            db.update(
                DatabaseHelper.TABLE_NAME, values, DatabaseHelper.COLUMN_ID + " = ?",
                arrayOf(personagem.id.toString())
            )
            Log.i("db_info", "Personagem atualizado")
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("db_info", "Erro ao atualizar personagem")
            return false
        } finally {
            db.close()
        }
    }

    override fun remover(id: Int): Boolean {
        val db = dbHelper.writableDatabase

        try {
            db.delete(
                DatabaseHelper.TABLE_NAME,
                DatabaseHelper.COLUMN_ID + " = ?",
                arrayOf(id.toString())
            )
            Log.i("db_info", "Personagem removido")
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("db_info", "Erro ao remover personagem")
            return false
        } finally {
            db.close()
        }
    }

    @SuppressLint("Range")
    override fun listar(): List<Personagem> {
        val personagens: MutableList<Personagem> = ArrayList()
        val db = dbHelper.readableDatabase

        try {
            val cursor = db.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null)

            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID))
                    val nome = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PERSONAGEM))
                    val nomeReal = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NOME))
                    val hv = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_HV))
                    val poderes = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PODERES))
                    val motivacao = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_MOTIVACAO))
                    val curiosidade = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CURIOSIDADE))
                    val foto = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_FOTO))

                    val p = Personagem(id, nome, nomeReal, hv, poderes, motivacao, curiosidade, foto)
                    personagens.add(p)
                } while (cursor.moveToNext())
            }

            Log.i("db_info", "Personagens listados")
            return personagens
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("db_info", "Erro ao listar personagens")
            return personagens
        } finally {
            db.close()
        }
    }

    override fun inserir(personagem: Personagem): Boolean {
        return salvar(personagem)
    }

}