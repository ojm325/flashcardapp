package com.ojm.flashcardapp;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ojm.flashcardapp.Cards.Deck;
import com.ojm.flashcardapp.Cards.FlashCard;
import com.ojm.flashcardapp.Storage.DataStorage;
import com.ojm.flashcardapp.Storage.SQLiteLocalStorage;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Omar on 1/31/2016.
 */
public class QuizTakingActivity extends BaseActivity implements SensorEventListener{
    @Bind(R.id.cardQuestionTextView) TextView cardQuestion;
    @Bind(R.id.cardAnswerTextView) TextView cardAnswer;

    protected int deckId;
    protected int cardId;
    protected Deck deck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_taking);
        ButterKnife.bind(this);

        deckId = getIntent().getIntExtra("DECK_ID", 0);
        cardId = getIntent().getIntExtra("CARD_ID", 0);

        DataStorage dataStorage = new SQLiteLocalStorage(this);

        deck = dataStorage.getDeck(deckId);
        Log.d("CARDS", String.valueOf(cardId));

        setTitle(deck.getDeckName());

        populateCard(deck.getCards().get(cardId));
    }



    @Override
    public boolean onTouchEvent(MotionEvent event){
        int action = MotionEventCompat.getActionMasked(event);

        switch (action){
            case (MotionEvent.ACTION_DOWN):
                Log.d("ACTION!", "swipe down");

                if(cardId == 0){
                    Toast.makeText(getApplicationContext(), "You're at the beginning of the deck.", Toast.LENGTH_SHORT).show();
                }else {
                    populateCard(deck.getCards().get(cardId--));
                }

                return true;
            case (MotionEvent.ACTION_UP):
                Log.d("ACTION!", "swipe up");

                if(cardId == deck.getCards().size()-1){
                    Toast.makeText(getApplicationContext(), "No more cards to show.", Toast.LENGTH_SHORT).show();
                }else {
                    populateCard(deck.getCards().get(cardId++));
                }

                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

    // Shake to randomize deck. Implement ability to shake in settings
    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    protected void populateCard(FlashCard card){
        cardQuestion.setText(card.getQuestion());
        cardAnswer.setText(card.getAnswer());

    }
}
