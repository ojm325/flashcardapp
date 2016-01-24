package com.ojm.flashcardapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ojm.flashcardapp.Cards.Deck;
import com.ojm.flashcardapp.Cards.FlashCard;
import com.ojm.flashcardapp.Storage.DataStorage;
import com.ojm.flashcardapp.Storage.SQLiteLocalStorage;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Omar on 1/22/2016.
 */
public class CardListActivity extends Activity {
    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.cardList) ListView cardList;

    private DataStorage dataStorage;
    private ArrayAdapter<String> adapter;
    private int deckId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        ButterKnife.bind(this);

        dataStorage = new SQLiteLocalStorage(this);

        deckId = getIntent().getIntExtra("DECK_ID", 0);

        ArrayList<FlashCard> cards = dataStorage.getAllCardsForDeck(deckId);

        adapter = new ArrayAdapter<String>(this, R.layout.deck_list_item);

        for(int i = 0; i < cards.size(); i++){
            FlashCard card = cards.get(i);
            adapter.add(card.getQuestion());
        }

        cardList.setAdapter(adapter);

        Log.d("DECK_ID", ""+deckId);
    }

    @OnClick(R.id.fab)
    public void fab(View view){
        Intent intent = new Intent(CardListActivity.this, CreateCardActivity.class);
        intent.putExtra("DECK_ID", deckId);
        startActivity(intent);
    }

}
