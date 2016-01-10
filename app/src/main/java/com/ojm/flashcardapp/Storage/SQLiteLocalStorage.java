package com.ojm.flashcardapp.Storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ojm.flashcardapp.Cards.FlashCardDeck;
import com.ojm.flashcardapp.Cards.FlashCardSingleCard;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Omar on 1/9/2016.
 */
public class SQLiteLocalStorage implements DataStorage {
    public static final String LOG_TAG = "SQLiteLocalStorage";

    SQLiteHelper dbHelper;
    SQLiteDatabase db;
    private String[] allDeckTableColumns = {dbHelper.DECK_deck_id, dbHelper.DECK_deck_name};
    private String[] allCardTableColumns = {dbHelper.CARD_card_id, dbHelper.DECK_deck_id, dbHelper.CARD_question, dbHelper.CARD_answer};

    public SQLiteLocalStorage(Context context){
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException{
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    @Override
    public void addDeck(FlashCardDeck deck) {
        try {
            ContentValues values = new ContentValues();
            values.put(dbHelper.DECK_deck_name, deck.getDeckName());

            db.insert(SQLiteHelper.DECK_TABLE, null, values);

        }catch(Exception e){
            Log.e(LOG_TAG, "ERROR: " + e.getMessage());
        }
    }

    @Override
    public void addCard(FlashCardSingleCard card, int deckId) {
        try {
            ContentValues values = new ContentValues();
            values.put(dbHelper.DECK_deck_id, deckId);
            values.put(dbHelper.CARD_question, card.getQuestion());
            values.put(dbHelper.CARD_answer, card.getAnswer());

            db.insert(SQLiteHelper.DECK_TABLE, null, values);

        }catch(Exception e){
            Log.e(LOG_TAG, "ERROR: " + e.getMessage());
        }
    }

    @Override
    public void modifyDeck(FlashCardDeck deck) {
        try {

        }catch(Exception e){
            Log.e(LOG_TAG, "ERROR: " + e.getMessage());
        }
    }

    @Override
    public void modifyCard(FlashCardSingleCard card, int deckId) {
        try {

        }catch(Exception e){
            Log.e(LOG_TAG, "ERROR: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<FlashCardDeck> getAllDecks() {
        try {
            ArrayList<FlashCardDeck> deck = new ArrayList<FlashCardDeck>();
            Cursor cursor = db.query(dbHelper.DECK_TABLE, allDeckTableColumns, null, null, null, null, null);

            return deck;
        }catch(Exception e){
            Log.e(LOG_TAG, "ERROR: " + e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<FlashCardSingleCard> getAllCardsForDeck(int deckId) {
        try {
            ArrayList<FlashCardSingleCard> card = new ArrayList<FlashCardSingleCard>();
            Cursor cursor = db.query(dbHelper.CARD_TABLE, allCardTableColumns, dbHelper.DECK_deck_id+ " = " +deckId, null, null, null, null);

            return null;
        }catch(Exception e){
            Log.e(LOG_TAG, "ERROR: " + e.getMessage());
            return null;
        }
    }

    // Maybe this will be used for when you select a deck from the list?
    @Override
    public FlashCardDeck getDeck() {
        try {
            return null;
        }catch(Exception e){
            Log.e(LOG_TAG, "ERROR: " + e.getMessage());
            return null;
        }
    }

    @Override
    public FlashCardSingleCard getCard(int deckId) {
        try {
            return null;
        }catch(Exception e){
            Log.e(LOG_TAG, "ERROR: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteDeck() {
        try {

        }catch(Exception e){
            Log.e(LOG_TAG, "ERROR: " + e.getMessage());
        }
    }

    @Override
    public void deleteCard(int deckId) {
        try {

        }catch(Exception e){
            Log.e(LOG_TAG, "ERROR: " + e.getMessage());
        }
    }
}
