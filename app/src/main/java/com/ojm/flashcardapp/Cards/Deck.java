package com.ojm.flashcardapp.Cards;

import java.util.ArrayList;

/**
 * Created by omar on 11/22/15.
 */
public class Deck {
    private int deckId;
    private String deckName;
    private ArrayList<FlashCard> cards;
    private String description;

    public Deck(int deckId, String deckName, ArrayList cards, String description){
        this.deckId = deckId;
        this.deckName = deckName;
        this.cards = cards;
        this.description = description;
    }
    public void setDeckName(String dN){
        this.deckName = dN;
    }

    public void setDeckId(int deckId){ this.deckId = deckId; }
    public void setCards(ArrayList c){
        this.cards = c;
    }
    public String getDeckName(){
        return this.deckName;
    }
    public int getDeckId(){
        return this.deckId;
    }
    public ArrayList<FlashCard> getCards(){
        return this.cards;
    }
    public String getDescription(){ return this.description; }
    public void setDescription(String description){ this.description = description; }
}
