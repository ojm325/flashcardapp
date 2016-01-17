package com.ojm.flashcardapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ojm.flashcardapp.Cards.FlashCardDeck;
import com.ojm.flashcardapp.Cards.FlashCardSingleCard;
import com.ojm.flashcardapp.Storage.DataStorage;
import com.ojm.flashcardapp.Storage.SQLiteHelper;
import com.ojm.flashcardapp.Storage.SQLiteLocalStorage;

import java.util.ArrayList;

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
        DataStorage localStorage = new SQLiteLocalStorage(getApplicationContext());

        ArrayList<FlashCardSingleCard> cards = new ArrayList<FlashCardSingleCard>();

        FlashCardSingleCard card1 = new FlashCardSingleCard("Is this a question?", null, null, "Yes", null);
        FlashCardSingleCard card2 = new FlashCardSingleCard("What's your name?", null, null, "Omar", null);
        FlashCardSingleCard card3 = new FlashCardSingleCard("What band was Mark Kozelek in?", null, null, "Red House Painters", null);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        /*
            This looks terrible. Here's an idea:
            - Make an individual card class that's supposed to take in necessary values.
            - Some parts can be optional.
            - Then we make an array of these cards. Sound good?

         */


        FlashCardDeck deck = new FlashCardDeck("Test Deck", "flip-to-view", cards);

        localStorage.addDeck(deck);
        for(int i = 0; i < cards.size(); i++) {
            localStorage.addCard(cards.get(i), 0);
        }

        /*
        ArrayList<FlashCardSingleCard> arr = localStorage.getAllCardsForDeck(0);
        for(int i = 0; i < arr.size(); i++){
            FlashCardSingleCard card = arr.get(i);
            Log.d("CARD IN DECK: ", card.getQuestion());
        }
        */


        Intent intent = new Intent(CreateDeckActivity.this, FlashCardListActivity.class);
        startActivity(intent);
    }
}
