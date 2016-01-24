package com.ojm.flashcardapp.Cards;

import java.util.ArrayList;

/**
 * Created by omar on 11/22/15.
 */
public class Deck {
    private String deckName;
    private String deckType;
    private ArrayList<FlashCard> cards;

    public Deck(String deckName, String deckType, ArrayList cards){
        this.deckName = deckName;
        this.deckType = deckType;
        this.cards = cards;
    }
    public void setDeckName(String dN){
        this.deckName = dN;
    }
    //Do I even need this
    public void setDeckType(String dT){
        this.deckType = dT;
    }
    public void setCards(ArrayList c){
        this.cards = c;
    }
    public String getDeckName(){
        return this.deckName;
    }
    public String getDeckType(){
        return this.deckType;
    }
    public ArrayList<FlashCard> getCards(){
        return this.cards;
    }
}
