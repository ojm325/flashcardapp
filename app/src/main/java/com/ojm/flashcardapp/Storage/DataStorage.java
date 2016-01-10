package com.ojm.flashcardapp.Storage;

import com.ojm.flashcardapp.Cards.FlashCardDeck;
import com.ojm.flashcardapp.Cards.FlashCardSingleCard;

import java.util.ArrayList;

/**
 * Created by Omar on 1/9/2016.
 */
public interface DataStorage {
    public void addDeck(FlashCardDeck deck);
    public void addCard(FlashCardSingleCard card);
    public void modifyDeck(FlashCardDeck deck);
    public void modifyCard(FlashCardSingleCard card);
    public ArrayList<FlashCardDeck> getAllDecks();
    public ArrayList<FlashCardSingleCard> getAllCards();
    public ArrayList getDeck();
    public ArrayList getCard();
}
