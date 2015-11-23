package com.ojm.flashcardapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by omar on 11/20/15.
 */
public class CreateDeckActivity extends Activity {
    @Bind(R.id.next) Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_create_flash_card);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.next)
    public void nextButton(View view) {
        GenerateDeckLocalSheet localSheet = new GenerateDeckLocalSheet();

        List<Map<Object, Object>> cards = new ArrayList<Map<Object, Object>>();

        cards.add(0, <"Is this a question?", "Yes">);
        cards.put("What's your name?", "Omar");
        cards.put("What band was Mark Kozelek in?", "Red House Painters");

        /*
            This looks terrible. Here's an idea:
            - Make an individual card class that's supposed to take in necessary values.
            - Some parts can be optional.
            - Then we make an array of these cards. Sound good?

         */


        FlashCardDeck deck = new FlashCardDeck("Test Deck", "flip-to-view", cards);


        localSheet.createXMLFile(deck);

        Intent intent = new Intent(CreateDeckActivity.this, FlashCardListActivity.class);
        startActivity(intent);
    }
}
