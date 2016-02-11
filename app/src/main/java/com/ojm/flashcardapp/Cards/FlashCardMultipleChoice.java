package com.ojm.flashcardapp.Cards;

import java.util.List;

/**
 * Created by Omar on 1/21/2016.
 */
public class FlashCardMultipleChoice implements FlashCard {
    private String question;
    private List<String> choices;
    private String answer;
    private String cardNote;

    public FlashCardMultipleChoice(String question,
                                   List<String> choices,
                                   String answer,
                                   String cardNote) {
        this.question = question;
        this.choices = choices;
        this.answer = answer;
        this.cardNote = cardNote;
    }

    public String getQuestion() { return question; }
    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getChoices() { return choices; }
    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    // Figure out best approach for multiple answers
    public String getAnswer() { return answer; }
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCardNote() { return cardNote; }
    public void setCardNote(String cardNote) {
        this.cardNote = cardNote;
    }
}
