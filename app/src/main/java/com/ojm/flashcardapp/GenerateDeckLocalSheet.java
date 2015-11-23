package com.ojm.flashcardapp;

import com.jamesmurty.utils.XMLBuilder2;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by omar on 11/22/15.
 */
public class GenerateDeckLocalSheet {

    public GenerateDeckLocalSheet(){}

    protected void createXMLFile(FlashCardDeck deck){
        XMLBuilder2 builder = XMLBuilder2.create("FlashCardAppDeck")
                .e("Deck").a("DeckName", deck.deckName()).a("DeckType", deck.deckType());

        for(int i = 0; i < deck.cards().size(); i++){
            builder.e("Card").a("Question", deck.cards().values().);
        }

    }
}
