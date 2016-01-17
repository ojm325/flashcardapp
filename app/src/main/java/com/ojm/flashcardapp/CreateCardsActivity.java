package com.ojm.flashcardapp;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * Created by Omar on 1/17/2016.
 */
public class CreateCardsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_flash_cards);
        ButterKnife.bind(this);
    }


}
