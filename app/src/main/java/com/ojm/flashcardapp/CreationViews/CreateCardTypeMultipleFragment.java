package com.ojm.flashcardapp.CreationViews;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
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

import java.util.SortedMap;
import java.util.TreeMap;

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
    private RadioGroup radioGroup;
    private TreeMap<String, Boolean> choices;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cardType = getArguments().getString("cardType");

        choices = new TreeMap<>();

        if(cardType.equals("True or False")){
            radioGroup = new RadioGroup(getActivity());
            addChoiceButton.setVisibility(View.GONE);
            RadioButton trueButton = new RadioButton(getActivity());
            trueButton.setText("True");
            choices.put("True", false);
            radioGroup.addView(trueButton);

            RadioButton falseButton = new RadioButton(getActivity());
            falseButton.setText("False");
            choices.put("False", false);
            radioGroup.addView(falseButton);

            answerChoicesSection.addView(radioGroup);
        }else if(cardType.equals("Multiple Answers")){
            checkboxTable = new TableLayout(getActivity());
            answerChoicesSection.addView(checkboxTable);
        }else if(cardType.equals("Multiple Choice")){
            radioGroup = new RadioGroup(getActivity());
            answerChoicesSection.addView(radioGroup);
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
                                    choices.put(choiceInputText.getText().toString(), false);
                                    radioGroup.addView(radioButton);
                                }else if(cardType.equals("Multiple Answers")){
                                    TableRow checkBoxRow = new TableRow(getActivity());

                                    CheckBox checkBox = new CheckBox(getActivity());
                                    checkBox.setText(choiceInputText.getText());
                                    choices.put(choiceInputText.getText().toString(), false);
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

    public TreeMap<String, Boolean> getChoices(){
        if((radioGroup != null) && (radioGroup.getChildCount() > 0)){
            int selectedId = radioGroup.getCheckedRadioButtonId();

            if(selectedId != -1) {
                RadioButton answerRadio = (RadioButton) radioGroup.getChildAt(selectedId);
                String answer = answerRadio.getText().toString();

                choices.put(answer, true);

                Log.d("MULTIPLE", choices.values().toString() + "");
            }

        }else if(checkboxTable != null){
            for(int i = 0; i < checkboxTable.getChildCount(); i++){
                View view = checkboxTable.getChildAt(i);
                TableRow row = (TableRow) view;

                CheckBox checkBox = (CheckBox) row.getChildAt(0);
                String answer = checkBox.getText().toString();

                if(checkBox.isChecked()){
                    choices.put(answer, true);
                }
            }

            Log.d("MULTIPLE", choices.size()+"");
        }

        return choices;
    }
}
