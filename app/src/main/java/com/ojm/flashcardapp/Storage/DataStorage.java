package com.ojm.flashcardapp.Storage;

import com.ojm.flashcardapp.Cards.Deck;
import com.ojm.flashcardapp.Cards.FlashCard;

import java.util.ArrayList;

/**
 * Created by Omar on 1/9/2016.
 */
public interface DataStorage {
    public void addDeck(Deck deck);
    public void addCard(FlashCard card, int deckId);
    public void modifyDeck(Deck deck);
    public void modifyCard(FlashCard card, int deckId);
    public ArrayList<Deck> getAllDecks();
    public ArrayList<FlashCard> getAllCardsForDeck(int deckId);
    public Deck getDeck(int deckId);
    public FlashCard getCard(int deckId);
    public void deleteDeck(int deckId);
    public void deleteCard(int deckId, int cardId);
}
