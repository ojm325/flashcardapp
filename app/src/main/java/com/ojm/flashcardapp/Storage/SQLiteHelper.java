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

    public static final String DECK_deck_id = "deck_id";
    public static final String DECK_deck_name = "deck_name";

    public static final String CARD_card_id = "card_id";
    public static final String CARD_question = "question";
    public static final String CARD_answer = "answer";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +DECK_TABLE+ "("
                    +DECK_deck_id+ "INTEGER PRIMARY KEY,"
                    +DECK_deck_name+ "TEXT UNIQUE)");

        db.execSQL("CREATE TABLE " +CARD_TABLE+ "("
                +CARD_card_id+ "INTEGER PRIMARY KEY,"
                +DECK_deck_id+ "INTEGER FOREIGN KEY,"
                +CARD_question+ "TEXT,"
                +CARD_answer+ "TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +DECK_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " +CARD_TABLE);
        onCreate(db);

    }

}
