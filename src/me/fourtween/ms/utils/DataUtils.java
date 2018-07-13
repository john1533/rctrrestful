package me.fourtween.ms.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.jdbc.StringUtils;

import me.fourtween.ms.entity.Daily;
import me.fourtween.ms.entity.KJTime;
import me.fourtween.ms.entity.MarkSixItem;
import me.fourtween.ms.entity.ResultType;

public class DataUtils {
	public static Logger log = Logger.getLogger(DataUtils.class);
	
	public static String getDailyStr(){
		//TODO 缓存、对比timestamp
		Daily daily = getDaily();
		ResultType<Daily> resultType = new ResultType<Daily>();
		if(daily!=null){
			resultType.setSuccessful(true);
			resultType.setMsg("ok");
			resultType.setData(daily);
		}else{
			resultType.setSuccessful(false);
			resultType.setMsg("no data");
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(resultType);
		return json;
	}
	
	private static Daily getDaily(){
			Connection conn = ConnectionManager.getConnection();
			Statement stmt = null;
			try {
				conn = ConnectionManager.getConnection();
				stmt = conn.createStatement();
				
				ResultSet ret = stmt.executeQuery("select * from  daily order by serial1 desc limit 1");
				Daily daily = null;
				if(ret != null&&ret.next()){
					daily =  new Daily();
					daily.setId(ret.getString("serial1"));
					daily.setDate(ret.getString("opendate"));
					
					daily.setTotalAmount(ret.getString("totalAmount"));
					daily.setNum1(ret.getString("num1"));
					daily.setNum2(ret.getString("num2"));
					daily.setNum3(ret.getString("num3"));
					daily.setNum4(ret.getString("num4"));
					daily.setNum5(ret.getString("num5"));
					daily.setNum6(ret.getString("num6"));
					daily.setNum7(ret.getString("num7"));
					
					daily.setAmount1(ret.getString("amount1"));
					daily.setAmount2(ret.getString("amount2"));
					daily.setAmount3(ret.getString("amount3"));
					
					daily.setCount1(ret.getString("count1"));
					daily.setCount2(ret.getString("count2"));
					daily.setCount3(ret.getString("count3"));
					
					daily.setNextSerial(ret.getString("nextserial"));
					daily.setNextDate(ret.getString("nextopen"));
					
					daily.setExt(ret.getString("jdb"));
					daily.setExpect(ret.getString("expect"));
					return daily;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					if(stmt!=null){
						stmt.close();
					}
					if(conn!=null){
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			return null;
	}
	
	
	public static List<MarkSixItem> getMsItems(String fromS,String toS){
		Connection conn = ConnectionManager.getConnection();
		Statement stmt = null;
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			
			String sql  = "select * from  marksixitem where 1=1";
			
			if(!StringUtils.isNullOrEmpty(fromS)){
				sql = sql+ " and serial1>='" + fromS + "'";
			}
			
			if(!StringUtils.isNullOrEmpty(toS)){
				sql = sql+ " and serial1<='" + toS + "'";
			}
			sql += " order by serial1 desc";
			
			System.out.println(sql);
			ResultSet ret = stmt.executeQuery(sql);
			List<MarkSixItem> result = null;
			if(ret != null){
				result = new ArrayList<MarkSixItem>();
				while(ret.next()){
					MarkSixItem item =  new MarkSixItem();
					item.setId(ret.getString("serial1"));
					item.setDate(ret.getString("opendate"));
					
					item.setNum1(ret.getString("num1"));
					item.setNum2(ret.getString("num2"));
					item.setNum3(ret.getString("num3"));
					item.setNum4(ret.getString("num4"));
					item.setNum5(ret.getString("num5"));
					item.setNum6(ret.getString("num6"));
					item.setNum7(ret.getString("num7"));
					
					item.setTotalAmount(ret.getString("totalAmount"));
					
					item.setAmount1(ret.getString("amount1"));
					item.setAmount2(ret.getString("amount2"));
					item.setAmount3(ret.getString("amount3"));
					item.setAmount4(ret.getString("amount4"));
					item.setAmount5(ret.getString("amount5"));
					item.setAmount6(ret.getString("amount6"));
					item.setAmount7(ret.getString("amount7"));
					
					item.setCount1(ret.getString("count1"));
					item.setCount2(ret.getString("count2"));
					item.setCount3(ret.getString("count3"));
					item.setCount4(ret.getString("count4"));
					item.setCount5(ret.getString("count5"));
					item.setCount6(ret.getString("count6"));
					item.setCount7(ret.getString("count7"));
					
					result.add(item);
				}
				
			}
			
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(stmt!=null){
					stmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;
	}
	
	public static String getMsItemStr(String fromS,String toS){
		List<MarkSixItem> msItems = getMsItems(fromS,toS);
		ResultType<List<MarkSixItem>> resultType = new ResultType<List<MarkSixItem>>();
		if(msItems!=null&&msItems.size()>0){
			resultType.setSuccessful(true);
			resultType.setMsg("ok");
			resultType.setData(msItems);
		}else{
			resultType.setSuccessful(false);
			resultType.setMsg("no data");
		}
		Gson gson = new Gson();
		String json = gson.toJson(resultType);
		return json;
	}
	
	public static byte[] getMsItemBytes(String fromS,String toS){
		String json = getMsItemStr(fromS,toS);
		byte[] ret = null;
		try {
			ret = GZIPUtils.compress(json.getBytes());
			System.out.println("json:"+json+"--size before compressed:"+json.getBytes().length+"size after compress:"+ret.length);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	
	
	
	private static List<KJTime> getKjtimes(String fromM,String toM){
		Connection conn = ConnectionManager.getConnection();
		Statement stmt = null;
		try {
			conn = ConnectionManager.getConnection();
			stmt = conn.createStatement();
			
			String sql  = "select * from  kjtime where 1=1";
			
			if(!StringUtils.isNullOrEmpty(fromM)){
				sql = sql+ " and time>='" + fromM + "-00'";
			}
			
			if(!StringUtils.isNullOrEmpty( toM)){
				sql = sql+ " and time<='" +  toM + "-31'";
			}
			sql += " order by time desc";
			System.out.println(sql);
			ResultSet ret = stmt.executeQuery(sql);
			List<KJTime> result = null;
			if(ret != null){
				result = new ArrayList<KJTime>();
				while(ret.next()){
					KJTime item =  new KJTime();
					item.setTime(ret.getDate("time"));
					item.setType(ret.getString("type"));
					result.add(item);
				}
				
			}
			
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(stmt!=null){
					stmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return null;
	}
	
	public static String getKjtimeStr(String fromM,String toM){
		List<KJTime> kjItems = getKjtimes(fromM,toM);
		ResultType<List<KJTime>> resultType = new ResultType<List<KJTime>>();
		if(kjItems!=null&&kjItems.size()>0){
			resultType.setSuccessful(true);
			resultType.setMsg("ok");
			resultType.setData(kjItems);
		}else{
			resultType.setSuccessful(false);
			resultType.setMsg("no data");
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(resultType);
		
		return json;
	}
	
	public static byte[] getKjtimebytes(String fromM,String toM){
		
		String json = getKjtimeStr(fromM,toM);
		byte[] ret = null;
		try {
			ret = GZIPUtils.compress(json.getBytes());
			System.out.println("json:"+json+"--size before compressed:"+json.getBytes().length+"size after compress:"+ret.length);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
}
