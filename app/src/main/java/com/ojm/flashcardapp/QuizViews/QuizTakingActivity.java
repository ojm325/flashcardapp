package com.ojm.flashcardapp.QuizViews;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.Toast;

import com.ojm.flashcardapp.BaseActivity;
import com.ojm.flashcardapp.CardFlip.CardFlipFragment;
import com.ojm.flashcardapp.Cards.Deck;
import com.ojm.flashcardapp.Cards.FlashCard;
import com.ojm.flashcardapp.Cards.FlashCardMultipleChoice;
import com.ojm.flashcardapp.R;
import com.ojm.flashcardapp.Storage.DataStorage;
import com.ojm.flashcardapp.Storage.SQLiteDeckCardStorage;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Omar on 1/31/2016.
 *
 * Associated layout(s): activity_quiz_taking.xml, individual_card.xml, individual_card_back.xml,
 *                      individual_card_front.xml
 */
public class QuizTakingActivity extends BaseActivity implements SensorEventListener{
    @Bind(R.id.flipCardButton) Button flipCardButton;

    protected int deckId;
    protected int cardId;
    protected Deck deck;

    protected float yDown, yUp;

    protected DataStorage dataStorage;
    protected CardFlipFragment cardFlipFragment;

    private Bundle deckBundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_taking);
        ButterKnife.bind(this);

        deckId = getIntent().getIntExtra("DECK_ID", 0);
        cardId = getIntent().getIntExtra("CARD_ID", 0);

        dataStorage = new SQLiteDeckCardStorage(this);

        deck = dataStorage.getDeck(deckId);
        Log.d("CARDS", String.valueOf(cardId));

        setTitle(deck.getDeckName());

        deckBundle = new Bundle();
        //FlashCard card = deck.getCards().get(0);

        // TODO: Setup Parcelable or Serializable to pass Flashcard data to cardFlipFragment.
        cardFlipFragment = new CardFlipFragment();

    }



    @Override
    public boolean onTouchEvent(MotionEvent event){
        int action = MotionEventCompat.getActionMasked(event);

        switch (action){
            case (MotionEvent.ACTION_DOWN):
                Log.d("SWIPE DOWN", "yDown "+yDown+" || "+yUp+ " :: "+cardId);

                yDown = event.getY();

                if (yDown < yUp){
                    if(cardId == 0){
                        Toast.makeText(getApplicationContext(), "You're at the beginning of the deck.", Toast.LENGTH_SHORT).show();
                        //populateCard(deck.getCards().get(cardId));
                    }else {
                        if(cardId >= deck.getCards().size()){
                            cardId = deck.getCards().size()-2;
                        }
                        //populateCard(deck.getCards().get(cardId--));
                    }
                }

                return true;
            case (MotionEvent.ACTION_UP):
                Log.d("SWIPE UP", "yDown "+yDown+" || "+yUp+ " :: "+cardId);

                yUp = event.getY();

                if (yDown > yUp){
                    if(cardId == deck.getCards().size()){
                        Toast.makeText(getApplicationContext(), "No more cards to show.", Toast.LENGTH_SHORT).show();
                    }else {
                        //populateCard(deck.getCards().get(cardId++));
                    }
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

    @OnClick (R.id.flipCardButton)
    protected void setFlipCardButton(){

        cardFlipFragment.flipCard();
    }
}
