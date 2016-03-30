package com.ojm.flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.*;

import com.ojm.flashcardapp.Cards.Deck;
import com.ojm.flashcardapp.CreationViews.CreateDeckActivity;
import com.ojm.flashcardapp.Storage.DataStorage;
import com.ojm.flashcardapp.Storage.SQLiteDeckCardStorage;


/*
    Associated layout(s): activity_deck_list.xml, deck_list_item.xml, content_deck_list.xml
 */
public class DeckListActivity extends BaseActivity {

    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.deckList) ListView deckList;

    private List<Deck> decks;

    private DataStorage dataStorage;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        dataStorage = new SQLiteDeckCardStorage(DeckListActivity.this);
        adapter = new ArrayAdapter<>(this, R.layout.deck_list_item);
    }

    /*
        When a row is clicked, it takes you to the Deck Options activity.
     */
    @OnItemClick(R.id.deckList)
    public void setDeckListRowClick(int position){
        Intent intent = new Intent(DeckListActivity.this, DeckOptionsActivity.class);
        intent.putExtra("DECK_ID", decks.get(position).getDeckId());
        startActivity(intent);
    }

    /*
        Add Deck Button, or floating action button
     */
    @OnClick(R.id.fab)
    public void fab(View view){
        Intent intent = new Intent(DeckListActivity.this, CreateDeckActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_flash_card_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.clear();
        addDecksToList();
        adapter.notifyDataSetChanged();
    }

    protected void addDecksToList(){
        decks = dataStorage.getAllDecks();

        for(int i = 0; i < decks.size(); i++){
            Deck deck = decks.get(i);
            adapter.add(deck.getDeckName());
        }

        deckList.setAdapter(adapter);
    }
}
