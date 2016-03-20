package com.ojm.flashcardapp.Cards;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omar on 1/21/2016.
 */
public class FlashCardQuestionAndAnswer implements FlashCard{
    private String cardType;
    private String question;
    private String answer;
    private String cardNote;

    public FlashCardQuestionAndAnswer(String cardType,
                                      String question,
                                      String answer,
                                      String cardNote) {
        this.cardType = cardType;
        this.question = question;
        this.answer = answer;
        this.cardNote = cardNote;
    }

    @Override
    public String getCardType() {
        return cardType;
    }

    @Override
    public void setCardType(String type) {
        cardType = type;
    }

    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }

    public List getAnswers() {
        List<String> answers = new ArrayList<>();
        answers.add(answer);
        return answers;
    }
    public void setAnswers(String answer) {
        this.answer = answer.toString();
    }

    public String getCardNote() {
        return cardNote;
    }
    public void setCardNote(String cardNote) {
        this.cardNote = cardNote;
    }
}
