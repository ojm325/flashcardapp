package com.ojm.flashcardapp.DataPoints;

import android.app.Activity;
import android.util.Log;

import com.jamesmurty.utils.XMLBuilder2;
import com.ojm.flashcardapp.Cards.FlashCardDeck;

import java.io.PrintWriter;
import java.util.Properties;

/**
 * Created by omar on 11/22/15.
 */
public class GenerateDeckLocalSheet extends Activity {

    public GenerateDeckLocalSheet(){}

    protected void createXMLFile(FlashCardDeck deck) {
        XMLBuilder2 builder = XMLBuilder2.create("FlashCardAppDeck")
                .e("Deck").a("DeckName", deck.getDeckName()).a("DeckType", deck.getDeckType());


        for(int i = 0; i < deck.getCards().size(); i++){
            builder.e("Card")
                    .e("Question")
                    .t(deck.getCards().get(i).getQuestion())
                    .up()
                    .e("Answer")
                    .t(deck.getCards().get(i).getAnswer());

        }

        writeToInternalDirectory(builder);

        Log.d("XMLBUILDER ", builder.asString());

    }

    protected void writeToInternalDirectory(XMLBuilder2 builder){
        //Probably just do SQLite instead...

        try{
            Properties outputProperties = new Properties();
            PrintWriter writer = new PrintWriter(openFileOutput("flashcard.xml", MODE_PRIVATE));
            builder.toWriter(writer, outputProperties);
        }catch(Exception e){

        }
        /*
        try{
            FileOutputStream outputStream = openFileOutput("fileName", Context.MODE_PRIVATE);
            outputStream.write();
            outputStream.close();
        }catch(Exception e){

        }
        */
    }
}
