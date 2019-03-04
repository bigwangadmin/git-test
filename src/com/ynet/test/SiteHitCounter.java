package com.ynet.test;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SiteHitCounter implements Filter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int count;
	
	public void init(FilterConfig config){
		
		count = 0;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
		
		count++;
		
		System.out.println(count);
		chain.doFilter(request, response);
	
	}
	public void destroy(){
		
	}
}
