package com.ojm.flashcardapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ojm.flashcardapp.Cards.Deck;
import com.ojm.flashcardapp.Cards.FlashCard;
import com.ojm.flashcardapp.Storage.DataStorage;
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
        setContentView(R.layout.content_create_deck);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.next)
    public void nextButton(View view) {
        DataStorage localStorage = new SQLiteLocalStorage(getApplicationContext());

        ArrayList<FlashCard> cards = new ArrayList<FlashCard>();

        FlashCard card1 = new FlashCard("Is this a question?", null, null, "Yes", null);
        FlashCard card2 = new FlashCard("What's your name?", null, null, "Omar", null);
        FlashCard card3 = new FlashCard("What band was Mark Kozelek in?", null, null, "Red House Painters", null);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);


        Deck deck = new Deck("Test Deck", "flip-to-view", cards);

        localStorage.addDeck(deck);
        for(int i = 0; i < cards.size(); i++) {
            localStorage.addCard(cards.get(i), 0);
        }


        ArrayList<FlashCard> arr = localStorage.getAllCardsForDeck(0);

        for(int i = 0; i < arr.size(); i++){
            FlashCard card = arr.get(i);
            Log.d("CARD IN DECK", card.getQuestion());
        }



        Intent intent = new Intent(CreateDeckActivity.this, CreateCardsActivity.class);
        startActivity(intent);
    }
}
