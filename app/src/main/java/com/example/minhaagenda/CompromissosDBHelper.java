package com.example.minhaagenda;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CompromissosDBHelper extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String NOME_DATABASE = "compromissosDB";

    public CompromissosDBHelper(Context context) {
        super(context, NOME_DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + CompromissosDBSchema.CompromissosTbl.NOME + " (" +
                CompromissosDBSchema.CompromissosTbl.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CompromissosDBSchema.CompromissosTbl.Cols.DATA + " TEXT, " +
                CompromissosDBSchema.CompromissosTbl.Cols.HORA + " TEXT, " +
                CompromissosDBSchema.CompromissosTbl.Cols.DESCRICAO + " TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CompromissosDBSchema.CompromissosTbl.NOME);
        onCreate(db);
    }
}
