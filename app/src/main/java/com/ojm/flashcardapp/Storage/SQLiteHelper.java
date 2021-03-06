package com.ojm.flashcardapp.Storage;

import android.content.Context;
import android.database.sqlite.*;
import android.util.Log;

/**
 * Created by omar on 11/22/15.
 */
public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = "SQLiteHelper";

    // Database Info
    public static final String DB_NAME = "flashcardsTEST.db";
    public static final int DB_VERSION = 1;

    // Tables
    public static final String DECK_TABLE = "deck";
    public static final String CARD_TABLE = "card";
    public static final String CARD_ANALYTICS_TABLE = "card_analytics";
    public static final String DECK_ANALYTICS_TABLE = "deck_analytics";
    public static final String CARD_CHOICES_TABLE = "card_choices";

    // deck rows
    public static final String DECK_deck_id = "deck_id";
    public static final String DECK_deck_name = "deck_name";
    public static final String DECK_description = "description";
    public static final String DECK_date_created_on = "date_created_on";
    public static final String DECK_date_last_modified = "date_last_modified";

    // card rows
    public static final String CARD_card_id = "card_id";
    public static final String CARD_type = "type";
    public static final String CARD_question = "question";
    public static final String CARD_notes = "notes";

    // card_choices rows
    public static final String CARD_CHOICES_id = "choice_id";
    public static final String CARD_CHOICES_answer_choice = "answer_choice";
    public static final String CARD_CHOICES_is_answer = "is_answer";

    // card_analytics rows
    public static final String CARD_ANALYTICS_id = "card_analytics_id";
    public static final String CARD_ANALYTICS_response = "response";
    public static final String CARD_ANALYTICS_timestamp_date = "response_timestamp";
    public static final String CARD_ANALYTICS_is_correct = "is_correct";

    // deck_analytics rows
    public static final String DECK_ANALYTICS_id = "deck_analytics_id";
    public static final String DECK_ANALYTICS_timestamp_started = "timestamp_started";
    public static final String DECK_ANALYTICS_timestamp_finished = "timestamp_finished";
    public static final String DECK_ANALYTICS_is_completed = "is_completed";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE " + DECK_TABLE + "("
                    + DECK_deck_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + DECK_deck_name + " TEXT,"
                    + DECK_description   + " TEXT,"
                    + DECK_date_created_on + " DATESTAMP DEFAULT CURRENT_TIMESTAMP,"
                    + DECK_date_last_modified + " DATESTAMP)");

            db.execSQL("CREATE TABLE " + CARD_TABLE + "("
                    + CARD_card_id + " INTEGER PRIMARY KEY,"
                    + DECK_deck_id + " INTEGER,"
                    + CARD_type + " TEXT,"
                    + CARD_question + " TEXT,"
                    + CARD_notes + " TEXT,"
                    + "FOREIGN KEY(deck_id) REFERENCES deck(deck_id))");

            /*
                We have a separate table for choices and answers in order to accommodate for
                multiple choices/answers.
             */
            db.execSQL("CREATE TABLE " + CARD_CHOICES_TABLE + "("
                    + CARD_CHOICES_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + DECK_deck_id + " INTEGER,"
                    + CARD_card_id + " INTEGER,"
                    + CARD_CHOICES_answer_choice + " TEXT,"
                    + CARD_CHOICES_is_answer + " INTEGER,"
                    + "FOREIGN KEY(deck_id) REFERENCES deck(deck_id),"
                    + "FOREIGN KEY(card_id) REFERENCES card(card_id))");


            /***
             *      Analytic Tables
             ***/

            db.execSQL("CREATE TABLE " + CARD_ANALYTICS_TABLE + "("
                    + CARD_ANALYTICS_id + " INTEGER PRIMARY KEY,"
                    + DECK_deck_id + " INTEGER,"
                    + CARD_card_id + " INTEGER,"
                    + CARD_ANALYTICS_timestamp_date + " DATESTAMP DEFAULT CURRENT_TIMESTAMP,"
                    + CARD_ANALYTICS_response + " TEXT,"
                    + CARD_ANALYTICS_is_correct + " INTEGER,"
                    + "FOREIGN KEY(deck_id) REFERENCES deck(deck_id),"
                    + "FOREIGN KEY(card_id) REFERENCES card(card_id))");

            db.execSQL("CREATE TABLE " + DECK_ANALYTICS_TABLE + "("
                    + DECK_ANALYTICS_id + " INTEGER PRIMARY KEY,"
                    + DECK_deck_id + " INTEGER,"
                    + DECK_ANALYTICS_timestamp_started + " DATESTAMP DEFAULT CURRENT_TIMESTAMP,"
                    + DECK_ANALYTICS_timestamp_finished + " DATESTAMP,"
                    + DECK_ANALYTICS_is_completed + " INTEGER,"
                    + "FOREIGN KEY(deck_id) REFERENCES deck(deck_id))");

            Log.d(LOG_TAG, "onCreate NOTE: Database tables created!");
        }catch (Exception e){
            Log.e(LOG_TAG, "onCreate ERROR: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + DECK_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + CARD_TABLE);
            onCreate(db);

            Log.d(LOG_TAG, "onUpgrade NOTE: Database tables updated!");
        }catch (Exception e){
            Log.e(LOG_TAG, "onUpgrade ERROR: " + e.getMessage());
        }

    }

}
