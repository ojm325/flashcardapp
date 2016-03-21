package com.ojm.flashcardapp.CreationViews;

import android.app.Fragment;
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
 *
 * Associated layout(s): fragment_create_card_type_writein.xml
 */
public class CreateCardTypeWriteInFragment extends Fragment {
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

    public String getAnswer(){
        return answerTextView.getText().toString();
    }
}
