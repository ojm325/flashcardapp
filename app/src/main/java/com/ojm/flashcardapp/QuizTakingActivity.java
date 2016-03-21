package com.ojm.flashcardapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.ojm.flashcardapp.Cards.Deck;
import com.ojm.flashcardapp.Cards.FlashCard;
import com.ojm.flashcardapp.Cards.FlashCardMultipleChoice;
import com.ojm.flashcardapp.Cards.FlashCardQuestionAndAnswer;
import com.ojm.flashcardapp.Storage.DataStorage;
import com.ojm.flashcardapp.Storage.SQLiteDeckCardStorage;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Omar on 1/31/2016.
 */
public class QuizTakingActivity extends BaseActivity implements SensorEventListener{
    @Bind(R.id.cardQuestionTextView) TextView cardQuestion;
    @Bind(R.id.cardAnswerTextView) TextView cardAnswer;
    @Bind(R.id.answerSectionLayout) FrameLayout answerSectionLayout;

    protected int deckId;
    protected int cardId;
    protected Deck deck;

    protected float yDown, yUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_taking);
        ButterKnife.bind(this);

        deckId = getIntent().getIntExtra("DECK_ID", 0);
        cardId = getIntent().getIntExtra("CARD_ID", 0);

        DataStorage dataStorage = new SQLiteDeckCardStorage(this);

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
                Log.d("SWIPE DOWN", "yDown "+yDown+" || "+yUp+ " :: "+cardId);

                yDown = event.getY();

                if (yDown < yUp){
                    if(cardId == 0){
                        Toast.makeText(getApplicationContext(), "You're at the beginning of the deck.", Toast.LENGTH_SHORT).show();
                        populateCard(deck.getCards().get(cardId));
                    }else {
                        if(cardId >= deck.getCards().size()){
                            cardId = deck.getCards().size()-2;
                        }
                        populateCard(deck.getCards().get(cardId--));
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
                        populateCard(deck.getCards().get(cardId++));
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

    protected void populateCard(FlashCard card){
        cardQuestion.setText(card.getQuestion());

        answerSectionLayout.removeAllViews();

        TableLayout checkboxTable;
        RadioGroup radioGroup;

        if(!card.getCardType().equals("Question and Answer")){
            FlashCardMultipleChoice multipleCard = (FlashCardMultipleChoice) card;

            if(card.getCardType().equals("Multiple Answers")){
                checkboxTable = new TableLayout(this);
                for(String choiceText : multipleCard.getChoices().keySet()){
                    TableRow checkBoxRow = new TableRow(this);
                    CheckBox checkBox = new CheckBox(this);
                    checkBox.setText(choiceText);
                    checkBoxRow.addView(checkBox);

                    checkboxTable.addView(checkBoxRow);
                }

                answerSectionLayout.addView(checkboxTable);
            }else{
                radioGroup = new RadioGroup(this);
                for(String choiceText : multipleCard.getChoices().keySet()){
                    RadioButton radioButton = new RadioButton(this);
                    radioButton.setText(choiceText);
                    radioGroup.addView(radioButton);
                }

                answerSectionLayout.addView(radioGroup);
            }
        }

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
