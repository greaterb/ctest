package com.boloshak.critical.thinking.beans;

import com.boloshak.critical.thinking.question.parser.FileParser;
import com.boloshak.critical.thinking.question.parser.NotValidQuestionException;
import com.boloshak.critical.thinking.question.parser.Question;
import com.boloshak.critical.thinking.question.parser.QuestionParser;
import org.icefaces.ace.component.chart.Axis;
import org.icefaces.ace.component.chart.AxisType;
import org.icefaces.ace.event.ChartImageExportEvent;
import org.icefaces.ace.model.chart.CartesianSeries;
import org.icefaces.ace.model.chart.ChartSeries;

import javax.faces.application.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Sergey on 11.03.2016.
 */
@ManagedBean
@SessionScoped
public class TestBean implements Serializable {


    public static final String FILE_NAME = "resources\\questions.txt";

    public static final Integer quantityQuestions = 7;

    private String name;

    private Integer questionNumber;

    private LinkedList<Question> questions;

    private Map<String,Integer> score;

    private Question current;

    private Boolean currentAnswer;


    private Axis barDemoDefaultAxis = new Axis() {{
        setTickAngle(-30);
    }};

    private Axis barDemoXAxis = new Axis() {{
    }};

    private Axis[] YAxes = new Axis[] {
            new Axis(){{
                setType(AxisType.CATEGORY);
            }}
    };



    public String startTest(){
        setQuestions(loadQuestions());
        score = new HashMap<String, Integer>();
        current = questions.getFirst();
        questionNumber = 1;
        return "test";
    }

    private LinkedList<Question> loadQuestions(){
        FileParser fileParser = new FileParser(FILE_NAME);
        fileParser.parse();
        System.out.println(fileParser.getContent());

        QuestionParser questionParser = new QuestionParser();

        try {
            questionParser.parse(fileParser.getContent());
        } catch (NotValidQuestionException e) {
            e.printStackTrace();
        }

       return questionParser.getQuestions();
    }

    public String next(){
        String currentTopic = current.getType();
        if(getCurrentAnswer()){
            if(score.get(currentTopic)!=null){
               Integer count = score.get(currentTopic);
                count++;
                score.put(currentTopic,count);
            }else{
                score.put(currentTopic,new Integer(1));
            }
        }else{
            score.put(currentTopic,new Integer(0));
        }
        int index =  questions.indexOf(current);
        ListIterator<Question> listIterator  = questions.listIterator(index+1);
        if(listIterator.hasNext()){
            current = listIterator.next();
        }
        setCurrentAnswer(null);


        if (getEndTest()) {
            return "result";
        } else {
            questionNumber++;
            return "";
        }

    }

    public ChartSeries getChartValues(){
        CartesianSeries chartSeries =  new CartesianSeries();
        chartSeries.setType(CartesianSeries.CartesianType.BAR);
        chartSeries.setHorizontalBar(true);

        for(String key: score.keySet()){
            chartSeries.add(score.get(key),key);
        }

        return chartSeries;
    }

    private byte[] image;

    public void saveImage(ChartImageExportEvent event) {
        image = event.getBytes();
    }

    public Resource getSavedImage() {
        return new Resource() {
            public InputStream getInputStream() throws IOException {
                return new ByteArrayInputStream(image);
            }

            public Map<String, String> getResponseHeaders() {
                return Collections.EMPTY_MAP;
            }

            public String getRequestPath() {
                return null;
            }

            public URL getURL() {
                return null;
            }

            public boolean userAgentNeedsUpdate(FacesContext context) {
                return true;
            }
        };
    }

    public boolean isImageSaved() {
        return image != null && image.length > 0;
    }

    public List<String> getLegend(){
        return new ArrayList<>(score.keySet());
    }

    public boolean getEndTest(){
        return questionNumber>=quantityQuestions;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    public LinkedList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(LinkedList<Question> questions) {
        this.questions = questions;
    }

    public Map<String, Integer> getScore() {
        return score;
    }

    public void setScore(Map<String, Integer> score) {
        this.score = score;
    }

    public Question getCurrent() {
        return current;
    }

    public void setCurrent(Question current) {
        this.current = current;
    }



    public void setCurrentAnswer(Boolean currentAnswer) {
        this.currentAnswer = currentAnswer;
    }

    public Boolean getCurrentAnswer() {
        return currentAnswer;
    }

    public Axis getBarDemoDefaultAxis() {
        return barDemoDefaultAxis;
    }

    public void setBarDemoDefaultAxis(Axis barDemoDefaultAxis) {
        this.barDemoDefaultAxis = barDemoDefaultAxis;
    }

    public Axis getBarDemoXAxis() {
        return barDemoXAxis;
    }

    public void setBarDemoXAxis(Axis barDemoXAxis) {
        this.barDemoXAxis = barDemoXAxis;
    }

    public Axis[] getYAxes() {
        return YAxes;
    }

    public void setYAxes(Axis[] YAxes) {
        this.YAxes = YAxes;
    }
}
