package me.fourtween.ms.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import me.fourtween.ms.utils.DataUtils;

/**
 * Servlet implementation class BreathServlet
 */
//@WebServlet("/OpenServlet")
public class UpdateAppServlet extends HttpServlet {
	public static Logger log = Logger.getLogger(UpdateAppServlet.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAppServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//au?curv=1.0
		// TODO Auto-generated method stub
		System.out.println("request content length:"+request.getContentLength());
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String fromM = request.getParameter("fromM");
		String toM = request.getParameter("toM");
		boolean compress = false;
		if(!StringUtils.isNullOrEmpty(request.getParameter("compress"))){
			compress = Boolean.parseBoolean(request.getParameter("compress"));
		}
		if(compress){
			OutputStream out = response.getOutputStream();
			out.write(DataUtils.getKjtimebytes(fromM, toM));
			out.flush();
			out.close();
		}else{
			response.getWriter().write(DataUtils.getKjtimeStr(fromM, toM));
			response.getWriter().flush();
			response.getWriter().close();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		doGet(request,response);
	}

}
