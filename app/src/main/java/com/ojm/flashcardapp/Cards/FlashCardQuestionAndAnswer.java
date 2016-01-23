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

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    @Override
    public String getCardNote() {
        return getCardNote();
    }
}
