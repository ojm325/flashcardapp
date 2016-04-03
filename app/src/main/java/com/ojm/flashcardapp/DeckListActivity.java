package com.ojm.flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    @Bind(R.id.deckListRecyclerView) RecyclerView deckListRecyclerView;

    private List<Deck> decks;

    private DataStorage dataStorage;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        dataStorage = new SQLiteDeckCardStorage(DeckListActivity.this);


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
    protected void onResume() {
        super.onResume();

        addDecksToList();
        mAdapter.notifyDataSetChanged();
    }

    protected void addDecksToList(){
        decks = dataStorage.getAllDecks();

        mAdapter = new DeckListAdapter(this, decks);

        deckListRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(this);
        deckListRecyclerView.setLayoutManager(mLayoutManager);
    }
}
