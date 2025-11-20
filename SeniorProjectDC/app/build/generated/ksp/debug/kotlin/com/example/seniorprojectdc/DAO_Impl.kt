package com.example.seniorprojectdc

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class DAO_Impl(
  __db: RoomDatabase,
) : DAO {
  private val __db: RoomDatabase

  private val __insertAdapterOfInsect: EntityInsertAdapter<Insect>

  private val __deleteAdapterOfInsect: EntityDeleteOrUpdateAdapter<Insect>
  init {
    this.__db = __db
    this.__insertAdapterOfInsect = object : EntityInsertAdapter<Insect>() {
      protected override fun createQuery(): String = "INSERT OR REPLACE INTO `insects` (`id`,`insectName`,`date`) VALUES (nullif(?, 0),?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Insect) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.insectName)
        statement.bindLong(3, entity.date.toLong())
      }
    }
    this.__deleteAdapterOfInsect = object : EntityDeleteOrUpdateAdapter<Insect>() {
      protected override fun createQuery(): String = "DELETE FROM `insects` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Insect) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
  }

  public override suspend fun insert(insect: Insect): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfInsect.insert(_connection, insect)
  }

  public override suspend fun delete(insect: Insect): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfInsect.handle(_connection, insect)
  }

  public override fun getAllInsects(): Flow<List<Insect>> {
    val _sql: String = "SELECT * FROM insects ORDER BY date DESC"
    return createFlow(__db, false, arrayOf("insects")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfInsectName: Int = getColumnIndexOrThrow(_stmt, "insectName")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _result: MutableList<Insect> = mutableListOf()
        while (_stmt.step()) {
          val _item: Insect
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpInsectName: String
          _tmpInsectName = _stmt.getText(_columnIndexOfInsectName)
          val _tmpDate: Int
          _tmpDate = _stmt.getLong(_columnIndexOfDate).toInt()
          _item = Insect(_tmpId,_tmpInsectName,_tmpDate)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
