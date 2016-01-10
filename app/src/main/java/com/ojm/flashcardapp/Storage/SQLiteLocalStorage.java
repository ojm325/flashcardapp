package com.ojm.flashcardapp.Storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ojm.flashcardapp.Cards.FlashCardDeck;
import com.ojm.flashcardapp.Cards.FlashCardSingleCard;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Omar on 1/9/2016.
 */
public class SQLiteLocalStorage implements DataStorage {
    SQLiteHelper dbHelper;
    SQLiteDatabase db;
    private String[] allDeckTableColumns = {dbHelper.DECK_id, dbHelper.DECK_deck_name};
    private String[] allCardTableColumns = {dbHelper.CARD_id, dbHelper.CARD_question, dbHelper.CARD_answer};

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

    }

    @Override
    public void addCard(FlashCardSingleCard card) {
        ContentValues values = new ContentValues();
        db.insert(SQLiteHelper.DECK_TABLE, null, values);
    }

    @Override
    public void modifyDeck(FlashCardDeck deck) {

    }

    @Override
    public void modifyCard(FlashCardSingleCard card) {

    }

    @Override
    public ArrayList<FlashCardDeck> getAllDecks() {
        return null;
    }

    @Override
    public ArrayList<FlashCardSingleCard> getAllCards() {
        return null;
    }

    @Override
    public ArrayList getDeck() {
        return null;
    }

    @Override
    public ArrayList getCard() {
        return null;
    }
}
