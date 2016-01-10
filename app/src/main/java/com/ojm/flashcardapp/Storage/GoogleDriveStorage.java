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
    public void addCard(FlashCardSingleCard card) {

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
