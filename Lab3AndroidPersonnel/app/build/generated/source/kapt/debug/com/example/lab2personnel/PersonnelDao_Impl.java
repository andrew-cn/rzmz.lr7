package com.example.lab2personnel;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Double;
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
public final class PersonnelDao_Impl implements PersonnelDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PersonnelEntity> __insertionAdapterOfPersonnelEntity;

  private final PersonaTypeConverters __personaTypeConverters = new PersonaTypeConverters();

  private final EntityDeletionOrUpdateAdapter<PersonnelEntity> __deletionAdapterOfPersonnelEntity;

  private final SharedSQLiteStatement __preparedStmtOfClear;

  public PersonnelDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPersonnelEntity = new EntityInsertionAdapter<PersonnelEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `personnel` (`id`,`type`,`name`,`age`,`hireDate`,`shift`,`hoursWorked`,`position`,`salary`,`specialization`,`projectsCount`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PersonnelEntity entity) {
        statement.bindLong(1, entity.getId());
        final String _tmp = __personaTypeConverters.fromPersonaType(entity.getType());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, _tmp);
        }
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
        statement.bindLong(4, entity.getAge());
        if (entity.getHireDate() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getHireDate());
        }
        if (entity.getShift() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getShift());
        }
        if (entity.getHoursWorked() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getHoursWorked());
        }
        if (entity.getPosition() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getPosition());
        }
        if (entity.getSalary() == null) {
          statement.bindNull(9);
        } else {
          statement.bindDouble(9, entity.getSalary());
        }
        if (entity.getSpecialization() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getSpecialization());
        }
        if (entity.getProjectsCount() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getProjectsCount());
        }
      }
    };
    this.__deletionAdapterOfPersonnelEntity = new EntityDeletionOrUpdateAdapter<PersonnelEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `personnel` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PersonnelEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__preparedStmtOfClear = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM personnel";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final PersonnelEntity person, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPersonnelEntity.insert(person);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final PersonnelEntity person, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfPersonnelEntity.handle(person);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clear(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClear.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClear.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getAll(final Continuation<? super List<PersonnelEntity>> $completion) {
    final String _sql = "SELECT * FROM personnel";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<PersonnelEntity>>() {
      @Override
      @NonNull
      public List<PersonnelEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
          final int _cursorIndexOfHireDate = CursorUtil.getColumnIndexOrThrow(_cursor, "hireDate");
          final int _cursorIndexOfShift = CursorUtil.getColumnIndexOrThrow(_cursor, "shift");
          final int _cursorIndexOfHoursWorked = CursorUtil.getColumnIndexOrThrow(_cursor, "hoursWorked");
          final int _cursorIndexOfPosition = CursorUtil.getColumnIndexOrThrow(_cursor, "position");
          final int _cursorIndexOfSalary = CursorUtil.getColumnIndexOrThrow(_cursor, "salary");
          final int _cursorIndexOfSpecialization = CursorUtil.getColumnIndexOrThrow(_cursor, "specialization");
          final int _cursorIndexOfProjectsCount = CursorUtil.getColumnIndexOrThrow(_cursor, "projectsCount");
          final List<PersonnelEntity> _result = new ArrayList<PersonnelEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PersonnelEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final PersonaType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = __personaTypeConverters.toPersonaType(_tmp);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final int _tmpAge;
            _tmpAge = _cursor.getInt(_cursorIndexOfAge);
            final String _tmpHireDate;
            if (_cursor.isNull(_cursorIndexOfHireDate)) {
              _tmpHireDate = null;
            } else {
              _tmpHireDate = _cursor.getString(_cursorIndexOfHireDate);
            }
            final String _tmpShift;
            if (_cursor.isNull(_cursorIndexOfShift)) {
              _tmpShift = null;
            } else {
              _tmpShift = _cursor.getString(_cursorIndexOfShift);
            }
            final Integer _tmpHoursWorked;
            if (_cursor.isNull(_cursorIndexOfHoursWorked)) {
              _tmpHoursWorked = null;
            } else {
              _tmpHoursWorked = _cursor.getInt(_cursorIndexOfHoursWorked);
            }
            final String _tmpPosition;
            if (_cursor.isNull(_cursorIndexOfPosition)) {
              _tmpPosition = null;
            } else {
              _tmpPosition = _cursor.getString(_cursorIndexOfPosition);
            }
            final Double _tmpSalary;
            if (_cursor.isNull(_cursorIndexOfSalary)) {
              _tmpSalary = null;
            } else {
              _tmpSalary = _cursor.getDouble(_cursorIndexOfSalary);
            }
            final String _tmpSpecialization;
            if (_cursor.isNull(_cursorIndexOfSpecialization)) {
              _tmpSpecialization = null;
            } else {
              _tmpSpecialization = _cursor.getString(_cursorIndexOfSpecialization);
            }
            final Integer _tmpProjectsCount;
            if (_cursor.isNull(_cursorIndexOfProjectsCount)) {
              _tmpProjectsCount = null;
            } else {
              _tmpProjectsCount = _cursor.getInt(_cursorIndexOfProjectsCount);
            }
            _item = new PersonnelEntity(_tmpId,_tmpType,_tmpName,_tmpAge,_tmpHireDate,_tmpShift,_tmpHoursWorked,_tmpPosition,_tmpSalary,_tmpSpecialization,_tmpProjectsCount);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
