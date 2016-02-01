package com.ojm.flashcardapp;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ojm.flashcardapp.Cards.Deck;
import com.ojm.flashcardapp.Cards.FlashCard;
import com.ojm.flashcardapp.Storage.DataStorage;
import com.ojm.flashcardapp.Storage.SQLiteLocalStorage;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Omar on 1/31/2016.
 */
public class QuizTakingActivity extends Activity implements SensorEventListener{
    @Bind(R.id.cardQuestionTextView) TextView cardQuestion;
    @Bind(R.id.cardAnswerTextView) TextView cardAnswer;

    protected int deckId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_taking);
        ButterKnife.bind(this);

        deckId = getIntent().getIntExtra("DECK_ID", 0);

        DataStorage dataStorage = new SQLiteLocalStorage(this);

        Deck deck = dataStorage.getDeck(deckId);
        Log.d("CARDS", String.valueOf(deck.getCards().size()));
        populateCard(deck.getCards().get(0));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int action = MotionEventCompat.getActionMasked(event);

        switch (action){
            case (MotionEvent.ACTION_DOWN):
                return true;
            case (MotionEvent.ACTION_UP):
                Log.d("ACTION!", "swipe up");
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
