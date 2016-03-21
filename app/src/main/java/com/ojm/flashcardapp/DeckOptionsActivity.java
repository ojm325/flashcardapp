package com.ojm.flashcardapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ojm.flashcardapp.Cards.Deck;
import com.ojm.flashcardapp.Storage.DataStorage;
import com.ojm.flashcardapp.Storage.SQLiteDeckCardStorage;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Omar on 1/23/2016.
 *
 * Associated layout(s): activity_deck_options.xml
 */
public class DeckOptionsActivity extends BaseActivity {
    @Bind(R.id.useDeckPracticeButton) Button useDeckPracticeButton;
    @Bind(R.id.useDeckChallangeButton) Button useDeckChallengeButton;
    @Bind(R.id.deckStatsButton) Button deckStatsButton;
    @Bind(R.id.modifyDeckButton) Button modifyDeckButton;
    @Bind(R.id.deleteDeckButton) Button deleteDeckButton;
    @Bind(R.id.deckNameTextView) TextView deckNameTextView;
    @Bind(R.id.deckDescriptionTextView) TextView deckDescriptionTextView;
    @Bind(R.id.cardsInDeckTextView) TextView cardsInDeckTextView;
    @Bind(R.id.deckAlertTextView) TextView deckAlertTextView;

    protected DataStorage dataStorage;
    protected int deckId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_options);
        ButterKnife.bind(this);

        dataStorage = new SQLiteDeckCardStorage(this);
        deckId = getIntent().getIntExtra("DECK_ID", 0);
        Log.d("DECK_ID", "" + deckId);

        Deck deck = dataStorage.getDeck(deckId);
        deckNameTextView.setText(deck.getDeckName());
        setTitle(deck.getDeckName());

        Log.d("Cards in deck", "" + deck.getCards().size());
        cardsInDeckTextView.setText("Cards In Deck: " + deck.getCards().size());

        deckDescriptionTextView.setText(deck.getDescription());

        // Leaving these outside of the if statement from below since they're not done yet.
        useDeckChallengeButton.setEnabled(false);
        deckStatsButton.setEnabled(false);

        if (deck.getCards().size() <= 0) {
            deckAlertTextView.setText("This deck has no cards. Click the 'Modify Deck' button to add cards.");
            useDeckPracticeButton.setEnabled(false);
        }else{
            deckAlertTextView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_flash_card_list, menu);
        return true;
    }

    @OnClick(R.id.useDeckPracticeButton)
    public void setUseDeckPracticeButton(View view) {
        Intent intent = new Intent(DeckOptionsActivity.this, QuizTakingActivity.class);
        intent.putExtra("DECK_ID", deckId);
        startActivity(intent);
    }

    @OnClick(R.id.useDeckChallangeButton)
    public void setUseDeckChallengeButton(View view){

    }

    @OnClick(R.id.deckStatsButton)
    public void setDeckStatsButton(View view) {

    }

    @OnClick(R.id.modifyDeckButton)
    public void setModifyDeckButton(View view) {
        Intent intent = new Intent(DeckOptionsActivity.this, CardListActivity.class);
        intent.putExtra("DECK_ID", deckId);
        startActivity(intent);
    }

    @OnClick(R.id.deleteDeckButton)
    public void setDeleteDeckButton(View view) {
        new AlertDialog.Builder(DeckOptionsActivity.this)
                .setTitle("Delete Deck")
                .setMessage("Are you sure you want to delete this deck?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dataStorage.deleteDeck(deckId);

                        Intent intent = new Intent(DeckOptionsActivity.this, DeckListActivity.class);
                        setResult(RESULT_OK, intent);
                        DeckOptionsActivity.this.finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {}})
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
