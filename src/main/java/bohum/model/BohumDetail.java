package bohum.model;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

public class BohumDetail {
	
	public ArrayList<BohumDataBean> getHohumDetail(String age) {
		ArrayList<BohumDataBean> bohumTestInfoArr = new ArrayList<BohumDataBean>();
		try {
			// 김경혜_ 코드리뷰
			// https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15094797#tab_layer_detail_function
			// 위의 url이 실손보험정보 신청하는 곳 홈페이지 규칙에 따라 소스 짬
			// 왜 StringBuilder? 스트링빌더가 스트링버퍼보다 성능 좋음
			// 단 멀티쓰레드땐 동기화 지원안하니까 주의!
			StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1160100/service/GetMedicalReimbursementInsuranceInfoService/getInsuranceInfo"); /*URL*/
			urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=wKQumq5LX0aGJN19E3mLdne0GDiEtPtVpVY3tDVBkOYPc21sBxDu%2B4lUggPaO0ETQboYKIVcYuGsd5lxtqhYoQ%3D%3D");//개인이 발급 받는 키
			urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한번에 1000개 받는다는 의미*/
			urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 넘버(여러 페이지에서 받을 수 도 있음)*/
			urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*데이터 수신 타입(xml/json)*/
			urlBuilder.append("&" + URLEncoder.encode("age","UTF-8") + "=" + URLEncoder.encode(age, "UTF-8")); /*나이 >> 우리는 나이대 별로 해주는거니까 10대, 20대, 30대.... */       
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			// 왜 GET방식인가? 홈페이지 규칙에서 GET으로 요청해달라고 함
			conn.setRequestProperty("Content-type", "application/json");
			// 왜 json인가? json이 javascript언어에서 파생된 언어이므로 web에서 다루기 좋음, 그리고 데이터가 가벼움
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
	        InputStreamReader in = new InputStreamReader((InputStream) conn.getContent(), "UTF-8");
	        // 왜 InputStreamReader를 거치는가? server(우리에게는 톰캣9.0)에도  UTF-8을 해줘야지 깨지지 않음
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(in);
	        }else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();
			System.out.println(sb.toString());

			String myData = sb.toString();
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject)parser.parse(myData);

			JSONObject jsonObj = (JSONObject)obj;
			JSONObject jsonResponse = (JSONObject)jsonObj.get("response");
			JSONObject jsonBody = (JSONObject)jsonResponse.get("body");
			JSONObject jsonItems = (JSONObject)jsonBody.get("items");
			JSONArray jsonItem = (JSONArray)jsonItems.get("item");
			
			
			System.out.println("basDt \t cmpyCd \t cmpyNm \t ptrn \t mog \t prdNm \t age \t mlInsRt \t fmlInsRt \t");
			for(int i=0;i<jsonItem.size();i++) {
				JSONObject item = (JSONObject)jsonItem.get(i);
				System.out.print(item.get("basDt")+"\t");			//YYYYMMDD 수정 날짜
				System.out.print(item.get("cmpyCd")+"\t");			// 회사 코드
				System.out.print(item.get("cmpyNm")+"\t");			// 회사이름
				System.out.print(item.get("ptrn")+"\t");			// 유병자/4세대실손보험
				System.out.print(item.get("mog")+"\t");				// 특약이름
				System.out.print(item.get("prdNm")+"\t");			// 보험이름
				System.out.print(item.get("age")+"\t");				// 나이
				System.out.print(item.get("mlInsRt")+"\t");			// 남자 보험료
				System.out.println(item.get("fmlInsRt")+"\t");		// 여자 보험료
				
				String basDt = (String)item.get("basDt");
				String cmpyCd = (String)item.get("cmpyCd");
				String cmpyNm = (String)item.get("cmpyNm");
				String ptrn = (String)item.get("ptrn");
				String mog = (String)item.get("mog");
				String prdNm = (String)item.get("prdNm");
				String mlInsRt = (String)item.get("mlInsRt");
				String fmlInsRt = (String)item.get("fmlInsRt");
				
				BohumDataBean bohumTestBean = new BohumDataBean(basDt, cmpyCd, cmpyNm, ptrn, mog, prdNm, age, mlInsRt, fmlInsRt);
				bohumTestInfoArr.add(bohumTestBean);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return bohumTestInfoArr;
	}//getHohumDetail
	
	
	public ArrayList<BohumDataBean> getHohumDetail(String cmpyNm ,String prdNm,String age) {
		ArrayList<BohumDataBean> bohumTestInfoArr = new ArrayList<BohumDataBean>();
		try {
			StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1160100/service/GetMedicalReimbursementInsuranceInfoService/getInsuranceInfo"); /*URL*/
			urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=wKQumq5LX0aGJN19E3mLdne0GDiEtPtVpVY3tDVBkOYPc21sBxDu%2B4lUggPaO0ETQboYKIVcYuGsd5lxtqhYoQ%3D%3D");
			urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝占� �뜝�룞�삕*/
			urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�샇*/
			urlBuilder.append("&" + URLEncoder.encode("resultType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*�뜝�룞�삕�뜝�룞�삕�뜝�룞�삕�뜝占�(xml/json)*/
			urlBuilder.append("&" + URLEncoder.encode("cmpyNm","UTF-8") + "=" + URLEncoder.encode(cmpyNm, "UTF-8")); /*�뜝�룞�삕�뜝�룞�삕*/       
			urlBuilder.append("&" + URLEncoder.encode("prdNm","UTF-8") + "=" + URLEncoder.encode(prdNm, "UTF-8")); /*�뜝�룞�삕�뜝�룞�삕*/       
			urlBuilder.append("&" + URLEncoder.encode("age","UTF-8") + "=" + URLEncoder.encode(age, "UTF-8")); /*�뜝�룞�삕�뜝�룞�삕*/       
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
	        InputStreamReader in = new InputStreamReader((InputStream) conn.getContent(), "UTF-8");
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(in);
	        }  else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();
			System.out.println(sb.toString());

			String myData = sb.toString();
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject)parser.parse(myData);

			JSONObject jsonObj = (JSONObject)obj;
			JSONObject jsonResponse = (JSONObject)jsonObj.get("response");
			JSONObject jsonBody = (JSONObject)jsonResponse.get("body");
			JSONObject jsonItems = (JSONObject)jsonBody.get("items");
			JSONArray jsonItem = (JSONArray)jsonItems.get("item");
			System.out.println("basDt \t cmpyCd \t cmpyNm \t ptrn \t mog \t prdNm \t age \t mlInsRt \t fmlInsRt \t");
			for(int i=0;i<jsonItem.size();i++) {
				JSONObject item = (JSONObject)jsonItem.get(i);
				System.out.print(item.get("basDt")+"\t");
				System.out.print(item.get("cmpyCd")+"\t");
				System.out.print(item.get("cmpyNm")+"\t");
				System.out.print(item.get("ptrn")+"\t");
				System.out.print(item.get("mog")+"\t");
				System.out.print(item.get("prdNm")+"\t");
				System.out.print(item.get("age")+"\t");
				System.out.print(item.get("mlInsRt")+"\t");
				System.out.println(item.get("fmlInsRt")+"\t");
				
				String basDt = (String)item.get("basDt");
				String cmpyCd = (String)item.get("cmpyCd");
				String ptrn = (String)item.get("ptrn");
				String mog = (String)item.get("mog");
				String mlInsRt = (String)item.get("mlInsRt");
				String fmlInsRt = (String)item.get("fmlInsRt");
				
				BohumDataBean bohumTestBean = new BohumDataBean(basDt, cmpyCd, cmpyNm, ptrn, mog, prdNm, age, mlInsRt, fmlInsRt);
				bohumTestInfoArr.add(bohumTestBean);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return bohumTestInfoArr;
	}//getHohumDetail
}//BohumDetail
