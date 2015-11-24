package com.ojm.flashcardapp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
