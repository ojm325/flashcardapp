package com.ojm.flashcardapp.CreationViews;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
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
import com.ojm.flashcardapp.Cards.FlashCardMultipleChoice;
import com.ojm.flashcardapp.Cards.FlashCardQuestionAndAnswer;
import com.ojm.flashcardapp.R;
import com.ojm.flashcardapp.Storage.DataStorage;
import com.ojm.flashcardapp.Storage.SQLiteDeckCardStorage;

import java.util.TreeMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Omar on 1/17/2016.
 *
 * Associated layout(s): activity_create_card.xml, fragment_create_card_type_multiple.xml,
 *                      fragment_create_card_type_write_in.xml
 */
public class CreateCardActivity extends BaseActivity {
    @Bind(R.id.createCardButton) Button createCardButton;
    @Bind(R.id.questionTextView) TextView questionTextView;
    @Bind(R.id.cardTypeRadioGroup) RadioGroup cardTypeRadioGroup;
    @Bind(R.id.cardNotesTextField) TextView cardNoteTextView;

    private int deckId;
    private String question;
    private Bundle cardType;
    int radioIndexOfChild = -1;

    private CreateCardTypeWriteInFragment writeinFragment;
    private CreateCardTypeMultipleFragment multipleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);
        ButterKnife.bind(this);

        deckId = getIntent().getIntExtra("DECK_ID", 0);

        // Determine the card type the user has selected, and adjust the fragment in this activity
        // so that the user can put in the correct choices/answer for that type.
        cardTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                int radioButtonId = group.getCheckedRadioButtonId();
                View radioButton = group.findViewById(radioButtonId);
                radioIndexOfChild = group.indexOfChild(radioButton);

                cardType = new Bundle();

                switch (radioIndexOfChild) {
                    case 0:
                        cardType.putString("cardType", "Question and Answer");

                        writeinFragment = new CreateCardTypeWriteInFragment();
                        fragmentTransaction.replace(R.id.answerLayout, writeinFragment);
                        fragmentTransaction.commit();

                        Log.d("TESTING", "Question and Answer");
                        break;
                    case 1:
                        multipleFragment = new CreateCardTypeMultipleFragment();

                        cardType.putString("cardType", "Multiple Choice");
                        multipleFragment.setArguments(cardType);

                        fragmentTransaction.replace(R.id.answerLayout, multipleFragment);
                        fragmentTransaction.commit();
                        Log.d("TESTING", "Multiple Choice");
                        break;
                    case 2:
                        multipleFragment = new CreateCardTypeMultipleFragment();

                        cardType.putString("cardType", "Multiple Answers");
                        multipleFragment.setArguments(cardType);

                        fragmentTransaction.replace(R.id.answerLayout, multipleFragment);
                        fragmentTransaction.commit();
                        Log.d("TESTING", "Multiple Answers");
                        break;
                    case 3:
                        multipleFragment = new CreateCardTypeMultipleFragment();

                        cardType.putString("cardType", "True or False");
                        multipleFragment.setArguments(cardType);

                        fragmentTransaction.replace(R.id.answerLayout, multipleFragment);
                        fragmentTransaction.commit();

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
        question = questionTextView.getText().toString();
        String cardNote = cardNoteTextView.getText().toString();

        // Verify if the user input everything correctly, then determine how the questions will be put
        // into storage based on card type.
        if(verifyCardCreation()){
            String answer = "";
            TreeMap<String, Boolean> choices;

            DataStorage dataStorage = new SQLiteDeckCardStorage(this);

            if(cardType.getString("cardType").equals("Question and Answer")){
                answer = writeinFragment.getAnswer();

                FlashCard card = new FlashCardQuestionAndAnswer(cardType.getString("cardType"), question, answer, cardNote);


                dataStorage.addCard(card, deckId);
                int lastCardId = dataStorage.getLastInsertedCardId(deckId);
                dataStorage.setAnswerChoiceForCard(deckId, lastCardId, answer, true);

            }else{
                choices = multipleFragment.getChoices();

                FlashCardMultipleChoice card = new FlashCardMultipleChoice(cardType.getString("cardType"), question, choices, cardNote);

                dataStorage.addCard(card, deckId);
                int lastCardId = dataStorage.getLastInsertedCardId(deckId);

                for(String choice: choices.keySet()){
                    dataStorage.setAnswerChoiceForCard(deckId, lastCardId, choice, choices.get(choice));
                }

            }


            Intent intent = new Intent(CreateCardActivity.this, CardListActivity.class);
            intent.putExtra("DECK_ID", deckId);
            setResult(RESULT_OK, intent);
            this.finish();



        }

    }

    protected boolean verifyCardCreation(){
        // How the verifier should work when completely implemented...
        // (Multiple Answers) Check if answer(s) have been selected.
        // (True False/ Multiple Choice) Check if answer has been selected
        // (Question and Answer) Check if answer has been written
        // Check if Card has question
        try {
            if (question.isEmpty()) {
                displayCardCreationError("Type in a question.");

                return false;
            } else if (radioIndexOfChild == -1) {
                displayCardCreationError("Select a card type.");

                return false;
            }else {
                return true;
            }
        }catch (NullPointerException e){
            displayCardCreationError("Put in or mark a correct answer.");

            return false;
        }
    }

    protected void displayCardCreationError(String errorMessage){
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(errorMessage)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


}
