package me.fourtween.ms.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.fourtween.ms.utils.SocketClientManager;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		 Process p = Runtime.getRuntime().exec("java -jar E:\\workspace-jee\\union1\\target\\union1-0.0.1-SNAPSHOT-jar-with-dependencies.jar");
//		 InputStream in =p.getInputStream();
//		 BufferedReader result = new BufferedReader(new InputStreamReader(in));
//		 System.out.println(result);
//		 String s;
//		 StringBuffer msg = new StringBuffer();
//		 while ((s = result.readLine()) != null) {
//			 System.out.println("successResult-----result:"+s);
//			 msg.append(s);
//         }
//		 in.close();
//		 System.out.println("msg:"+msg);
		
//		final String IP_ADDR = "localhost";//服务器地址   
//	    final int PORT = 12345;//服务器端口号
////		while (true) {    
//            Socket socket = null;  
//            try {  
//                //创建一个流套接字并将其连接到指定主机上的指定端口号  
//                socket = new Socket(IP_ADDR, PORT);    
//                    
//                DataInputStream input = new DataInputStream(socket.getInputStream());    
//                DataOutputStream out = new DataOutputStream(socket.getOutputStream());    
//                out.writeUTF("startSync");    
//                    
//                String ret = input.readUTF();
//                System.out.println("服务器端返回过来的是: " + ret);    
////                // 如接收到 "OK" 则断开连接    
////                if ("OK".equals(ret)) {    
////                    System.out.println("客户端将关闭连接");    
////                    Thread.sleep(500);    
////                    break;    
////                }    
//                out.close();  
//                input.close();
//                if(!"".equals(ret)){
//                	response.sendRedirect("http://"+ret);
//                }else{
//                	response.getWriter().println("未能获取二维码");
//                }
//                response.getWriter().flush();
//                response.getWriter().close();
//            } catch (Exception e) {
//            	e.printStackTrace();
//                System.out.println("客户端异常:" + e.getMessage());   
//            } finally {  
//                if (socket != null) {  
//                    try {  
//                        socket.close();  
//                    } catch (IOException e) {  
//                        socket = null;   
//                        System.out.println("客户端 finally 异常:" + e.getMessage());   
//                    }  
//                }  
//            }  
//        }
		
//		SocketClientManager.getSocket();
		
		SocketClientManager.sendMsg("fasdfaeeasdfasdf343241234jhl;khsa;kldfhkashdfk;haspidfhpoaisdhfoasdhfhqwlehroqwheopirhqoiwehrqhwoierhoiqwheroiqhwerhpqwherihqweprhqpwehriqhhdndndnladkfaksldjf;lajsdlfj;aldjkddkdddddddddddddddddddd".getBytes());
		response.getWriter().println("aaaaaaa");
		response.getWriter().flush();
		response.getWriter().close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		System.out.println("test");
		InputStream in = request.getInputStream();
		int len = request.getContentLength();
		System.out.println(len);
		byte[] bs = new byte[len];
		in.read(bs);
		in.close();
		System.out.println(new String(bs,"utf8"));
	}

}
