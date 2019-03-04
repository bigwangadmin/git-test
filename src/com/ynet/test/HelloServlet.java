package com.ynet.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Hello")
public class HelloServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.getWriter().write("Hello servlet");
		//获取上下文请求的URI部分
		String context = request.getContextPath();
		System.out.println(context);
		//获取HTTP请求的方法
		String reqMethod = request.getMethod();
		System.out.println("HTTP请求的方法为:"+reqMethod);
		//获取接收到这个请求的端口号
		int port = request.getServerPort();
		System.out.println("获取到的端口号为:"+port);
		
		String encoding = request.getCharacterEncoding();
		System.out.println("返回的字符编码为:"+encoding);
	}
	
	public void doPst(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		doPost(request, response);
		
	}
}
