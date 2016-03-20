package com.ojm.flashcardapp.Cards;

import java.util.*;

/**
 * Created by Omar on 1/21/2016.
 */
public class FlashCardMultipleChoice implements FlashCard {
    private String cardType;
    private String question;
    private TreeMap<String, Boolean> choices;
    private String cardNote;

    public FlashCardMultipleChoice(String cardType,
                                   String question,
                                   TreeMap<String, Boolean> choices,
                                   String cardNote) {
        this.question = question;
        this.choices = choices;
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

    public String getQuestion() { return question; }
    public void setQuestion(String question) {
        this.question = question;
    }

    public TreeMap<String, Boolean> getChoices() { return choices; }
    public void setChoices(TreeMap<String, Boolean> choices) {
        this.choices = choices;
    }

    public List getAnswers() {
        List<String> answers = new ArrayList<>();

        for(String choice : choices.keySet()){
            if (choices.get(choice).equals(true)){
                answers.add(choice);
            }
        }

        return answers;
    }

    public String getCardNote() { return cardNote; }
    public void setCardNote(String cardNote) {
        this.cardNote = cardNote;
    }
}
