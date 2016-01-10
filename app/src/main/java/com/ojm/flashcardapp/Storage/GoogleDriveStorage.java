package com.ojm.flashcardapp.Storage;

import com.ojm.flashcardapp.Cards.FlashCardDeck;
import com.ojm.flashcardapp.Cards.FlashCardSingleCard;

import java.util.ArrayList;

/**
 * Created by omar on 11/22/15.
 */
public class GoogleDriveStorage implements DataStorage {

    @Override
    public void addDeck(FlashCardDeck deck) {

    }

    @Override
    public void addCard(FlashCardSingleCard card, int deckId) {

    }

    @Override
    public void modifyDeck(FlashCardDeck deck) {

    }

    @Override
    public void modifyCard(FlashCardSingleCard card, int deckId) {

    }

    @Override
    public ArrayList<FlashCardDeck> getAllDecks() {
        return null;
    }

    @Override
    public ArrayList<FlashCardSingleCard> getAllCardsForDeck(int deckId) {
        return null;
    }

    @Override
    public FlashCardDeck getDeck() {
        return null;
    }

    @Override
    public FlashCardSingleCard getCard(int deckId) {
        return null;
    }

    @Override
    public void deleteDeck() {

    }

    @Override
    public void deleteCard(int deckId) {

    }
}
