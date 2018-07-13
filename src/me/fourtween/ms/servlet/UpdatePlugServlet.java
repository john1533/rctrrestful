package me.fourtween.ms.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class BreathServlet
 */
//@WebServlet("/OpenServlet")
public class UpdatePlugServlet extends HttpServlet {
	public static Logger log = Logger.getLogger(UpdatePlugServlet.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePlugServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//pu?channel=normal&mv=1.0&pv=1.0&pid=
		//当前主版本号，当前插件版本号，当前插件包名
		// TODO Auto-generated method stub
		
		System.out.println("request Parameters:"+request.getPathInfo());
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String shotVer = "";
		String prefix = "plug";
		//主版本号，
		Map<String,String> targetVersion = new HashMap<String,String>();
		targetVersion.put("1.0", "1.1");
		targetVersion.put("2.0", "2.1");
		
		String mainCurVersion = request.getParameter("mv");
		String plugCurVersion = request.getParameter("pv");

		for(String targetMv:targetVersion.keySet()){
			if(targetMv.equals(mainCurVersion)){
				shotVer = targetVersion.get(targetMv);
				if(shotVer.compareTo(plugCurVersion)<=0){
					shotVer = "";
				}
				break;
			}
		}
		System.out.println("shotVer:"+shotVer);
		System.out.println("context path:"+getServletContext().getRealPath("files"));
		if("".equals(shotVer)){
			response.setStatus(300);
			response.getWriter().flush();
			response.getWriter().close();
		}else{
//			OutputStream out = response.getOutputStream();
//			out.write(DataUtils.getKjtimebytes(fromM, toM));
//			out.flush();
//			out.close();

			response.addHeader("version", shotVer);
			InputStream inStream = new FileInputStream(new File(getServletContext().getRealPath("files"),shotVer));

            OutputStream outStream = response.getOutputStream();
            response.setHeader("version", shotVer);
            byte[] buffer = new byte[1024*100];
            int length = 0;
            int total = 0;
            while((length = inStream.read(buffer))!=-1){
            	total += length;
                outStream.write(buffer,0,length);
            }
            response.setContentLength(total);
            
            outStream.flush();
            inStream.close();
            outStream.close();
			
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
