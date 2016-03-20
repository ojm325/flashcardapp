package com.ojm.flashcardapp.Cards;

import java.util.*;

/**
 * Created by omar on 11/23/15.
 */
public interface FlashCard {
    String getCardType();
    void setCardType(String type);
    String getQuestion();
    void setQuestion(String question);
    String getCardNote();
    // In order to support Multiple Answers, we would need to return a List
    List getAnswers();
    void setCardNote(String cardNote);

}
