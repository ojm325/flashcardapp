package com.ojm.flashcardapp.Cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by omar on 11/23/15.
 */
public interface FlashCard {
    String getQuestion();
    void setQuestion(String question);
    String getAnswer();
    void setAnswer(String answer);
    String getCardNote();
    void setCardNote(String cardNote);

}
