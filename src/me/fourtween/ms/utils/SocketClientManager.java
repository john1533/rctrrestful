package me.fourtween.ms.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

public class SocketClientManager {
	public static Socket mSocket;
	
	public static Socket getSocket(){
		if(mSocket!=null){
			System.out.println("socket.isConnected():"+mSocket.isConnected());
			System.out.println("socket.isClosed():"+mSocket.isClosed());	
		}

		if(mSocket!=null&&mSocket.isConnected()&&!mSocket.isClosed())
			return mSocket;
		try {
			System.out.println("11111111111111");
			mSocket = new Socket("localhost",9090);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
        
        return mSocket;
	}
	
	
	public static void sendMsg(byte[] contents){
		Socket socket = getSocket();
//		System.out.println("socket.isConnected():"+socket.isConnected());
//		System.out.println("socket.isClosed():"+socket.isClosed());
		if (socket.isConnected()){  
            OutputStream os =null;  
            InputStream is = null; 
            ByteArrayOutputStream baOut = null;
            try {  
//                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
//                    String in = br.readLine();  
                os = socket.getOutputStream();  
                os.write(contents);//"hello".getBytes());  
                os.flush();
//                Thread.sleep(100);
                
                is = socket.getInputStream();
                
                int length = is.available();
                byte[] tmpBytes = new byte[length];
                System.out.println(length);
                is.read(tmpBytes);
                System.out.println("socket:"+socket+"---------response:" + new String(tmpBytes,"UTF8"));  
                
                
//                baOut = new ByteArrayOutputStream();
//                byte[] tmpBytes = new byte[1024];
//                int count = -1;
//                while((count=is.read(tmpBytes))!=-1){
//                	System.out.println("count:"+count);
//                	baOut.write(tmpBytes, 0, count);
//                }
//                System.out.println("socket:"+socket+"---------response:" + new String(baOut.toByteArray(),"UTF8"));  
            } catch (Exception e) {  
                e.printStackTrace();
                if(os != null)
 					try {
 						os.close();
 						socket.close();
 					} catch (IOException e1) {
 						// TODO Auto-generated catch block
 						e1.printStackTrace();
 					} //由于是长连接，抛异常时要关闭os，否则会broken pipe  
                 if(is != null)
 					try {
 						is.close();
 					} catch (IOException e1) {
 						// TODO Auto-generated catch block
 						e1.printStackTrace();
 					}
                
            } finally {
            	if(baOut!=null){
                 	try {
 						baOut.close();
 					} catch (IOException e1) {
 						// TODO Auto-generated catch block
 						e1.printStackTrace();
 					}
                 }
            	 
                 
                 
                System.out.println("do nothing");  
            }  
                  
        }
	}
}
