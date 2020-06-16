package com.irontec.roomexample.database.daos;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.arch.persistence.room.util.StringUtil;
import android.database.Cursor;
import com.irontec.roomexample.database.entities.Course;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class CourseDao_Impl implements CourseDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfCourse;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfCourse;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public CourseDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCourse = new EntityInsertionAdapter<Course>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Course`(`uid`,`name`,`usd_buy`,`usd_sell`,`eur_buy`,`eur_sell`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Course value) {
        stmt.bindLong(1, value.getUid());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getUsd_buy() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getUsd_buy());
        }
        if (value.getUsd_sell() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getUsd_sell());
        }
        if (value.getEur_buy() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getEur_buy());
        }
        if (value.getEur_sell() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEur_sell());
        }
      }
    };
    this.__deletionAdapterOfCourse = new EntityDeletionOrUpdateAdapter<Course>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Course` WHERE `uid` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Course value) {
        stmt.bindLong(1, value.getUid());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM course";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(List<Course> cours) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfCourse.insert(cours);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Course client) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfCourse.handle(client);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<Course> getAll() {
    final String _sql = "SELECT * FROM course";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfUsdBuy = _cursor.getColumnIndexOrThrow("usd_buy");
      final int _cursorIndexOfUsdSell = _cursor.getColumnIndexOrThrow("usd_sell");
      final int _cursorIndexOfEurBuy = _cursor.getColumnIndexOrThrow("eur_buy");
      final int _cursorIndexOfEurSell = _cursor.getColumnIndexOrThrow("eur_sell");
      final List<Course> _result = new ArrayList<Course>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Course _item;
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpUsd_buy;
        _tmpUsd_buy = _cursor.getString(_cursorIndexOfUsdBuy);
        final String _tmpUsd_sell;
        _tmpUsd_sell = _cursor.getString(_cursorIndexOfUsdSell);
        final String _tmpEur_buy;
        _tmpEur_buy = _cursor.getString(_cursorIndexOfEurBuy);
        final String _tmpEur_sell;
        _tmpEur_sell = _cursor.getString(_cursorIndexOfEurSell);
        _item = new Course(_tmpUid,_tmpName,_tmpUsd_buy,_tmpUsd_sell,_tmpEur_buy,_tmpEur_sell);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Course> getUsd_buy() {
    final String _sql = "SELECT * FROM course WHERE usd_buy=(SELECT max(usd_buy) FROM course)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfUsdBuy = _cursor.getColumnIndexOrThrow("usd_buy");
      final int _cursorIndexOfUsdSell = _cursor.getColumnIndexOrThrow("usd_sell");
      final int _cursorIndexOfEurBuy = _cursor.getColumnIndexOrThrow("eur_buy");
      final int _cursorIndexOfEurSell = _cursor.getColumnIndexOrThrow("eur_sell");
      final List<Course> _result = new ArrayList<Course>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Course _item;
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpUsd_buy;
        _tmpUsd_buy = _cursor.getString(_cursorIndexOfUsdBuy);
        final String _tmpUsd_sell;
        _tmpUsd_sell = _cursor.getString(_cursorIndexOfUsdSell);
        final String _tmpEur_buy;
        _tmpEur_buy = _cursor.getString(_cursorIndexOfEurBuy);
        final String _tmpEur_sell;
        _tmpEur_sell = _cursor.getString(_cursorIndexOfEurSell);
        _item = new Course(_tmpUid,_tmpName,_tmpUsd_buy,_tmpUsd_sell,_tmpEur_buy,_tmpEur_sell);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Course> getUsd_sell() {
    final String _sql = "SELECT * FROM course WHERE usd_sell=(SELECT min(usd_sell) FROM course)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfUsdBuy = _cursor.getColumnIndexOrThrow("usd_buy");
      final int _cursorIndexOfUsdSell = _cursor.getColumnIndexOrThrow("usd_sell");
      final int _cursorIndexOfEurBuy = _cursor.getColumnIndexOrThrow("eur_buy");
      final int _cursorIndexOfEurSell = _cursor.getColumnIndexOrThrow("eur_sell");
      final List<Course> _result = new ArrayList<Course>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Course _item;
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpUsd_buy;
        _tmpUsd_buy = _cursor.getString(_cursorIndexOfUsdBuy);
        final String _tmpUsd_sell;
        _tmpUsd_sell = _cursor.getString(_cursorIndexOfUsdSell);
        final String _tmpEur_buy;
        _tmpEur_buy = _cursor.getString(_cursorIndexOfEurBuy);
        final String _tmpEur_sell;
        _tmpEur_sell = _cursor.getString(_cursorIndexOfEurSell);
        _item = new Course(_tmpUid,_tmpName,_tmpUsd_buy,_tmpUsd_sell,_tmpEur_buy,_tmpEur_sell);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Course> getEur_buy() {
    final String _sql = "SELECT * FROM course WHERE eur_buy=(SELECT max(eur_buy) FROM course)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfUsdBuy = _cursor.getColumnIndexOrThrow("usd_buy");
      final int _cursorIndexOfUsdSell = _cursor.getColumnIndexOrThrow("usd_sell");
      final int _cursorIndexOfEurBuy = _cursor.getColumnIndexOrThrow("eur_buy");
      final int _cursorIndexOfEurSell = _cursor.getColumnIndexOrThrow("eur_sell");
      final List<Course> _result = new ArrayList<Course>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Course _item;
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpUsd_buy;
        _tmpUsd_buy = _cursor.getString(_cursorIndexOfUsdBuy);
        final String _tmpUsd_sell;
        _tmpUsd_sell = _cursor.getString(_cursorIndexOfUsdSell);
        final String _tmpEur_buy;
        _tmpEur_buy = _cursor.getString(_cursorIndexOfEurBuy);
        final String _tmpEur_sell;
        _tmpEur_sell = _cursor.getString(_cursorIndexOfEurSell);
        _item = new Course(_tmpUid,_tmpName,_tmpUsd_buy,_tmpUsd_sell,_tmpEur_buy,_tmpEur_sell);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Course> getEur_sell() {
    final String _sql = "SELECT * FROM course WHERE eur_sell=(SELECT min(eur_sell) FROM course)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfUsdBuy = _cursor.getColumnIndexOrThrow("usd_buy");
      final int _cursorIndexOfUsdSell = _cursor.getColumnIndexOrThrow("usd_sell");
      final int _cursorIndexOfEurBuy = _cursor.getColumnIndexOrThrow("eur_buy");
      final int _cursorIndexOfEurSell = _cursor.getColumnIndexOrThrow("eur_sell");
      final List<Course> _result = new ArrayList<Course>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Course _item;
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpUsd_buy;
        _tmpUsd_buy = _cursor.getString(_cursorIndexOfUsdBuy);
        final String _tmpUsd_sell;
        _tmpUsd_sell = _cursor.getString(_cursorIndexOfUsdSell);
        final String _tmpEur_buy;
        _tmpEur_buy = _cursor.getString(_cursorIndexOfEurBuy);
        final String _tmpEur_sell;
        _tmpEur_sell = _cursor.getString(_cursorIndexOfEurSell);
        _item = new Course(_tmpUid,_tmpName,_tmpUsd_buy,_tmpUsd_sell,_tmpEur_buy,_tmpEur_sell);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Course> loadAllByIds(Integer[] customersId) {
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM course WHERE uid IN (");
    final int _inputSize = customersId.length;
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (Integer _item : customersId) {
      if (_item == null) {
        _statement.bindNull(_argIndex);
      } else {
        _statement.bindLong(_argIndex, _item);
      }
      _argIndex ++;
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfUid = _cursor.getColumnIndexOrThrow("uid");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfUsdBuy = _cursor.getColumnIndexOrThrow("usd_buy");
      final int _cursorIndexOfUsdSell = _cursor.getColumnIndexOrThrow("usd_sell");
      final int _cursorIndexOfEurBuy = _cursor.getColumnIndexOrThrow("eur_buy");
      final int _cursorIndexOfEurSell = _cursor.getColumnIndexOrThrow("eur_sell");
      final List<Course> _result = new ArrayList<Course>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Course _item_1;
        final int _tmpUid;
        _tmpUid = _cursor.getInt(_cursorIndexOfUid);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpUsd_buy;
        _tmpUsd_buy = _cursor.getString(_cursorIndexOfUsdBuy);
        final String _tmpUsd_sell;
        _tmpUsd_sell = _cursor.getString(_cursorIndexOfUsdSell);
        final String _tmpEur_buy;
        _tmpEur_buy = _cursor.getString(_cursorIndexOfEurBuy);
        final String _tmpEur_sell;
        _tmpEur_sell = _cursor.getString(_cursorIndexOfEurSell);
        _item_1 = new Course(_tmpUid,_tmpName,_tmpUsd_buy,_tmpUsd_sell,_tmpEur_buy,_tmpEur_sell);
        _result.add(_item_1);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
