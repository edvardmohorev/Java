package com.irontec.roomexample.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import com.irontec.roomexample.database.daos.CourseDao;
import com.irontec.roomexample.database.daos.CourseDao_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class AppDatabase_Impl extends AppDatabase {
  private volatile CourseDao _courseDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Course` (`uid` INTEGER NOT NULL, `name` TEXT, `usd_buy` TEXT, `usd_sell` TEXT, `eur_buy` TEXT, `eur_sell` TEXT, PRIMARY KEY(`uid`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"7bebe6d0a1adfe033995d23cbd50a8a2\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Course`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsCourse = new HashMap<String, TableInfo.Column>(6);
        _columnsCourse.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1));
        _columnsCourse.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsCourse.put("usd_buy", new TableInfo.Column("usd_buy", "TEXT", false, 0));
        _columnsCourse.put("usd_sell", new TableInfo.Column("usd_sell", "TEXT", false, 0));
        _columnsCourse.put("eur_buy", new TableInfo.Column("eur_buy", "TEXT", false, 0));
        _columnsCourse.put("eur_sell", new TableInfo.Column("eur_sell", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCourse = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCourse = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCourse = new TableInfo("Course", _columnsCourse, _foreignKeysCourse, _indicesCourse);
        final TableInfo _existingCourse = TableInfo.read(_db, "Course");
        if (! _infoCourse.equals(_existingCourse)) {
          throw new IllegalStateException("Migration didn't properly handle Course(com.irontec.roomexample.database.entities.Course).\n"
                  + " Expected:\n" + _infoCourse + "\n"
                  + " Found:\n" + _existingCourse);
        }
      }
    }, "7bebe6d0a1adfe033995d23cbd50a8a2", "24a326c4d6818b08552f78fd2c402f6e");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "Course");
  }

  @Override
  public CourseDao courseDao() {
    if (_courseDao != null) {
      return _courseDao;
    } else {
      synchronized(this) {
        if(_courseDao == null) {
          _courseDao = new CourseDao_Impl(this);
        }
        return _courseDao;
      }
    }
  }
}
