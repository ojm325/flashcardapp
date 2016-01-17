package com.ojm.flashcardapp.Storage;

import com.ojm.flashcardapp.Cards.FlashCardDeck;
import com.ojm.flashcardapp.Cards.FlashCardSingleCard;

import java.util.ArrayList;

/**
 * Created by Omar on 1/9/2016.
 */
public interface DataStorage {
    public void addDeck(FlashCardDeck deck);
    public void addCard(FlashCardSingleCard card, int deckId);
    public void modifyDeck(FlashCardDeck deck);
    public void modifyCard(FlashCardSingleCard card, int deckId);
    public ArrayList<FlashCardDeck> getAllDecks();
    public ArrayList<FlashCardSingleCard> getAllCardsForDeck(int deckId);
    public FlashCardDeck getDeck();
    public FlashCardSingleCard getCard(int deckId);
    public void deleteDeck(int deckId);
    public void deleteCard(int deckId, int cardId);
}
