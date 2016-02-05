package com.ojm.flashcardapp.Cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by omar on 11/23/15.
 */
public interface FlashCard {
    public String getQuestion();
    public void setQuestion(String question);
    public String getCardNote();
    public void setCardNote(String cardNote);
}
