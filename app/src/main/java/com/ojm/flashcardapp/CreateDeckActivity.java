package com.ojm.flashcardapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
    @Bind(R.id.deckNameTextView) Button deckNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_deck);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.next)
    public void nextButton(View view) {
        DataStorage localStorage = new SQLiteLocalStorage(getApplicationContext());

        String deckName = deckNameTextView.getText().toString();
        String[] deckNameLetters = deckName.split("");

        if(deckName.isEmpty() || deckNameLetters.length > 3){
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Deck name is too short. Your deck name must contain more than three letters.")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }else {
            Deck deck = new Deck(deckName, "flip-to-view", null);
            localStorage.addDeck(deck);

            Intent intent = new Intent(CreateDeckActivity.this, CreateCardActivity.class);
            startActivity(intent);
        }
    }
}
