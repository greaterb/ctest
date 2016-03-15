package com.boloshak.critical.thinking.question.parser;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Sergey on 13.02.2016.
 */
public class FileParser{

    private String fileName;

    private List<String> content;

    public FileParser(String fileName) {
        this.fileName = fileName;
    }

    public void parse() {
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("questions.txt").getPath();
            File file = new File(path);
            InputStream inputStream =
                    getClass().getClassLoader().getResourceAsStream("questions.txt");
            FileUtils.copyInputStreamToFile(inputStream, file);
            content = FileUtils.readLines(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}
