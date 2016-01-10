package com.ojm.flashcardapp.Storage;

/**
 * Created by Omar on 1/9/2016.
 */
public interface DataStorage {
    public void saveDeck();
    public void saveCard();
    public void modifyDeck();
    public void modifyCard();
    public void getAllDecks();
    public void getAllCards();
    public void getDeck();
    public void getCard();
}
