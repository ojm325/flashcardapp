package com.ojm.flashcardapp.CreationViews;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;

import com.ojm.flashcardapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Omar on 3/16/2016.
 */
public class CreateCardTypeMultipleFragment extends CreateCardTypeFragment {
    @Bind(R.id.addChoiceButton) Button addChoiceButton;
    @Bind(R.id.answerChoicesSection) TableLayout answerChoicesSection;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_card_type_multiple, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.addChoiceButton)
    public void addChoiceButton(View view) {
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        final EditText choiceInputText = new EditText(getActivity());
        choiceInputText.setHint("Enter Choice Text Here");
        alertDialog.setTitle("Add Choice");
        alertDialog.setMessage("Type in a choice for this question.");
        alertDialog.setView(choiceInputText);

        CheckBox checkBox = new CheckBox(getActivity());
        checkBox.setText(choiceInputText.getText());

    }
}
