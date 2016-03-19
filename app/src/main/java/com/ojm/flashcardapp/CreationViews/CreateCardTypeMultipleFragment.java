package com.ojm.flashcardapp.CreationViews;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.ojm.flashcardapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Omar on 3/16/2016.
 */
public class CreateCardTypeMultipleFragment extends Fragment {
    @Bind(R.id.addChoiceButton) Button addChoiceButton;
    @Bind(R.id.answerChoicesSection) TableLayout answerChoicesSection;

    private String cardType = "";
    private TableLayout checkboxTable;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cardType = getArguments().getString("cardType");

        if(cardType.equals("True or False")){
            RadioGroup trueFalseGroup = new RadioGroup(getActivity());
            addChoiceButton.setVisibility(View.GONE);
            RadioButton trueButton = new RadioButton(getActivity());
            trueButton.setText("TRUE");
            trueFalseGroup.addView(trueButton);

            RadioButton falseButton = new RadioButton(getActivity());
            falseButton.setText("FALSE");
            trueFalseGroup.addView(falseButton);

            answerChoicesSection.addView(trueFalseGroup);
        }else if(cardType.equals("Multiple Answers")){
            checkboxTable = new TableLayout(getActivity());
            answerChoicesSection.addView(checkboxTable);
        }
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
        final EditText choiceInputText = new EditText(getActivity());
        choiceInputText.setHint("Enter Choice Text Here");


        new AlertDialog.Builder(getActivity())
                .setTitle("Add Choice")
                        .setMessage("Type in a choice for this question.")
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if(cardType.equals("Multiple Choice")){
                                    RadioButton radioButton = new RadioButton(getActivity());
                                    radioButton.setText(choiceInputText.getText());
                                    answerChoicesSection.addView(radioButton);
                                }else if(cardType.equals("Multiple Answers")){
                                    TableRow checkBoxRow = new TableRow(getActivity());

                                    CheckBox checkBox = new CheckBox(getActivity());
                                    checkBox.setText(choiceInputText.getText());
                                    checkBoxRow.addView(checkBox);

                                    checkboxTable.addView(checkBoxRow);
                                }
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setView(choiceInputText)
                        .show();


    }
}
