package com.ojm.flashcardapp;

import android.os.Bundle;
import android.widget.TextView;

import com.ojm.flashcardapp.Cards.Deck;
import com.ojm.flashcardapp.Cards.FlashCard;
import com.ojm.flashcardapp.Storage.DataStorage;
import com.ojm.flashcardapp.Storage.SQLiteDeckCardStorage;

import java.util.LinkedHashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by omar on 11/22/15.
 */
public class FlashCardActivity extends BaseActivity {
    @Bind(R.id.cardQuestionTextView) TextView cardQuestion;
    @Bind(R.id.cardAnswerTextView) TextView cardAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_card);
        ButterKnife.bind(this);

        DataStorage dataStorage = new SQLiteDeckCardStorage(this);
        Deck deck = dataStorage.getDeck(0);

        setTitle(deck.getDeckName());

        populateCard(deck.getCards().get(0));
    }

    protected void populateCard(FlashCard card){
        cardQuestion.setText(card.getQuestion());

        if(card.getCardType().equals("Multiple Answers")){
            String answerConcat = "";

            for(int i = 0; i < card.getAnswers().size(); i++){
                answerConcat += card.getAnswers().get(i).toString()+ ", ";
            }

            cardAnswer.setText(answerConcat);
        }else{
            cardAnswer.setText(card.getAnswers().get(0).toString());
        }

    }
}
