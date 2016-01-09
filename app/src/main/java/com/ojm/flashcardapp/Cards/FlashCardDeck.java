package com.ojm.flashcardapp.Cards;

import java.util.ArrayList;

/**
 * Created by omar on 11/22/15.
 */
public class FlashCardDeck {
    private String deckName;
    private String deckType;
    private ArrayList<FlashCardSingleCard> cards;

    public FlashCardDeck(String deckName, String deckType, ArrayList cards){
        this.deckName = deckName;
        this.deckType = deckType;
        this.cards = cards;
    }
    public String getDeckName(){
        return this.deckName;
    }
    public String getDeckType(){
        return this.deckType;
    }
    public ArrayList<FlashCardSingleCard> getCards(){
        return this.cards;
    }
}
