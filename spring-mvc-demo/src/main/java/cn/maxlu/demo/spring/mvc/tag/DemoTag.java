package cn.maxlu.demo.spring.mvc.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;

/**
 * Created by dell on 2017/6/22.
 */

public class DemoTag extends BodyTagSupport {

    private String name;

    private String value;

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print("====doStartTag====");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            pageContext.getOut().print("====doEndTag====");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doEndTag();
    }

    @Override
    public void release() {
        super.release();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
