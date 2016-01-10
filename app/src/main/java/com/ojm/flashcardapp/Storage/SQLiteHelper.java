package com.ojm.flashcardapp.Storage;

import android.content.Context;
import android.database.sqlite.*;

/**
 * Created by omar on 11/22/15.
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "flashcardsTEST.db";
    public static final int DB_VERSION = 1;
    public static final String DECK_TABLE = "deck_table";
    public static final String CARD_TABLE = "card_table";

    public static final String DECK_COL1 = "id";
    public static final String DECK_COL2 = "deck_name";

    public static final String CARD_COL1 = "id";
    public static final String CARD_COL2 = "question";
    public static final String CARD_COL3 = "answer";

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +DECK_TABLE+ "("
                    +DECK_COL1+ "INTEGER PRIMARY KEY,"
                    +DECK_COL2+ "TEXT)");

        db.execSQL("CREATE TABLE " +CARD_TABLE+ "("
                +CARD_COL1+ "INTEGER PRIMARY KEY,"
                +CARD_COL2+ "TEXT,"
                +CARD_COL3+ "TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
