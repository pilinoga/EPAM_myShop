package com.epam.shop.controller.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class AuthorTag extends TagSupport {
    private static final Logger logger = LogManager.getLogger(AuthorTag.class);
    private String author;
    private String year;

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public int doStartTag(){
        String tag = doTag();
        try {
            JspWriter out = pageContext.getOut();
            out.write(tag);
        } catch (IOException e) {
            logger.error("Cannot write to stream");
        }
        return SKIP_BODY;
    }

    private String doTag() {
        StringBuilder tag = new StringBuilder();
        tag.append(author).append(", ").append(year);
        return tag.toString();
    }
}