package com.ojm.flashcardapp.Storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ojm.flashcardapp.Cards.Deck;
import com.ojm.flashcardapp.Cards.FlashCard;
import com.ojm.flashcardapp.Cards.FlashCardMultipleChoice;
import com.ojm.flashcardapp.Cards.FlashCardQuestionAndAnswer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 * Created by Omar on 1/9/2016.
 */
public class SQLiteDeckCardStorage implements DataStorage {
    public static final String LOG_TAG = "SQLiteDeckCardStorage";

    SQLiteHelper dbHelper;
    SQLiteDatabase db;
    private String[] allDeckTableColumns = {dbHelper.DECK_deck_id, dbHelper.DECK_deck_name, dbHelper.DECK_description};
    private String[] allCardTableColumns = {dbHelper.CARD_card_id, dbHelper.DECK_deck_id, dbHelper.CARD_type, dbHelper.CARD_question, dbHelper.CARD_notes};
    private String[] allCardChoicesTableColumns = {dbHelper.CARD_CHOICES_id, dbHelper.DECK_deck_id, dbHelper.CARD_card_id, dbHelper.CARD_CHOICES_answer_choice, dbHelper.CARD_CHOICES_is_answer};

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
            values.put(dbHelper.DECK_description, deck.getDescription());

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
            values.put(dbHelper.CARD_type, card.getCardType());
            values.put(dbHelper.CARD_question, card.getQuestion());
            values.put(dbHelper.CARD_notes, card.getCardNote());

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
                    String description = cursor.getString(cursor.getColumnIndex(dbHelper.DECK_description));


                    Deck deck = new Deck(deckId, deckName, cards, description);
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
                    int cardId = cursor.getInt(cursor.getColumnIndex(dbHelper.CARD_card_id));
                    String cardType = cursor.getString(cursor.getColumnIndex(dbHelper.CARD_type));
                    String question = cursor.getString(cursor.getColumnIndex(dbHelper.CARD_question));
                    String cardNote = cursor.getString(cursor.getColumnIndex(dbHelper.CARD_notes));

                    TreeMap<String, Boolean> choices = getAnswerChoicesForCard(deckId, cardId);

                    if(cardType.equals("Question and Answer")){
                        FlashCardQuestionAndAnswer card = new FlashCardQuestionAndAnswer(cardType, question, null, cardNote);

                        card.setAnswers(choices.firstKey());

                        cards.add(card);
                    }else{
                        FlashCardMultipleChoice card = new FlashCardMultipleChoice(cardType, question, null, cardNote);

                        card.setChoices(choices);

                        cards.add(card);
                    }

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

    @Override
    public Deck getDeck(int deckId) {
        try {
            this.open();

            Deck deck = new Deck(deckId, null, null, null);

            Cursor cursor = db.query(dbHelper.DECK_TABLE, allDeckTableColumns, dbHelper.DECK_deck_id+ " = " +deckId, null, null, null, null);

            if(cursor.moveToFirst()) {
                while (!cursor.isAfterLast()){
                    String deckName = cursor.getString(cursor.getColumnIndex(dbHelper.DECK_deck_name));
                    ArrayList<FlashCard>cards = this.getAllCardsForDeck(deckId);
                    String description = cursor.getString(cursor.getColumnIndex(dbHelper.DECK_description));

                    deck.setDeckName(deckName);
                    deck.setCards(cards);
                    deck.setDescription(description);

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
    public int getLastInsertedCardId(int deckId) {
        try {
            this.open();

            Cursor cursor = db.query(dbHelper.CARD_TABLE, allCardTableColumns, dbHelper.DECK_deck_id+ " = " +deckId, null, null, null, dbHelper.CARD_card_id+" DESC", "1");

            int cardId = -1;

            if(cursor.moveToFirst()) {
                cardId = cursor.getInt(cursor.getColumnIndex(dbHelper.CARD_card_id));
            }

            this.close();
            return cardId;
        }catch(Exception e){
            Log.e(LOG_TAG, "getLastInsertedCardId ERROR: " + e.getMessage());
            return -1;
        }
    }

    @Override
    public void deleteDeck(int deckId) {
        try {
            this.open();
            db.delete(dbHelper.DECK_TABLE, dbHelper.DECK_deck_id + " = ?", new String[]{Integer.toString(deckId)});

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
    public void setAnswerChoiceForCard(int deckId, int cardId, String answerChoice, boolean isAnswer) {
        try {
            this.open();

            int isAnswerNum = 0;

            if(isAnswer){ isAnswerNum = 1; }

            ContentValues values = new ContentValues();
            values.put(dbHelper.DECK_deck_id, deckId);
            values.put(dbHelper.CARD_card_id, cardId);
            values.put(dbHelper.CARD_CHOICES_answer_choice, answerChoice);
            values.put(dbHelper.CARD_CHOICES_is_answer, isAnswer);


            //values.put(dbHelper.CARD_choice_id_answer, card.getAnswer());

            db.insert(SQLiteHelper.CARD_CHOICES_TABLE, null, values);

            this.close();

        }catch(Exception e){
            Log.e(LOG_TAG, "setAnswerChoicesForCard ERROR: " + e.getMessage());
        }
    }

    @Override
    public void modifyAnswerChoiceForCard(int cardChoiceId, int deckId, int cardId) {

    }

    @Override
    public TreeMap getAnswerChoicesForCard(int deckId, int cardId) {
        try {
            this.open();

            TreeMap<String, Boolean> choices = new TreeMap<>();

            Cursor cursor = db.query(dbHelper.CARD_CHOICES_TABLE,
                    allCardChoicesTableColumns,
                    dbHelper.DECK_deck_id+ " = " +deckId+ " AND "+dbHelper.CARD_card_id+" = " +cardId,
                    null, null, null, null);

            if(cursor.moveToFirst()) {
                while (!cursor.isAfterLast()){
                    String choiceString = cursor.getString(cursor.getColumnIndex(dbHelper.CARD_CHOICES_answer_choice));
                    int isAnswerInt = cursor.getInt(cursor.getColumnIndex(dbHelper.CARD_CHOICES_is_answer));
                    boolean isAnswer;

                    if(isAnswerInt == 1){
                        isAnswer = true;
                    }else{
                        isAnswer = false;
                    }

                    choices.put(choiceString, isAnswer);

                    cursor.moveToNext();
                }
            }

            this.close();

            return choices;
        }catch(Exception e){
            Log.e(LOG_TAG, "getAnswerChoicesForCard ERROR: " + e.getMessage());
            return null;
        }
    }
}
