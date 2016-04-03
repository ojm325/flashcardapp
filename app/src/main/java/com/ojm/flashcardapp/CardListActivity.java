package com.ojm.flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ojm.flashcardapp.Cards.Deck;
import com.ojm.flashcardapp.Cards.FlashCard;
import com.ojm.flashcardapp.CreationViews.CreateCardActivity;
import com.ojm.flashcardapp.Storage.DataStorage;
import com.ojm.flashcardapp.Storage.SQLiteDeckCardStorage;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Omar on 1/22/2016.
 *
 * Associated layout(s): activity_card_list.xml, deck_list_item.xml, content_card_list.xml
 */
public class CardListActivity extends BaseActivity {
    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.cardList) ListView cardList;

    private DataStorage dataStorage;
    private ArrayAdapter<String> adapter;
    private int deckId;
    private Deck deck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        ButterKnife.bind(this);

        //cardList.deferNotifyDataSetChanged();

        dataStorage = new SQLiteDeckCardStorage(this);

        deckId = getIntent().getIntExtra("DECK_ID", 0);

        deck = dataStorage.getDeck(deckId);

        setTitle(deck.getDeckName());

        ArrayList<FlashCard> cards = deck.getCards();

        adapter = new ArrayAdapter<>(this, R.layout.deck_list_item);

        for(int i = 0; i < cards.size(); i++){
            FlashCard card = cards.get(i);
            adapter.add(card.getQuestion());
        }

        cardList.setAdapter(adapter);

        Log.d("DECK_ID", ""+deckId);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            Intent refresh = new Intent(this, CardListActivity.class);

            deckId = getIntent().getIntExtra("DECK_ID", 0);
            refresh.putExtra("DECK_ID", deckId);
            startActivity(refresh);
            this.finish();
        }
    }

    @OnClick(R.id.fab)
    public void fab(View view){
        Intent intent = new Intent(CardListActivity.this, CreateCardActivity.class);
        intent.putExtra("DECK_ID", deckId);
        startActivityForResult(intent, 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_flash_card_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, DeckListActivity.class);
                startActivity(intent);
                this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
