package com.boloshak.critical.thinking.question.parser;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Sergey on 12.02.2016.
 */
public class Question {

    String type;

    String text;


    Map<String,Boolean> answer = Collections.emptyMap();


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String,Boolean> getAnswer() {
        return answer;
    }

    public void setAnswer(Map<String,Boolean> answer) {
        this.answer = answer;
    }
}
