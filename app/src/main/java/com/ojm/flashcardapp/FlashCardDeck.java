package com.ojm.flashcardapp;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by omar on 11/22/15.
 */
public class FlashCardDeck {
    private String deckName;
    private String deckType;
    private LinkedHashMap<Object, Object> cards;

    public FlashCardDeck(String deckName, String deckType, LinkedHashMap<Object, Object> cards){
        this.deckName = deckName;
        this.deckType = deckType;
        this.cards = cards;
    }
    public String deckName(){
        return this.deckName;
    }
    public String deckType(){
        return this.deckType;
    }
    public LinkedHashMap<Object, Object> cards(){
        return this.cards;
    }
}
