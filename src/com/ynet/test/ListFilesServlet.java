package com.ynet.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/ListFilesServlet")
public class ListFilesServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
			
		String writefile = request.getParameter("writeFile");
		String readfile = request.getParameter("readFile");
		PrintWriter pw = null;
		BufferedWriter bw = null;
		
		try {
			 pw = new PrintWriter( new File(writefile));
			 bw = new BufferedWriter(pw);
			 listFile(readfile, pw, bw);;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void listFile(String str, PrintWriter pw, BufferedWriter bw) throws IOException{
		
		File file = new File(str);
		File[] files = file.listFiles();
		bw = new BufferedWriter(pw);
		for(File f:files){
			bw.write(f.toString());
			System.out.println(f.toString());
			bw.newLine();
			bw.flush();
			if(f.isDirectory()){
				listFile(str, pw, bw);
			}
		}
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
			doGet(request,response);

	}
}