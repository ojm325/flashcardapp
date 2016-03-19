package com.ojm.flashcardapp.CreationViews;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ojm.flashcardapp.BaseActivity;
import com.ojm.flashcardapp.CardListActivity;
import com.ojm.flashcardapp.Cards.FlashCard;
import com.ojm.flashcardapp.Cards.FlashCardQuestionAndAnswer;
import com.ojm.flashcardapp.R;
import com.ojm.flashcardapp.Storage.DataStorage;
import com.ojm.flashcardapp.Storage.SQLiteDeckCardStorage;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Omar on 1/17/2016.
 */
public class CreateCardActivity extends BaseActivity {
    @Bind(R.id.createCardButton) Button createCardButton;
    @Bind(R.id.questionTextView) TextView questionTextView;
    @Bind(R.id.cardTypeRadioGroup) RadioGroup cardTypeRadioGroup;

    private int deckId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);
        ButterKnife.bind(this);

        deckId = getIntent().getIntExtra("DECK_ID", 0);

        cardTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                int radioButtonId = group.getCheckedRadioButtonId();
                View radioButton = group.findViewById(radioButtonId);
                int radioIndexOfChild = group.indexOfChild(radioButton);

                switch (radioIndexOfChild) {
                    case 0:
                        CreateCardTypeWriteInFragment writeinFragment = new CreateCardTypeWriteInFragment();
                        fragmentTransaction.replace(R.id.answerLayout, writeinFragment);
                        fragmentTransaction.commit();
                        Log.d("TESTING", "Question and Answer");
                        break;
                    case 1:
                        CreateCardTypeMultipleFragment multipleFragment = new CreateCardTypeMultipleFragment();
                        fragmentTransaction.replace(R.id.answerLayout, multipleFragment);
                        fragmentTransaction.commit();
                        Log.d("TESTING", "Multiple Choice");
                        break;
                    case 2:
                        Log.d("TESTING", "Multiple Answers");
                        break;
                    case 3:
                        Log.d("TESTING", "True or False");
                        break;
                    default:
                        Log.d("TESTING", "" + radioButtonId + "||" + radioIndexOfChild);
                        break;
                }
            }
        });
    }

    @OnClick(R.id.createCardButton)
    public void createCardButton(View view) {
        String question = questionTextView.getText().toString();
        //String answer = answerTextView.getText().toString();

        FlashCard card = new FlashCardQuestionAndAnswer(question, null, "DEPRECATED", null);

        DataStorage dataStorage = new SQLiteDeckCardStorage(this);
        dataStorage.addCard(card, deckId);

        Intent intent = new Intent(CreateCardActivity.this, CardListActivity.class);
        intent.putExtra("DECK_ID", deckId);
        setResult(RESULT_OK, intent);
        this.finish();

    }


}
