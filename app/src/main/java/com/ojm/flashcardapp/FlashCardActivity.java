package com.ojm.flashcardapp;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * Created by omar on 11/22/15.
 */
public class FlashCardActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_card);
        ButterKnife.bind(this);
    }
}
