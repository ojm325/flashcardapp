package com.ojm.flashcardapp.Storage;

import com.ojm.flashcardapp.Cards.Deck;
import com.ojm.flashcardapp.Cards.FlashCard;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/**
 * Created by omar on 11/22/15.
 */
public class WebStorage implements DataStorage {

    @Override
    public void addDeck(Deck deck) {

    }

    @Override
    public void addCard(FlashCard card, int deckId) {

    }

    @Override
    public void modifyDeck(Deck deck) {

    }

    @Override
    public void modifyCard(FlashCard card, int deckId) {

    }

    @Override
    public ArrayList<Deck> getAllDecks() {
        return null;
    }

    @Override
    public ArrayList<FlashCard> getAllCardsForDeck(int deckId) {
        return null;
    }

    @Override
    public Deck getDeck(int deckId) {
        return null;
    }

    @Override
    public FlashCard getCard(int deckId) {
        return null;
    }

    @Override
    public int getLastInsertedCardId(int deckId) {
        return 0;
    }

    @Override
    public void deleteDeck(int deckId) {

    }

    @Override
    public void deleteCard(int deckId, int cardId) {

    }

    @Override
    public void deleteAllAnswerChoicesForCard(int deckId, int cardId) {

    }

    @Override
    public void deleteAnswerChoice(int deckId, int cardId, String choice) {

    }

    @Override
    public void setAnswerChoiceForCard(int deckId, int cardId, String answerChoice, boolean isAnswer) {

    }

    @Override
    public void modifyAnswerChoiceForCard(int cardChoiceId, int deckId, int cardId) {

    }

    @Override
    public TreeMap getAnswerChoicesForCard(int deckId, int cardId) {
        return null;
    }

}
