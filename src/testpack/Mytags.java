package testpack;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class Mytags extends SimpleTagSupport{

	private String color = "black";
	private int size = 1;

	public void setColor(String color) {
		this.color = color;
	}
	public void setSize(String size) {
		try {
			this.size = Integer.parseInt(size);
		}catch(Exception e) {
			
		}
	}

	
	@Override
	public void doTag() throws JspException, IOException {
		
		JspWriter out = getJspContext().getOut();
		out.println("<h2 style=\"color:"+color+"; font-size:"+size+"em;\">");
		getJspBody().invoke(null);
		out.println("</h2>");
	}
	
}
