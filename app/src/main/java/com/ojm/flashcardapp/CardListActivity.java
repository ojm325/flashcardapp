package com.ojm.flashcardapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Omar on 1/22/2016.
 */
public class CardListActivity extends Activity {
    @Bind(R.id.fab) FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_list);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.fab)
    public void fab(View view){
        Intent intent = new Intent(CardListActivity.this, CreateCardActivity.class);
        startActivity(intent);
    }

}
