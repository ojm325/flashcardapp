package com.ojm.flashcardapp;

import android.util.Log;

import com.jamesmurty.utils.XMLBuilder2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by omar on 11/22/15.
 */
public class GenerateDeckLocalSheet {

    public GenerateDeckLocalSheet(){}

    protected void createXMLFile(FlashCardDeck deck){
        XMLBuilder2 builder = XMLBuilder2.create("FlashCardAppDeck")
                .e("Deck").a("DeckName", deck.getDeckName()).a("DeckType", deck.getDeckType());


        for(int i = 0; i < deck.getCards().size(); i++){
            builder.e("Card")
                    .e("Question")
                    .t(deck.getCards().get(i).getQuestion())
                    .up()
                    .e("Answer")
                    .t(deck.getCards().get(i).getAnswer());

        }

        Log.d("XMLBUILDER ", builder.asString());

    }
}
