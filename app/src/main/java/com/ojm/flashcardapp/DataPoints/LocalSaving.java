package com.ojm.flashcardapp.DataPoints;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.*;
import android.util.Log;

import com.jamesmurty.utils.XMLBuilder2;
import com.ojm.flashcardapp.Cards.FlashCardDeck;

import java.io.PrintWriter;
import java.util.Properties;

/**
 * Created by omar on 11/22/15.
 */
public class LocalSaving extends SQLiteOpenHelper {

    public LocalSaving(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void createDB() {


    }

    public void saveDeck(){

    }

    public void saveCard(){

    }

    public void modifyDeck(){

    }

    public void modifyCard(){

    }

    public void getAllDecks(){

    }

    public void getAllCards(){

    }

    public void getDeck(){

    }

    public void getCard(){
        
    }
}
