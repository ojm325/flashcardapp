package com.ojm.flashcardapp.CreationViews;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ojm.flashcardapp.BaseActivity;
import com.ojm.flashcardapp.CardListActivity;
import com.ojm.flashcardapp.Cards.Deck;
import com.ojm.flashcardapp.R;
import com.ojm.flashcardapp.Storage.DataStorage;
import com.ojm.flashcardapp.Storage.SQLiteDeckCardStorage;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by omar on 11/20/15.
 *
 * Associated layout(s): activity_create_deck.xml
 */
public class CreateDeckActivity extends BaseActivity {
    @Bind(R.id.next) Button next;
    @Bind(R.id.deckNameTextField) TextView deckNameTextField;
    @Bind(R.id.deckDescriptionTextField) TextView deckDescriptionTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_deck);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.next)
    public void nextButton(View view) {
        DataStorage localStorage = new SQLiteDeckCardStorage(getApplicationContext());

        String deckName = deckNameTextField.getText().toString();
        String deckDescription = deckDescriptionTextField.getText().toString();
        String[] deckNameLetters = deckName.split("");

        if(deckName.isEmpty() || deckNameLetters.length < 4){
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Deck name is too short. Your deck name must contain more than three letters.")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }else {
            Deck deck = new Deck(-1, deckName, null, deckDescription);
            localStorage.addDeck(deck);
            ArrayList<Deck> tempDeck = localStorage.getAllDecks();
            int deckId = tempDeck.get(tempDeck.size()-1).getDeckId();

            Intent intent = new Intent(CreateDeckActivity.this, CardListActivity.class);
            intent.putExtra("DECK_ID", deckId);
            startActivity(intent);
        }
    }
}
