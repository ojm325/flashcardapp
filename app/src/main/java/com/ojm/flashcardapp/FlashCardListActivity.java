package com.ojm.flashcardapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.*;

public class FlashCardListActivity extends AppCompatActivity {

    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.deckList) SwipeMenuListView deckList;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        list = new ArrayList<>();
        list.add("Test 1");
        list.add("Test 2");
        list.add("Test 3");
        final CustomArrayAdapter adapter = new CustomArrayAdapter(this, R.layout.deck_list_item, list);
        deckList.setAdapter(adapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());

                // set item width
                openItem.setBackground((new ColorDrawable(Color.BLUE)));
                openItem.setWidth(200);
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                openItem.setTitleColor(Color.BLACK);
                // set item title font color
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                // set item width
                deleteItem.setWidth(200);
                deleteItem.setTitleSize(18);
                deleteItem.setTitleColor(Color.BLACK);
                deleteItem.setBackground((new ColorDrawable(Color.RED)));
                // set a icon
                deleteItem.setTitle("DELETE");
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        deckList.setMenuCreator(creator);

        deckList.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
            @Override
            public void onSwipeStart(int position) {

            }

            @Override
            public void onSwipeEnd(int position) {

            }
        });

        deckList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                Log.d("POSITION ", "" + position);
                switch (index) {
                    case 0:
                        Intent intent = new Intent(FlashCardListActivity.this, FlashCardActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        // delete
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }

        });


        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }

    @OnClick(R.id.fab)
    public void fab(View view){
        /*
        list.add("Test 4");
        final CustomArrayAdapter adapter = new CustomArrayAdapter(this, R.layout.deck_list_item, list);
        deckList.setAdapter(adapter);
        */
        Intent intent = new Intent(FlashCardListActivity.this, CreateDeckActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_flash_card_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
