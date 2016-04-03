package com.ojm.flashcardapp.QuizViews;

import android.os.Bundle;
import android.widget.TextView;

import com.ojm.flashcardapp.BaseActivity;
import com.ojm.flashcardapp.Cards.Deck;
import com.ojm.flashcardapp.R;
import com.ojm.flashcardapp.Storage.DataStorage;
import com.ojm.flashcardapp.Storage.SQLiteDeckCardStorage;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Omar on 4/3/2016.
 */
public class QuizResultsActivity extends BaseActivity{
    @Bind(R.id.deckNameTextView) TextView deckNameTextView;
    @Bind(R.id.questionsRightTextView) TextView questionsRightTextView;
    @Bind(R.id.questionsWrongTextView) TextView questionsWrongTextView;
    @Bind(R.id.scoreTextView) TextView scoreTextView;

    protected DataStorage dataStorage;
    private int deckId;
    private Deck deck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);
        ButterKnife.bind(this);

        dataStorage = new SQLiteDeckCardStorage(this);

        deckId = getIntent().getIntExtra("DECK_ID", 0);
        int questionsRightInt = getIntent().getIntExtra("QUESTIONS_RIGHT", 0);
        int questionsWrongInt = getIntent().getIntExtra("QUESTIONS_WRONG", 0);
        double scoreInt = questionsRightInt / deck.getCards().size();

        deck = dataStorage.getDeck(deckId);

        deckNameTextView.setText(deck.getDeckName());
        questionsRightTextView.setText(R.string.quiz_taking_questions_right_text + questionsRightInt);
        questionsRightTextView.setText(R.string.quiz_taking_questions_right_text + questionsWrongInt);
        scoreTextView.setText(R.string.quiz_results_score_text + scoreInt + "%");

    }

}
