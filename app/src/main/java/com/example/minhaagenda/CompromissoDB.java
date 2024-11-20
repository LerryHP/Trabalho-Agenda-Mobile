package com.example.minhaagenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class CompromissoDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "compromissos.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "compromissos";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATA = "data";
    private static final String COLUMN_HORA = "hora";
    private static final String COLUMN_DESCRICAO = "descricao";

    public CompromissoDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATA + " TEXT, " +
                COLUMN_HORA + " TEXT, " +
                COLUMN_DESCRICAO + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addCompromisso(Compromisso compromisso) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DATA, compromisso.getData());
        values.put(COLUMN_HORA, compromisso.getHora());
        values.put(COLUMN_DESCRICAO, compromisso.getDescricao());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Compromisso> getCompromissos() {
        List<Compromisso> compromissos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, COLUMN_DATA + " ASC");

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String data = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATA));
                String hora = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HORA));
                String descricao = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRICAO));

                compromissos.add(new Compromisso(data, hora, descricao));
            } while (cursor.moveToNext());
            cursor.close();
        }

        db.close();
        return compromissos;
    }
}
