package com.payswitch.momopos.room.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.payswitch.momopos.room.dao.entity.database.TransactionDao;
import com.payswitch.momopos.room.dao.entity.database.TransactionDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TransactionDatabase_Impl extends TransactionDatabase {
  private volatile TransactionDao _transactionDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `TransactionEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `fName` TEXT, `status` TEXT, `code` TEXT, `reason` TEXT, `amount` TEXT, `processing_code` TEXT, `transaction_id` TEXT, `desc` TEXT, `subscriber_number` TEXT, `r_switch` TEXT, `merchant_id` TEXT, `date` TEXT, `time` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `PulledTransactionEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `transaction_id` TEXT, `amount` TEXT, `subscriber_number` TEXT, `subscriber_email` TEXT, `r_switch` TEXT, `description` TEXT, `response_transaction_id` TEXT, `response_status` TEXT, `response_reason` TEXT, `response_code` TEXT, `creation_date` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'f2eb3955e8a3e5c2768d34d132ec1716')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `TransactionEntity`");
        _db.execSQL("DROP TABLE IF EXISTS `PulledTransactionEntity`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      public void onCreate(SupportSQLiteDatabase _db) {
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
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsTransactionEntity = new HashMap<String, TableInfo.Column>(14);
        _columnsTransactionEntity.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntity.put("fName", new TableInfo.Column("fName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntity.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntity.put("code", new TableInfo.Column("code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntity.put("reason", new TableInfo.Column("reason", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntity.put("amount", new TableInfo.Column("amount", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntity.put("processing_code", new TableInfo.Column("processing_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntity.put("transaction_id", new TableInfo.Column("transaction_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntity.put("desc", new TableInfo.Column("desc", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntity.put("subscriber_number", new TableInfo.Column("subscriber_number", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntity.put("r_switch", new TableInfo.Column("r_switch", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntity.put("merchant_id", new TableInfo.Column("merchant_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntity.put("date", new TableInfo.Column("date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionEntity.put("time", new TableInfo.Column("time", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTransactionEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTransactionEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTransactionEntity = new TableInfo("TransactionEntity", _columnsTransactionEntity, _foreignKeysTransactionEntity, _indicesTransactionEntity);
        final TableInfo _existingTransactionEntity = TableInfo.read(_db, "TransactionEntity");
        if (! _infoTransactionEntity.equals(_existingTransactionEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "TransactionEntity(com.payswitch.momopos.room.entity.TransactionEntity).\n"
                  + " Expected:\n" + _infoTransactionEntity + "\n"
                  + " Found:\n" + _existingTransactionEntity);
        }
        final HashMap<String, TableInfo.Column> _columnsPulledTransactionEntity = new HashMap<String, TableInfo.Column>(12);
        _columnsPulledTransactionEntity.put("id", new TableInfo.Column("id", "INTEGER", false, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPulledTransactionEntity.put("transaction_id", new TableInfo.Column("transaction_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPulledTransactionEntity.put("amount", new TableInfo.Column("amount", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPulledTransactionEntity.put("subscriber_number", new TableInfo.Column("subscriber_number", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPulledTransactionEntity.put("subscriber_email", new TableInfo.Column("subscriber_email", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPulledTransactionEntity.put("r_switch", new TableInfo.Column("r_switch", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPulledTransactionEntity.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPulledTransactionEntity.put("response_transaction_id", new TableInfo.Column("response_transaction_id", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPulledTransactionEntity.put("response_status", new TableInfo.Column("response_status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPulledTransactionEntity.put("response_reason", new TableInfo.Column("response_reason", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPulledTransactionEntity.put("response_code", new TableInfo.Column("response_code", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPulledTransactionEntity.put("creation_date", new TableInfo.Column("creation_date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPulledTransactionEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPulledTransactionEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPulledTransactionEntity = new TableInfo("PulledTransactionEntity", _columnsPulledTransactionEntity, _foreignKeysPulledTransactionEntity, _indicesPulledTransactionEntity);
        final TableInfo _existingPulledTransactionEntity = TableInfo.read(_db, "PulledTransactionEntity");
        if (! _infoPulledTransactionEntity.equals(_existingPulledTransactionEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "PulledTransactionEntity(com.payswitch.momopos.room.entity.PulledTransactionEntity).\n"
                  + " Expected:\n" + _infoPulledTransactionEntity + "\n"
                  + " Found:\n" + _existingPulledTransactionEntity);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "f2eb3955e8a3e5c2768d34d132ec1716", "918664d41de5912ec615fb3dea55e0f1");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "TransactionEntity","PulledTransactionEntity");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `TransactionEntity`");
      _db.execSQL("DELETE FROM `PulledTransactionEntity`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(TransactionDao.class, TransactionDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public TransactionDao getDao() {
    if (_transactionDao != null) {
      return _transactionDao;
    } else {
      synchronized(this) {
        if(_transactionDao == null) {
          _transactionDao = new TransactionDao_Impl(this);
        }
        return _transactionDao;
      }
    }
  }
}
