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
import android.widget.TableLayout;

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
        final EditText choiceInputText = new EditText(getActivity());
        choiceInputText.setHint("Enter Choice Text Here");


        new AlertDialog.Builder(getActivity())
                .setTitle("Add Choice")
                        .setMessage("Type in a choice for this question.")
                        .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                CheckBox checkBox = new CheckBox(getActivity());
                                checkBox.setText(choiceInputText.getText());
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
