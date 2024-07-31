package com.payswitch.momopos.room.dao.entity.database;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.payswitch.momopos.room.entity.PulledTransactionEntity;
import com.payswitch.momopos.room.entity.TransactionEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TransactionDao_Impl implements TransactionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TransactionEntity> __insertionAdapterOfTransactionEntity;

  private final EntityInsertionAdapter<PulledTransactionEntity> __insertionAdapterOfPulledTransactionEntity;

  private final EntityDeletionOrUpdateAdapter<TransactionEntity> __updateAdapterOfTransactionEntity;

  private final EntityDeletionOrUpdateAdapter<PulledTransactionEntity> __updateAdapterOfPulledTransactionEntity;

  private final EntityDeletionOrUpdateAdapter<PulledTransactionEntity> __updateAdapterOfPulledTransactionEntity_1;

  private final SharedSQLiteStatement __preparedStmtOfDelete;

  private final SharedSQLiteStatement __preparedStmtOfDeletePulled;

  public TransactionDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransactionEntity = new EntityInsertionAdapter<TransactionEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `TransactionEntity` (`id`,`fName`,`status`,`code`,`reason`,`amount`,`processing_code`,`transaction_id`,`desc`,`subscriber_number`,`r_switch`,`merchant_id`,`date`,`time`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TransactionEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getFName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getFName());
        }
        if (value.getStatus() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getStatus());
        }
        if (value.getCode() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getCode());
        }
        if (value.getReason() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getReason());
        }
        if (value.getAmount() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getAmount());
        }
        if (value.getProcessing_code() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getProcessing_code());
        }
        if (value.getTransaction_id() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getTransaction_id());
        }
        if (value.getDesc() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getDesc());
        }
        if (value.getSubscriber_number() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getSubscriber_number());
        }
        if (value.getR_switch() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getR_switch());
        }
        if (value.getMerchant_id() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getMerchant_id());
        }
        if (value.getDate() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getDate());
        }
        if (value.getTime() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getTime());
        }
      }
    };
    this.__insertionAdapterOfPulledTransactionEntity = new EntityInsertionAdapter<PulledTransactionEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `PulledTransactionEntity` (`id`,`transaction_id`,`amount`,`subscriber_number`,`subscriber_email`,`r_switch`,`description`,`response_transaction_id`,`response_status`,`response_reason`,`response_code`,`creation_date`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PulledTransactionEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getTransaction_id() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTransaction_id());
        }
        if (value.getAmount() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAmount());
        }
        if (value.getSubscriber_number() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSubscriber_number());
        }
        if (value.getSubscriber_email() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getSubscriber_email());
        }
        if (value.getR_switch() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getR_switch());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getDescription());
        }
        if (value.getResponse_transaction_id() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getResponse_transaction_id());
        }
        if (value.getResponse_status() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getResponse_status());
        }
        if (value.getResponse_reason() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getResponse_reason());
        }
        if (value.getResponse_code() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getResponse_code());
        }
        if (value.getCreation_date() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getCreation_date());
        }
      }
    };
    this.__updateAdapterOfTransactionEntity = new EntityDeletionOrUpdateAdapter<TransactionEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `TransactionEntity` SET `id` = ?,`fName` = ?,`status` = ?,`code` = ?,`reason` = ?,`amount` = ?,`processing_code` = ?,`transaction_id` = ?,`desc` = ?,`subscriber_number` = ?,`r_switch` = ?,`merchant_id` = ?,`date` = ?,`time` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, TransactionEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getFName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getFName());
        }
        if (value.getStatus() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getStatus());
        }
        if (value.getCode() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getCode());
        }
        if (value.getReason() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getReason());
        }
        if (value.getAmount() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getAmount());
        }
        if (value.getProcessing_code() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getProcessing_code());
        }
        if (value.getTransaction_id() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getTransaction_id());
        }
        if (value.getDesc() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getDesc());
        }
        if (value.getSubscriber_number() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getSubscriber_number());
        }
        if (value.getR_switch() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getR_switch());
        }
        if (value.getMerchant_id() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getMerchant_id());
        }
        if (value.getDate() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getDate());
        }
        if (value.getTime() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getTime());
        }
        if (value.getId() == null) {
          stmt.bindNull(15);
        } else {
          stmt.bindLong(15, value.getId());
        }
      }
    };
    this.__updateAdapterOfPulledTransactionEntity = new EntityDeletionOrUpdateAdapter<PulledTransactionEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `PulledTransactionEntity` SET `id` = ?,`transaction_id` = ?,`amount` = ?,`subscriber_number` = ?,`subscriber_email` = ?,`r_switch` = ?,`description` = ?,`response_transaction_id` = ?,`response_status` = ?,`response_reason` = ?,`response_code` = ?,`creation_date` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PulledTransactionEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getTransaction_id() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTransaction_id());
        }
        if (value.getAmount() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAmount());
        }
        if (value.getSubscriber_number() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSubscriber_number());
        }
        if (value.getSubscriber_email() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getSubscriber_email());
        }
        if (value.getR_switch() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getR_switch());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getDescription());
        }
        if (value.getResponse_transaction_id() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getResponse_transaction_id());
        }
        if (value.getResponse_status() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getResponse_status());
        }
        if (value.getResponse_reason() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getResponse_reason());
        }
        if (value.getResponse_code() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getResponse_code());
        }
        if (value.getCreation_date() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getCreation_date());
        }
        if (value.getId() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindLong(13, value.getId());
        }
      }
    };
    this.__updateAdapterOfPulledTransactionEntity_1 = new EntityDeletionOrUpdateAdapter<PulledTransactionEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `PulledTransactionEntity` SET `id` = ?,`transaction_id` = ?,`amount` = ?,`subscriber_number` = ?,`subscriber_email` = ?,`r_switch` = ?,`description` = ?,`response_transaction_id` = ?,`response_status` = ?,`response_reason` = ?,`response_code` = ?,`creation_date` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PulledTransactionEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getTransaction_id() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTransaction_id());
        }
        if (value.getAmount() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAmount());
        }
        if (value.getSubscriber_number() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSubscriber_number());
        }
        if (value.getSubscriber_email() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getSubscriber_email());
        }
        if (value.getR_switch() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getR_switch());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getDescription());
        }
        if (value.getResponse_transaction_id() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getResponse_transaction_id());
        }
        if (value.getResponse_status() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getResponse_status());
        }
        if (value.getResponse_reason() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getResponse_reason());
        }
        if (value.getResponse_code() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getResponse_code());
        }
        if (value.getCreation_date() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getCreation_date());
        }
        if (value.getId() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindLong(13, value.getId());
        }
      }
    };
    this.__preparedStmtOfDelete = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM transactionentity";
        return _query;
      }
    };
    this.__preparedStmtOfDeletePulled = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM pulledtransactionentity";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final TransactionEntity transactionEntity,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTransactionEntity.insert(transactionEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object insertPulled(final PulledTransactionEntity pulledTransactionEntity,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPulledTransactionEntity.insert(pulledTransactionEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object update(final TransactionEntity transactionEntity,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTransactionEntity.handle(transactionEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updatePulled(final PulledTransactionEntity pulledTransactionEntity,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPulledTransactionEntity.handle(pulledTransactionEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updateRecord(final PulledTransactionEntity pulledTransactionEntity,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPulledTransactionEntity_1.handle(pulledTransactionEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object delete(final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDelete.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDelete.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object deletePulled(final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeletePulled.acquire();
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfDeletePulled.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public LiveData<List<TransactionEntity>> getTransaction() {
    final String _sql = "SELECT * FROM transactionentity ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"transactionentity"}, false, new Callable<List<TransactionEntity>>() {
      @Override
      public List<TransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFName = CursorUtil.getColumnIndexOrThrow(_cursor, "fName");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCode = CursorUtil.getColumnIndexOrThrow(_cursor, "code");
          final int _cursorIndexOfReason = CursorUtil.getColumnIndexOrThrow(_cursor, "reason");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfProcessingCode = CursorUtil.getColumnIndexOrThrow(_cursor, "processing_code");
          final int _cursorIndexOfTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "transaction_id");
          final int _cursorIndexOfDesc = CursorUtil.getColumnIndexOrThrow(_cursor, "desc");
          final int _cursorIndexOfSubscriberNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "subscriber_number");
          final int _cursorIndexOfRSwitch = CursorUtil.getColumnIndexOrThrow(_cursor, "r_switch");
          final int _cursorIndexOfMerchantId = CursorUtil.getColumnIndexOrThrow(_cursor, "merchant_id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
          final List<TransactionEntity> _result = new ArrayList<TransactionEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final TransactionEntity _item;
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
            }
            final String _tmpFName;
            if (_cursor.isNull(_cursorIndexOfFName)) {
              _tmpFName = null;
            } else {
              _tmpFName = _cursor.getString(_cursorIndexOfFName);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final String _tmpCode;
            if (_cursor.isNull(_cursorIndexOfCode)) {
              _tmpCode = null;
            } else {
              _tmpCode = _cursor.getString(_cursorIndexOfCode);
            }
            final String _tmpReason;
            if (_cursor.isNull(_cursorIndexOfReason)) {
              _tmpReason = null;
            } else {
              _tmpReason = _cursor.getString(_cursorIndexOfReason);
            }
            final String _tmpAmount;
            if (_cursor.isNull(_cursorIndexOfAmount)) {
              _tmpAmount = null;
            } else {
              _tmpAmount = _cursor.getString(_cursorIndexOfAmount);
            }
            final String _tmpProcessing_code;
            if (_cursor.isNull(_cursorIndexOfProcessingCode)) {
              _tmpProcessing_code = null;
            } else {
              _tmpProcessing_code = _cursor.getString(_cursorIndexOfProcessingCode);
            }
            final String _tmpTransaction_id;
            if (_cursor.isNull(_cursorIndexOfTransactionId)) {
              _tmpTransaction_id = null;
            } else {
              _tmpTransaction_id = _cursor.getString(_cursorIndexOfTransactionId);
            }
            final String _tmpDesc;
            if (_cursor.isNull(_cursorIndexOfDesc)) {
              _tmpDesc = null;
            } else {
              _tmpDesc = _cursor.getString(_cursorIndexOfDesc);
            }
            final String _tmpSubscriber_number;
            if (_cursor.isNull(_cursorIndexOfSubscriberNumber)) {
              _tmpSubscriber_number = null;
            } else {
              _tmpSubscriber_number = _cursor.getString(_cursorIndexOfSubscriberNumber);
            }
            final String _tmpR_switch;
            if (_cursor.isNull(_cursorIndexOfRSwitch)) {
              _tmpR_switch = null;
            } else {
              _tmpR_switch = _cursor.getString(_cursorIndexOfRSwitch);
            }
            final String _tmpMerchant_id;
            if (_cursor.isNull(_cursorIndexOfMerchantId)) {
              _tmpMerchant_id = null;
            } else {
              _tmpMerchant_id = _cursor.getString(_cursorIndexOfMerchantId);
            }
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final String _tmpTime;
            if (_cursor.isNull(_cursorIndexOfTime)) {
              _tmpTime = null;
            } else {
              _tmpTime = _cursor.getString(_cursorIndexOfTime);
            }
            _item = new TransactionEntity(_tmpId,_tmpFName,_tmpStatus,_tmpCode,_tmpReason,_tmpAmount,_tmpProcessing_code,_tmpTransaction_id,_tmpDesc,_tmpSubscriber_number,_tmpR_switch,_tmpMerchant_id,_tmpDate,_tmpTime);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<PulledTransactionEntity>> getTransactionPulled() {
    final String _sql = "SELECT * FROM pulledtransactionentity ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"pulledtransactionentity"}, false, new Callable<List<PulledTransactionEntity>>() {
      @Override
      public List<PulledTransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "transaction_id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfSubscriberNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "subscriber_number");
          final int _cursorIndexOfSubscriberEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "subscriber_email");
          final int _cursorIndexOfRSwitch = CursorUtil.getColumnIndexOrThrow(_cursor, "r_switch");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfResponseTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "response_transaction_id");
          final int _cursorIndexOfResponseStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "response_status");
          final int _cursorIndexOfResponseReason = CursorUtil.getColumnIndexOrThrow(_cursor, "response_reason");
          final int _cursorIndexOfResponseCode = CursorUtil.getColumnIndexOrThrow(_cursor, "response_code");
          final int _cursorIndexOfCreationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "creation_date");
          final List<PulledTransactionEntity> _result = new ArrayList<PulledTransactionEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final PulledTransactionEntity _item;
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
            }
            final String _tmpTransaction_id;
            if (_cursor.isNull(_cursorIndexOfTransactionId)) {
              _tmpTransaction_id = null;
            } else {
              _tmpTransaction_id = _cursor.getString(_cursorIndexOfTransactionId);
            }
            final String _tmpAmount;
            if (_cursor.isNull(_cursorIndexOfAmount)) {
              _tmpAmount = null;
            } else {
              _tmpAmount = _cursor.getString(_cursorIndexOfAmount);
            }
            final String _tmpSubscriber_number;
            if (_cursor.isNull(_cursorIndexOfSubscriberNumber)) {
              _tmpSubscriber_number = null;
            } else {
              _tmpSubscriber_number = _cursor.getString(_cursorIndexOfSubscriberNumber);
            }
            final String _tmpSubscriber_email;
            if (_cursor.isNull(_cursorIndexOfSubscriberEmail)) {
              _tmpSubscriber_email = null;
            } else {
              _tmpSubscriber_email = _cursor.getString(_cursorIndexOfSubscriberEmail);
            }
            final String _tmpR_switch;
            if (_cursor.isNull(_cursorIndexOfRSwitch)) {
              _tmpR_switch = null;
            } else {
              _tmpR_switch = _cursor.getString(_cursorIndexOfRSwitch);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpResponse_transaction_id;
            if (_cursor.isNull(_cursorIndexOfResponseTransactionId)) {
              _tmpResponse_transaction_id = null;
            } else {
              _tmpResponse_transaction_id = _cursor.getString(_cursorIndexOfResponseTransactionId);
            }
            final String _tmpResponse_status;
            if (_cursor.isNull(_cursorIndexOfResponseStatus)) {
              _tmpResponse_status = null;
            } else {
              _tmpResponse_status = _cursor.getString(_cursorIndexOfResponseStatus);
            }
            final String _tmpResponse_reason;
            if (_cursor.isNull(_cursorIndexOfResponseReason)) {
              _tmpResponse_reason = null;
            } else {
              _tmpResponse_reason = _cursor.getString(_cursorIndexOfResponseReason);
            }
            final String _tmpResponse_code;
            if (_cursor.isNull(_cursorIndexOfResponseCode)) {
              _tmpResponse_code = null;
            } else {
              _tmpResponse_code = _cursor.getString(_cursorIndexOfResponseCode);
            }
            final String _tmpCreation_date;
            if (_cursor.isNull(_cursorIndexOfCreationDate)) {
              _tmpCreation_date = null;
            } else {
              _tmpCreation_date = _cursor.getString(_cursorIndexOfCreationDate);
            }
            _item = new PulledTransactionEntity(_tmpId,_tmpTransaction_id,_tmpAmount,_tmpSubscriber_number,_tmpSubscriber_email,_tmpR_switch,_tmpDescription,_tmpResponse_transaction_id,_tmpResponse_status,_tmpResponse_reason,_tmpResponse_code,_tmpCreation_date);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<PulledTransactionEntity>> searchDatabase(final String searchQuery) {
    final String _sql = "SELECT * FROM pulledtransactionentity WHERE transaction_id LIKE ? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (searchQuery == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, searchQuery);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"pulledtransactionentity"}, false, new Callable<List<PulledTransactionEntity>>() {
      @Override
      public List<PulledTransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "transaction_id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfSubscriberNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "subscriber_number");
          final int _cursorIndexOfSubscriberEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "subscriber_email");
          final int _cursorIndexOfRSwitch = CursorUtil.getColumnIndexOrThrow(_cursor, "r_switch");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfResponseTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "response_transaction_id");
          final int _cursorIndexOfResponseStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "response_status");
          final int _cursorIndexOfResponseReason = CursorUtil.getColumnIndexOrThrow(_cursor, "response_reason");
          final int _cursorIndexOfResponseCode = CursorUtil.getColumnIndexOrThrow(_cursor, "response_code");
          final int _cursorIndexOfCreationDate = CursorUtil.getColumnIndexOrThrow(_cursor, "creation_date");
          final List<PulledTransactionEntity> _result = new ArrayList<PulledTransactionEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final PulledTransactionEntity _item;
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
            }
            final String _tmpTransaction_id;
            if (_cursor.isNull(_cursorIndexOfTransactionId)) {
              _tmpTransaction_id = null;
            } else {
              _tmpTransaction_id = _cursor.getString(_cursorIndexOfTransactionId);
            }
            final String _tmpAmount;
            if (_cursor.isNull(_cursorIndexOfAmount)) {
              _tmpAmount = null;
            } else {
              _tmpAmount = _cursor.getString(_cursorIndexOfAmount);
            }
            final String _tmpSubscriber_number;
            if (_cursor.isNull(_cursorIndexOfSubscriberNumber)) {
              _tmpSubscriber_number = null;
            } else {
              _tmpSubscriber_number = _cursor.getString(_cursorIndexOfSubscriberNumber);
            }
            final String _tmpSubscriber_email;
            if (_cursor.isNull(_cursorIndexOfSubscriberEmail)) {
              _tmpSubscriber_email = null;
            } else {
              _tmpSubscriber_email = _cursor.getString(_cursorIndexOfSubscriberEmail);
            }
            final String _tmpR_switch;
            if (_cursor.isNull(_cursorIndexOfRSwitch)) {
              _tmpR_switch = null;
            } else {
              _tmpR_switch = _cursor.getString(_cursorIndexOfRSwitch);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpResponse_transaction_id;
            if (_cursor.isNull(_cursorIndexOfResponseTransactionId)) {
              _tmpResponse_transaction_id = null;
            } else {
              _tmpResponse_transaction_id = _cursor.getString(_cursorIndexOfResponseTransactionId);
            }
            final String _tmpResponse_status;
            if (_cursor.isNull(_cursorIndexOfResponseStatus)) {
              _tmpResponse_status = null;
            } else {
              _tmpResponse_status = _cursor.getString(_cursorIndexOfResponseStatus);
            }
            final String _tmpResponse_reason;
            if (_cursor.isNull(_cursorIndexOfResponseReason)) {
              _tmpResponse_reason = null;
            } else {
              _tmpResponse_reason = _cursor.getString(_cursorIndexOfResponseReason);
            }
            final String _tmpResponse_code;
            if (_cursor.isNull(_cursorIndexOfResponseCode)) {
              _tmpResponse_code = null;
            } else {
              _tmpResponse_code = _cursor.getString(_cursorIndexOfResponseCode);
            }
            final String _tmpCreation_date;
            if (_cursor.isNull(_cursorIndexOfCreationDate)) {
              _tmpCreation_date = null;
            } else {
              _tmpCreation_date = _cursor.getString(_cursorIndexOfCreationDate);
            }
            _item = new PulledTransactionEntity(_tmpId,_tmpTransaction_id,_tmpAmount,_tmpSubscriber_number,_tmpSubscriber_email,_tmpR_switch,_tmpDescription,_tmpResponse_transaction_id,_tmpResponse_status,_tmpResponse_reason,_tmpResponse_code,_tmpCreation_date);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<TransactionEntity>> searchDatabaseToday(final String searchQuery) {
    final String _sql = "SELECT * FROM transactionentity WHERE transaction_id LIKE ? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (searchQuery == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, searchQuery);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"transactionentity"}, false, new Callable<List<TransactionEntity>>() {
      @Override
      public List<TransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFName = CursorUtil.getColumnIndexOrThrow(_cursor, "fName");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCode = CursorUtil.getColumnIndexOrThrow(_cursor, "code");
          final int _cursorIndexOfReason = CursorUtil.getColumnIndexOrThrow(_cursor, "reason");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfProcessingCode = CursorUtil.getColumnIndexOrThrow(_cursor, "processing_code");
          final int _cursorIndexOfTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "transaction_id");
          final int _cursorIndexOfDesc = CursorUtil.getColumnIndexOrThrow(_cursor, "desc");
          final int _cursorIndexOfSubscriberNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "subscriber_number");
          final int _cursorIndexOfRSwitch = CursorUtil.getColumnIndexOrThrow(_cursor, "r_switch");
          final int _cursorIndexOfMerchantId = CursorUtil.getColumnIndexOrThrow(_cursor, "merchant_id");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
          final List<TransactionEntity> _result = new ArrayList<TransactionEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final TransactionEntity _item;
            final Integer _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getInt(_cursorIndexOfId);
            }
            final String _tmpFName;
            if (_cursor.isNull(_cursorIndexOfFName)) {
              _tmpFName = null;
            } else {
              _tmpFName = _cursor.getString(_cursorIndexOfFName);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final String _tmpCode;
            if (_cursor.isNull(_cursorIndexOfCode)) {
              _tmpCode = null;
            } else {
              _tmpCode = _cursor.getString(_cursorIndexOfCode);
            }
            final String _tmpReason;
            if (_cursor.isNull(_cursorIndexOfReason)) {
              _tmpReason = null;
            } else {
              _tmpReason = _cursor.getString(_cursorIndexOfReason);
            }
            final String _tmpAmount;
            if (_cursor.isNull(_cursorIndexOfAmount)) {
              _tmpAmount = null;
            } else {
              _tmpAmount = _cursor.getString(_cursorIndexOfAmount);
            }
            final String _tmpProcessing_code;
            if (_cursor.isNull(_cursorIndexOfProcessingCode)) {
              _tmpProcessing_code = null;
            } else {
              _tmpProcessing_code = _cursor.getString(_cursorIndexOfProcessingCode);
            }
            final String _tmpTransaction_id;
            if (_cursor.isNull(_cursorIndexOfTransactionId)) {
              _tmpTransaction_id = null;
            } else {
              _tmpTransaction_id = _cursor.getString(_cursorIndexOfTransactionId);
            }
            final String _tmpDesc;
            if (_cursor.isNull(_cursorIndexOfDesc)) {
              _tmpDesc = null;
            } else {
              _tmpDesc = _cursor.getString(_cursorIndexOfDesc);
            }
            final String _tmpSubscriber_number;
            if (_cursor.isNull(_cursorIndexOfSubscriberNumber)) {
              _tmpSubscriber_number = null;
            } else {
              _tmpSubscriber_number = _cursor.getString(_cursorIndexOfSubscriberNumber);
            }
            final String _tmpR_switch;
            if (_cursor.isNull(_cursorIndexOfRSwitch)) {
              _tmpR_switch = null;
            } else {
              _tmpR_switch = _cursor.getString(_cursorIndexOfRSwitch);
            }
            final String _tmpMerchant_id;
            if (_cursor.isNull(_cursorIndexOfMerchantId)) {
              _tmpMerchant_id = null;
            } else {
              _tmpMerchant_id = _cursor.getString(_cursorIndexOfMerchantId);
            }
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final String _tmpTime;
            if (_cursor.isNull(_cursorIndexOfTime)) {
              _tmpTime = null;
            } else {
              _tmpTime = _cursor.getString(_cursorIndexOfTime);
            }
            _item = new TransactionEntity(_tmpId,_tmpFName,_tmpStatus,_tmpCode,_tmpReason,_tmpAmount,_tmpProcessing_code,_tmpTransaction_id,_tmpDesc,_tmpSubscriber_number,_tmpR_switch,_tmpMerchant_id,_tmpDate,_tmpTime);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
