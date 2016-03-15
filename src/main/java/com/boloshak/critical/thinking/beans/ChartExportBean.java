package com.boloshak.critical.thinking.beans;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name= ChartExportBean.BEAN_NAME)
@CustomScoped(value = "#{window}")
public class ChartExportBean implements Serializable {
    public static final String BEAN_NAME = "chartExportBean";
    public String getBeanName() { return BEAN_NAME; }

    boolean requestOldIE;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext ec = context.getExternalContext();
        String ua = ec.getRequestHeaderMap().get("user-agent");
        requestOldIE = ua.contains("MSIE 7.0;") || ua.contains("MSIE 8.0;") ;
    }

    public boolean isRequestOldIE() {
        return requestOldIE;
    }

    public void exportHandler(org.icefaces.ace.event.ChartImageExportEvent e) {
        try {
            java.io.FileOutputStream out = new java.io.FileOutputStream("asdf1.png");
            out.write(e.getBytes());
            out.close();
        } catch (Exception ex) {

        }
    }
}