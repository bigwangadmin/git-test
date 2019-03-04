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
		
		//获取读取的目录
		String filepath = request.getParameter("file");
		File file = new File(filepath);
		//获取写入的文件名
		String readFile = request.getParameter("file1");
		ServletContext context = request.getServletContext();
		
		File file1 = new File(fileName(readFile, context));
		PrintWriter pw = new PrintWriter(file1);
		BufferedWriter bw = new BufferedWriter(pw);
		try {
			listFile(file, bw);
		} catch (FileNotFoundException e) {
			System.out.println("写入文件失败!");
		}finally{
			bw.close();
		}
	

		
	}
	//列出指定目录及子目录
	public void listFile(File file, BufferedWriter writer) throws FileNotFoundException{
		
		File[] f = file.listFiles();
		//采用递归的方法进行循环读取，增强的for循环
		for(File files:f){
			try {
				writer.write(files.toString()+"\r\n");
				writer.newLine();
				writer.flush();
				if(files.isDirectory()){
					listFile(files, writer);
				}
			} catch (IOException e) {
				System.out.println("写入文件失败");
			}
			
			
		}
	}
	//判断文件是否存在，如果存在的话，随机生成一个文件名
	public String fileName(String str, ServletContext context){
		String filePath = null;
		//获取项目的绝对路径
		String rootPath = context.getRealPath("/");
		File file = new File(rootPath+str);
		//格式化时间，格式为年月日
		SimpleDateFormat ft = new SimpleDateFormat("yyMMdd");
		String number = ft.format(Calendar.getInstance().getTime());
		if(file.exists()){
			//生成一个1到10的随机数
			int i = (int) ((Math.random()*9+1)*10);
			 number = number + Integer.toString(i);
			filePath = rootPath+number+".txt";
		}else{
			filePath = rootPath+str;
		}
		System.out.println(filePath);
		return filePath;
		}
	
	//doPost中调用doGet的目的是：不需要考虑客户端发送的是get或post请求
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}
