package com.boloshak.critical.thinking.question.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sergey on 12.02.2016.
 */
public class QuestionParser {

    public static final String START_QUESTIONS = "/\\";
    public static final String END_QUESTIONS = "\\/";
    public static final String TYPE_MARK = "T:";
    public static final String QUESTION_MARK = "Q:";

    public static final String EMPTY_STRING = "";


    private LinkedList<Question> questions = new LinkedList<Question>();

    public void parse(List<String> inputStrings) throws NotValidQuestionException {

        int stringIterator = 0;
        int questionCounter = 0;

//переписать этот говнокод
        while (stringIterator < inputStrings.size()) {
            String parsedString = inputStrings.get(stringIterator);
            Question question = new Question();
            question.setAnswer(new HashMap<String, Boolean>());


            if (parsedString.equals(START_QUESTIONS)) {
                questionCounter++;
                stringIterator++;
                parsedString = inputStrings.get(stringIterator);
                parseType(questionCounter, parsedString, question);

                stringIterator++;
                parsedString = inputStrings.get(stringIterator);
                parseQuestion(questionCounter, parsedString, question);

                stringIterator++;
                parsedString = inputStrings.get(stringIterator);
                stringIterator = parseAnswers(questionCounter, stringIterator, parsedString, question, inputStrings);
            }

            questions.add(question);

            stringIterator++;

        }

    }


    private void parseType(int questionNumber, String parsedString, Question question) throws NotValidQuestionException {
        if (parsedString != null || !parsedString.equals(EMPTY_STRING) || parsedString.startsWith(TYPE_MARK)) {
            question.setType(parsedString.substring(TYPE_MARK.length()));
        } else {
            throw new NotValidQuestionException("Emty or not valid topic of question number" + questionNumber);
        }
    }

    private void parseQuestion(int questionNumber, String parsedString, Question question) throws NotValidQuestionException {
        if (parsedString != null || !parsedString.equals(EMPTY_STRING) || parsedString.startsWith(QUESTION_MARK)) {
            question.setText(parsedString.substring(QUESTION_MARK.length()));
        } else {
            throw new NotValidQuestionException("Emty or not valid question of question number" + questionNumber);
        }
    }

    private int parseAnswers(int questionNumber, int stringIterator, String parsedString, Question question, List<String> inputStrings) throws NotValidQuestionException {
        while (!parsedString.equals(END_QUESTIONS)) {
            if (parsedString != null || !parsedString.equals(EMPTY_STRING) || parsedString.startsWith("-") || parsedString.equals("+")) {
                Boolean solver = parsedString.startsWith("+");

                question.getAnswer().put(parsedString.substring(1),solver);
            } else {
                throw new NotValidQuestionException("Empty or not valid answers of question number" + questionNumber);
            }
            try {
                stringIterator++;
                parsedString = inputStrings.get(stringIterator);
            } catch (IndexOutOfBoundsException e){
                throw new NotValidQuestionException("Empty or not valid answers of question number" + questionNumber);
            }
        }
        return stringIterator;
    }

 /*   private List<Question> getQuestions(int quantity){
        List<Question> resultQuestions= new
        if(quantity>1){

        }
    }*/

    public LinkedList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(LinkedList<Question> questions) {
        this.questions = questions;
    }
}
