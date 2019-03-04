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
		//��ȡ�����������URI����
		String context = request.getContextPath();
		System.out.println(context);
		//��ȡHTTP����ķ���
		String reqMethod = request.getMethod();
		System.out.println("HTTP����ķ���Ϊ:"+reqMethod);
		//��ȡ���յ��������Ķ˿ں�
		int port = request.getServerPort();
		System.out.println("��ȡ���Ķ˿ں�Ϊ:"+port);
		
		String encoding = request.getCharacterEncoding();
		System.out.println("���ص��ַ�����Ϊ:"+encoding);
	}
	
	public void doPst(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		doPost(request, response);
		
	}
}
