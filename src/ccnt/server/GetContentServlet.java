package ccnt.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import ccnt.client.service.GetContentService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class GetContentServlet extends RemoteServiceServlet implements
    GetContentService {

	/*****************************************************************************
	 * 精确查询概念词
	 * 
	 * @param 查询词汇
	 * @return Concept List
	 */
	public String getRemote(String url) {
		String urlNew = (ontoTool + url).replace("#", "%23");
		return getService(urlNew).replace(';', '；');
	}
	
	public String getOtherService(String concept, String type){
		String keyword = concept;
	  try {
	    keyword = URLEncoder.encode(concept, "UTF-8");
	  } catch (UnsupportedEncodingException e1) {
	    e1.printStackTrace();
	  }
	  String urlNew = "";
	  if(type.equals("Pic")){
			urlNew = "http://10.214.33.123/search_pic/search.do?keyword=" + keyword + "&start=1";
			return "<picture-list></picture-list>";
	  }
	  else if(type.equals("Paper")){
			urlNew = "http://10.214.33.193:8080/MedTale/SearchPaper?keyword=" + keyword + "&pageNo=1";
			return "<paper-list><paper-size>0</paper-size></paper-list>";
	  }
		return "";
		//return getService(urlNew);
	}
	
	private String getService(String url){
		System.out.println(url);
		logger.info(url);
		String resStr = "";
		try {
			URL fullUrl = new URL(url);
			URLConnection urlc = fullUrl.openConnection();
			urlc.setConnectTimeout(0);
			urlc.setReadTimeout(0);
			urlc.setAllowUserInteraction(true);
			urlc.setDoOutput(true);
			urlc.setRequestProperty("Method", "GET");
			InputStream tmpIS = urlc.getInputStream();
			BufferedReader in = null;
			String ct = urlc.getContentType();
			String encoding = ct.split("charset=")[1].trim();
			// String encoding = urlc.getContentEncoding();
			if (encoding == null)
				encoding = "UTF-8";
			in = new BufferedReader(new InputStreamReader(tmpIS, encoding));
			String destStr = "";
			String inputLin = "";
			while ((inputLin = in.readLine()) != null) {
				destStr += inputLin + "\n";
			}

			resStr = new String(destStr.getBytes(encoding), encoding);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resStr;
	}

	private final static Logger logger = Logger
	    .getLogger(GetContentServlet.class);

	private final static String ontoTool = "http://dbmanager.cintcm.ac.cn/OntoTool/";
	//private final static String ontoTool = "http://10.214.33.222:8080/OntoTool/";
}