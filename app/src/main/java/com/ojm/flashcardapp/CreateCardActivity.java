package com.ojm.flashcardapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ojm.flashcardapp.Cards.FlashCard;
import com.ojm.flashcardapp.Cards.FlashCardQuestionAndAnswer;
import com.ojm.flashcardapp.Storage.DataStorage;
import com.ojm.flashcardapp.Storage.SQLiteLocalStorage;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Omar on 1/17/2016.
 */
public class CreateCardActivity extends Activity {
    @Bind(R.id.createCardButton) Button createCardButton;
    @Bind(R.id.questionTextView) TextView questionTextView;
    @Bind(R.id.answerTextView) TextView answerTextView;

    private int deckId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);
        ButterKnife.bind(this);

        deckId = getIntent().getIntExtra("DECK_ID", 0);
    }

    @OnClick(R.id.createCardButton)
    public void createCardButton(View view) {
        String question = questionTextView.getText().toString();
        String answer = answerTextView.getText().toString();

        FlashCard card = new FlashCardQuestionAndAnswer(question, null, answer, null);

        DataStorage dataStorage = new SQLiteLocalStorage(this);
        dataStorage.addCard(card, deckId);

        Intent intent = new Intent(CreateCardActivity.this, CardListActivity.class);
        intent.putExtra("DECK_ID", deckId);
        startActivity(intent);

    }


}
