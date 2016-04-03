package com.ojm.flashcardapp.QuizViews;

import android.os.Bundle;
import android.widget.TextView;

import com.ojm.flashcardapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Omar on 4/3/2016.
 */
public class QuizChallengeActivity extends QuizTakingActivity {
    @Bind(R.id.questionsRightTextView) TextView questionsRightTextView;
    @Bind(R.id.questionsWrongTextView) TextView questionsWrongTextView;

    int questionsRightInt = 0;
    int questionsWrongInt = 0;
    int cardsLeftInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        cardsLeftInt = super.dataStorage.getDeck(super.deckId).getCards().size();
    }

}
