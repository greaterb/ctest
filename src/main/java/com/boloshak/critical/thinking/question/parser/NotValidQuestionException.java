package com.boloshak.critical.thinking.question.parser;

/**
 * Created by Sergey on 14.02.2016.
 */
public class NotValidQuestionException extends Exception {

    public NotValidQuestionException() {
        super();
    }

    public NotValidQuestionException(String message) {
        super(message);
    }

    public NotValidQuestionException(String message, Throwable cause) {
        super(message, cause);
    }
}
