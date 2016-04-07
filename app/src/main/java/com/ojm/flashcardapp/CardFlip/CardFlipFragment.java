package com.ojm.flashcardapp.CardFlip;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.ojm.flashcardapp.Cards.FlashCard;
import com.ojm.flashcardapp.Cards.FlashCardMultipleChoice;
import com.ojm.flashcardapp.R;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Omar on 4/5/2016.
 */
public class CardFlipFragment extends Fragment {

    private boolean mShowingBack = false;
    private FlashCard flashCard;
    private CardFrontFragment cardFrontFragment;
    private CardBackFragment cardBackFragment;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cardFrontFragment = new CardFrontFragment();

        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.cardViewArea, cardFrontFragment)
                    .commit();

            //cardFrontFragment.setDataForCardFront(flashCard);

        } else {
            mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.individual_card, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    /**
     * A fragment representing the front of the card.
     */
    public static class CardFrontFragment extends Fragment {
        @Nullable @Bind(R.id.cardQuestionTextView) TextView cardQuestion;
        @Nullable @Bind(R.id.answerSectionLayout) FrameLayout answerSectionLayout;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.individual_card_front, container, false);
            ButterKnife.bind(this, view);

            return view;
        }

        public void setDataForCardFront(FlashCard card){
            cardQuestion.setText(card.getQuestion());

            answerSectionLayout.removeAllViews();

            TableLayout checkboxTable;
            RadioGroup radioGroup;

            if(!card.getCardType().equals("Question and Answer")){
                FlashCardMultipleChoice multipleCard = (FlashCardMultipleChoice) card;

                if(card.getCardType().equals("Multiple Answers")){
                    checkboxTable = new TableLayout(getActivity());
                    for(String choiceText : multipleCard.getChoices().keySet()){
                        TableRow checkBoxRow = new TableRow(getActivity());
                        CheckBox checkBox = new CheckBox(getActivity());
                        checkBox.setText(choiceText);
                        checkBoxRow.addView(checkBox);

                        checkboxTable.addView(checkBoxRow);
                    }

                    answerSectionLayout.addView(checkboxTable);
                }else{
                    radioGroup = new RadioGroup(getActivity());
                    for(String choiceText : multipleCard.getChoices().keySet()){
                        RadioButton radioButton = new RadioButton(getActivity());
                        radioButton.setText(choiceText);
                        radioGroup.addView(radioButton);
                    }

                    answerSectionLayout.addView(radioGroup);
                }
            }
        }
    }

    /**
     * A fragment representing the back of the card.
     */
    public static class CardBackFragment extends Fragment {
       @Nullable @Bind(R.id.cardAnswerTextView) TextView cardAnswer;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.individual_card_back, container, false);
            ButterKnife.bind(this, view);

            return view;
        }

        public void setDataForCardBack(FlashCard card){
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

    public void flipCard() {
        if (mShowingBack) {
            getFragmentManager().popBackStack();
            return;
        }

        mShowingBack = true;

        cardBackFragment = new CardBackFragment();

        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)
                .replace(R.id.cardViewArea, cardBackFragment)
                .addToBackStack(null)
                .commit();

        //cardBackFragment.setDataForCardBack(flashCard);
    }

}
