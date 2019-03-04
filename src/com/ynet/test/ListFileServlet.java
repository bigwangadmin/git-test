package com.ynet.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ListFileServlet")
public class ListFileServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//��ȡ��ȡ��Ŀ¼
		String filepath = request.getParameter("file");
		File file = new File(filepath);
		//��ȡд����ļ���
		String readFile = request.getParameter("file1");
		ServletContext context = request.getServletContext();
		
		File file1 = new File(fileName(readFile, context));
		PrintWriter pw = new PrintWriter(file1);
		BufferedWriter bw = new BufferedWriter(pw);
		try {
			listFile(file, bw);
		} catch (FileNotFoundException e) {
			System.out.println("д���ļ�ʧ��!");
		}finally{
			bw.close();
		}
	

		
	}
	//�г�ָ��Ŀ¼����Ŀ¼
	public void listFile(File file, BufferedWriter writer) throws FileNotFoundException{
		
		File[] f = file.listFiles();
		//���õݹ�ķ�������ѭ����ȡ����ǿ��forѭ��
		for(File files:f){
			try {
				writer.write(files.toString()+"\r\n");
				writer.newLine();
				writer.flush();
				if(files.isDirectory()){
					listFile(files, writer);
				}
			} catch (IOException e) {
				System.out.println("д���ļ�ʧ��");
			}
			
			
		}
	}
	//�ж��ļ��Ƿ���ڣ�������ڵĻ����������һ���ļ���
	public String fileName(String str, ServletContext context){
		String filePath = null;
		//��ȡ��Ŀ�ľ���·��
		String rootPath = context.getRealPath("/");
		File file = new File(rootPath+str);
		//��ʽ��ʱ�䣬��ʽΪ������
		SimpleDateFormat ft = new SimpleDateFormat("yyMMdd");
		String number = ft.format(Calendar.getInstance().getTime());
		if(file.exists()){
			//����һ��1��10�������
			int i = (int) ((Math.random()*9+1)*10);
			 number = number + Integer.toString(i);
			filePath = rootPath+number+".txt";
		}else{
			filePath = rootPath+str;
		}
		System.out.println(filePath);
		return filePath;
		}
	
	//doPost�е���doGet��Ŀ���ǣ�����Ҫ���ǿͻ��˷��͵���get��post����
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}
