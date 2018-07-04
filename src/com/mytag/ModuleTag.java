package com.mytag;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import StringUtil.StringUtil;

import com.bean.News;
import com.dao.NewsDaoImpl;

public class ModuleTag extends SimpleTagSupport {
	
	private String type;
	public void setType(String type){
		this.type = type;
	}
	
	public void doTag() throws JspException, IOException{
		JspWriter out = getJspContext().getOut();
		//用type查数据库,out输出
	  	//根据type查数据
		//list = dao.queryPage();
		
		NewsDaoImpl nd = new NewsDaoImpl();
		List<News> newsList = nd.queryNewsByType(type,1,10);
		
		out.print("<div class='container'>");
	  	out.print("<div class='panel panel-default'>");
	  	out.print("<div class='panel-heading'>");
	  	out.print("<a href='/NewsRelease/servlet/NewsListServlet?type=" + type + "'>"+ StringUtil.typeToChineseStr(type) +"</a>");	//type
	  	out.print("</div>");
	  	out.print("<div class='panel-body'>");
	  	
	  	//第1列
	  	out.print("<div class='col-md-6'>");
		out.print("<dl class='dl-horizontal'>");
		for(int i=0;i<newsList.size()&&i<5;i++){
			out.print("<dt>");
			out.print("<span class='icon'>");out.print("</span>");
			out.print("<a class='title' href='/NewsRelease/servlet/NewsDetailServlet?newsid="+newsList.get(i).getNewsid()+"' title='"+newsList.get(i).getTitle()+"'>");		
			out.print(newsList.get(i).getTitle());
			out.print("</a>");
			out.print("</dt>");
			
			out.print("<dd class='time text-right'>");
			String time = newsList.get(i).getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = sdf.parse(time);
				out.print(sdf.format(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.print("</dd>");
			
			
		}
		
		out.print("</dl>");
		out.print("</div>");
		//第2列
		out.print("<div class='col-md-6'>");
		out.print("<ul>");
		for(int i=5;i<newsList.size()&&i<10;i++){
			out.print("<li>");
			out.print("<span class='icon'>");out.print("</span>");
			out.print("<a class='title' href='/NewsRelease/servlet/NewsDetailServlet?newsid="+newsList.get(i).getNewsid()+"' title='"+newsList.get(i).getTitle()+"'>");
			
			out.print(newsList.get(i).getTitle());
		
			out.print("</a>");
			
			out.print("<span class='time text-right'>");
			String time = newsList.get(i).getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
			try {
				Date date = sdf.parse(time);
				out.print(sdf.format(date));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.print("</span>");
			
			out.print("</li>");
		}
		
		
		out.print("</ul>");
		out.print("</div>");
		
		
		out.print("</div>");
		out.print("</div>");
		out.print("</div>");
	}
	
}
