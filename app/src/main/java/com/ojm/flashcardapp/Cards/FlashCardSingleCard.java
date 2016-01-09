package com.ojm.flashcardapp.Cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by omar on 11/23/15.
 */
public class FlashCardSingleCard {
    private String question;
    private String questionPicture;
    private ArrayList multipleChoices;
    private String answer;
    private String answerNote;

    public FlashCardSingleCard(String question,
                               String questionPicture,
                               ArrayList multipleChoices,
                               String answer,
                               String answerNote){
        this.question = question;
        this.questionPicture = questionPicture;
        this.multipleChoices = multipleChoices;
        this.answer = answer;
        this.answerNote = answerNote;

    }
    public String getQuestion(){
        return this.question;
    }
    public String getQuestionPicture(){ return this.questionPicture; }
    public ArrayList getMultipleChoices(){
        return this.multipleChoices;
    }
    public String getAnswer(){ return answer;}
    public String getAnswerNote(){ return answerNote;}
}
