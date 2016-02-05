package com.ojm.flashcardapp.Cards;

import java.util.ArrayList;

/**
 * Created by Omar on 1/21/2016.
 */
public class FlashCardQuestionAndAnswer implements FlashCard{
    private String question;
    private String questionPicture;
    private ArrayList multipleChoices;
    private String answer;
    private String cardNote;

    public FlashCardQuestionAndAnswer(String question,
                     String questionPicture,
                     String answer,
                     String cardNote) {
        this.question = question;
        this.questionPicture = questionPicture;
        this.answer = answer;
        this.cardNote = cardNote;
    }

    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCardNote() {
        return getCardNote();
    }
    public void setCardNote(String cardNote) {
        this.cardNote = cardNote;
    }
}
