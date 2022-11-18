package com.example.atpdevmobilejava.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class FinanceDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "finances.sqlite";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "Finance";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_VALUE = "value";
    private static final String COL_STATUS = "status";

    public FinanceDatabase (Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE IF NOT EXISTS "
                + DB_TABLE + " ("
                + COL_ID + " integer primary key autoincrement, "
                + COL_NAME + " text, "
                + COL_VALUE + " text, "
                + COL_STATUS + " text)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long createFinance (Finance f) {
        ContentValues values = new ContentValues();
        values.put(COL_NAME, f.getName());
        values.put(COL_VALUE, f.getValue());
        values.put(COL_STATUS, f.getStatus());
        SQLiteDatabase database = getWritableDatabase();
        long id = database.insert(DB_TABLE, null, values);
        database.close();
        return id;
    }

    public long insertFinance (Finance f) {
        ContentValues values = new ContentValues();
        values.put(COL_ID, f.getId());
        values.put(COL_NAME, f.getName());
        values.put(COL_VALUE, f.getValue());
        values.put(COL_STATUS, f.getStatus());
        SQLiteDatabase database = getWritableDatabase();
        long id = database.insert(DB_TABLE, null, values);
        database.close();
        return id;
    }

    public ArrayList<Finance> getFinancesFromDB () {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(DB_TABLE, null, null, null, null, null, COL_ID + " DESC");
        ArrayList<Finance> finances = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID));
                String name = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_NAME)
                );
                String value = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_VALUE)
                );
                String status = cursor.getString(
                        cursor.getColumnIndexOrThrow(COL_STATUS)
                );
                finances.add(new Finance(id, name, value, status));
            } while (cursor.moveToNext());
        }

        database.close();
        return finances;
    }

    public int updateFinance (Finance f) {
        ContentValues values = new ContentValues();
        values.put(COL_NAME, f.getName());
        values.put(COL_VALUE, f.getValue());
        values.put(COL_STATUS, f.getStatus());
        String id = String.valueOf(f.getId());
        SQLiteDatabase database = getWritableDatabase();
        int count = database.update(DB_TABLE, values, COL_ID + " = ?", new String[]{id});
        database.close();
        return count;
    }

    public int updateStatus (Finance f) {
        ContentValues values = new ContentValues();
        values.put(COL_STATUS, f.getStatus());
        String id = String.valueOf(f.getId());
        SQLiteDatabase database = getWritableDatabase();
        int count = database.update(DB_TABLE, values, COL_ID + " = ?", new String[]{id});
        database.close();
        return count;
    }

    public int removeFinance (Finance f) {
        String id = String.valueOf(f.getId());
        SQLiteDatabase database = getWritableDatabase();
        int count = database.delete(DB_TABLE,
                COL_ID + " = ?", new String[]{id});
        database.close();
        return count;
    }
}
