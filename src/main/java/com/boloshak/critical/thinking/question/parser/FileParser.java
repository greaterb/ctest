package com.boloshak.critical.thinking.question.parser;

import org.apache.commons.io.FileUtils;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
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
            String path = ((ServletContext) FacesContext
                    .getCurrentInstance().getExternalContext().getContext()).getRealPath(fileName);
            File file = new File(path);
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
