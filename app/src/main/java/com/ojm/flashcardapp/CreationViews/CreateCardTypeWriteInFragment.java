package com.ojm.flashcardapp.CreationViews;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.ojm.flashcardapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Omar on 3/16/2016.
 */
public class CreateCardTypeWriteInFragment extends CreateCardTypeFragment{
    @Bind(R.id.answerTextView) TextView answerTextView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_card_type_writein, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
