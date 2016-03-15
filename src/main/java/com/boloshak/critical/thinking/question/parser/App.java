package com.boloshak.critical.thinking.question.parser;

/**
 * Created by Sergey on 13.02.2016.
 */
public class App {

    public static final String FILE_NAME = "src\\main\\resources\\questions.txt";

    public static void main(String[] args) {

        FileParser fileParser = new FileParser(FILE_NAME);
        fileParser.parse();
        System.out.println(fileParser.getContent());

        QuestionParser questionParser = new QuestionParser();

        try {
            questionParser.parse(fileParser.getContent());
        } catch (NotValidQuestionException e) {
            e.printStackTrace();
        }

        questionParser.getQuestions();


    }
}
