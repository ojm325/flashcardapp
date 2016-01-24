package com.ojm.flashcardapp.Storage;

import com.ojm.flashcardapp.Cards.Deck;
import com.ojm.flashcardapp.Cards.FlashCard;

import java.util.ArrayList;

/**
 * Created by omar on 11/22/15.
 */
public class GoogleDriveStorage implements DataStorage {

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
    public void deleteDeck(int deckId) {

    }

    @Override
    public void deleteCard(int deckId, int cardId) {

    }
}
