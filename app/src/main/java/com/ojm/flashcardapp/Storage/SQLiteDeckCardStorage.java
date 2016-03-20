package com.ojm.flashcardapp.Storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ojm.flashcardapp.Cards.Deck;
import com.ojm.flashcardapp.Cards.FlashCard;
import com.ojm.flashcardapp.Cards.FlashCardQuestionAndAnswer;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Omar on 1/9/2016.
 */
public class SQLiteDeckCardStorage implements DataStorage {
    public static final String LOG_TAG = "SQLiteDeckCardStorage";

    SQLiteHelper dbHelper;
    SQLiteDatabase db;
    private String[] allDeckTableColumns = {dbHelper.DECK_deck_id, dbHelper.DECK_deck_name};
    private String[] allCardTableColumns = {dbHelper.CARD_card_id, dbHelper.DECK_deck_id, dbHelper.CARD_question, dbHelper.CARD_choice_id_answer};

    public SQLiteDeckCardStorage(Context context){
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException{
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    @Override
    public void addDeck(Deck deck) {
        try {
            this.open();
            ContentValues values = new ContentValues();
            values.put(dbHelper.DECK_deck_name, deck.getDeckName());

            db.insert(SQLiteHelper.DECK_TABLE, null, values);

            this.close();

        }catch(Exception e){
            Log.e(LOG_TAG, "addDeck ERROR: " + e.getMessage());
        }
    }

    @Override
    public void addCard(FlashCard card, int deckId) {
        try {
            this.open();

            ContentValues values = new ContentValues();
            values.put(dbHelper.DECK_deck_id, deckId);
            values.put(dbHelper.CARD_question, card.getQuestion());
            //values.put(dbHelper.CARD_choice_id_answer, card.getAnswer());

            db.insert(SQLiteHelper.CARD_TABLE, null, values);

            this.close();

        }catch(Exception e){
            Log.e(LOG_TAG, "addCard ERROR: " + e.getMessage());
        }
    }

    @Override
    public void modifyDeck(Deck deck) {
        try {

        }catch(Exception e){
            Log.e(LOG_TAG, "modifyDeck ERROR: " + e.getMessage());
        }
    }

    @Override
    public void modifyCard(FlashCard card, int deckId) {
        try {

        }catch(Exception e){
            Log.e(LOG_TAG, "modifyCard ERROR: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Deck> getAllDecks() {
        try {
            this.open();

            ArrayList<Deck> decks = new ArrayList<>();
            Cursor cursor = db.query(dbHelper.DECK_TABLE, allDeckTableColumns, null, null, null, null, null);

            if(cursor.moveToFirst()) {
                while (!cursor.isAfterLast()){
                    int deckId = cursor.getInt(cursor.getColumnIndex(dbHelper.DECK_deck_id));
                    String deckName = cursor.getString(cursor.getColumnIndex(dbHelper.DECK_deck_name));
                    ArrayList<FlashCard> cards = this.getAllCardsForDeck(deckId);


                    Deck deck = new Deck(deckId, deckName, cards);
                    decks.add(deck);

                    cursor.moveToNext();
                }
            }

            this.close();

            return decks;
        }catch(Exception e){
            Log.e(LOG_TAG, "getAllDecks ERROR: " + e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<FlashCard> getAllCardsForDeck(int deckId) {
        try {
            this.open();

            ArrayList<FlashCard> cards = new ArrayList<>();
            Cursor cursor = db.query(dbHelper.CARD_TABLE, allCardTableColumns, dbHelper.DECK_deck_id+ " = " +deckId, null, null, null, null);

            if(cursor.moveToFirst()) {
                while (!cursor.isAfterLast()){
                    String question = cursor.getString(cursor.getColumnIndex(dbHelper.CARD_question));
                    String answer = cursor.getString(cursor.getColumnIndex(dbHelper.CARD_choice_id_answer));

                    FlashCard card = new FlashCardQuestionAndAnswer(question, null, answer, null);
                    cards.add(card);

                    cursor.moveToNext();
                }
            }

            this.close();

            return cards;
        }catch(Exception e){
            Log.e(LOG_TAG, "getAllCardsForDeck ERROR: " + e.getMessage());
            return null;
        }
    }

    // Maybe this will be used for when you select a deck from the list?
    @Override
    public Deck getDeck(int deckId) {
        try {
            this.open();

            Deck deck = new Deck(deckId, null, null);

            Cursor cursor = db.query(dbHelper.DECK_TABLE, allDeckTableColumns, dbHelper.DECK_deck_id+ " = " +deckId, null, null, null, null);

            if(cursor.moveToFirst()) {
                while (!cursor.isAfterLast()){
                    String deckName = cursor.getString(cursor.getColumnIndex(dbHelper.DECK_deck_name));
                    ArrayList<FlashCard>cards = this.getAllCardsForDeck(deckId);

                    deck.setDeckName(deckName);
                    deck.setCards(cards);

                    cursor.moveToNext();
                }
            }

            this.close();

            return deck;
        }catch(Exception e){
            Log.e(LOG_TAG, "getDeck ERROR: " + e.getMessage());
            return null;
        }
    }

    @Override
    public FlashCard getCard(int deckId) {
        try {
            return null;
        }catch(Exception e){
            Log.e(LOG_TAG, "getCard ERROR: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteDeck(int deckId) {
        try {
            this.open();
            db.delete(dbHelper.DECK_TABLE, dbHelper.DECK_deck_id+ " = ?", new String[]{Integer.toString(deckId)});

            // We also have to delete all of the cards from that deck.
            db.delete(dbHelper.CARD_TABLE, dbHelper.DECK_deck_id+ " = ?", new String[]{Integer.toString(deckId)});

            this.close();
        }catch(Exception e){
            Log.e(LOG_TAG, "deleteDeck ERROR: " + e.getMessage());
        }
    }

    @Override
    public void deleteCard(int deckId, int cardId) {
        try {
            this.open();
            db.delete(dbHelper.CARD_TABLE, dbHelper.CARD_card_id+ " = ?", new String[]{Integer.toString(cardId)});
            this.close();
        }catch(Exception e){
            Log.e(LOG_TAG, "deleteCard ERROR: " + e.getMessage());
        }
    }

    @Override
    public void setAnswerChoicesForCard(int deckId, int cardId) {
        
    }

    @Override
    public ArrayList<String> getAnswerChoicesForCard(int deckId, int cardId) {
        return null;
    }
}
