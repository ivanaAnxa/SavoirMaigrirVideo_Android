package anxa.com.smvideo.datahandler;


import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class XMLfunctions {

	public final static Document XMLfromString(String xml){
		
		Document doc = null;
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
        	
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			InputSource is = new InputSource();
	        is.setCharacterStream(new StringReader(xml));
	        doc = db.parse(is); 
	        
		} catch (ParserConfigurationException e) {
		//	System.out.println("XML parse error: " + e.getMessage());
			Log.d("error", "XML parse error: " + e.getMessage());
			return null;
		} catch (SAXException e) {
		//	System.out.println("Wrong XML file structure: " + e.getMessage());
			Log.d("error", "XML parse error: " + e.getMessage());
			return null;
		} catch (IOException e) {
		//	System.out.println("I/O exeption: " + e.getMessage());
			Log.d("error", "XML parse error: " + e.getMessage());
			return null;
		}
		       
        return doc;
        
	}
	
	/** Returns element value
	  * @param elem element (it is XML tag)
	  * @return Element value otherwise empty String
	  */
	 public final static String getElementValue(Node elem ) {
	     Node kid;
	     try{
	    	 if( elem != null){
	     
	         if (elem.hasChildNodes()){
	             for( kid = elem.getFirstChild(); kid != null; kid = kid.getNextSibling() ){
	            	 
	                 if( kid.getNodeType() == Node.TEXT_NODE  ){
	                     return kid.getNodeValue();
	                 }else if (kid.getNodeType() == Node.COMMENT_NODE){
	                	 String cData = kid.getNodeValue();
	                	 int end =  cData.indexOf("]]");
	                	 return cData.substring(7, end);
	                 
	                 }
	             }
	         }
	     }}catch(Exception e){
	    	 e.printStackTrace();
	    	 
	     }
	     return "";
	 }
		 
//	 public static String getXML(){
//			String line = null;
//			Log.d("line", "starting");
//			try {
//
//				DefaultHttpClient httpClient = new DefaultHttpClient();
//
//
//				BasicCookieStore cookieStore = new BasicCookieStore();
//
//				String login_cookie_string = MainActivity.settings.getString("cookie", "");
//				String[] cookie_parts = null;
//				if(login_cookie_string.length()> 0)
//				{
//					Log.d("line", "cookie_parts");
//					//debug_view.setText(login_cookie_string);
//					cookie_parts = login_cookie_string.split("=");
//					if(cookie_parts.length == 2)
//					{
//						for(int t=0;t<cookie_parts.length;t++)
//						{
//							//debug_view.append("part "+cookie_parts[t]);
//						}
//						Cookie login_cookie = new BasicClientCookie(cookie_parts[0],cookie_parts[1]);
//						((BasicClientCookie) login_cookie).setDomain("aujourdhui.com");
//						cookieStore.addCookie(login_cookie);
//					}
//					else
//					{
//						String tempName = "";
//						String tempValue = "";
//						for(int t=0,i=0;t<=cookie_parts.length;t++,i++)
//						{
//							//debug_view.append("part "+cookie_parts[t]);
//							if (i==2){
//								Log.d("line", "tempName = "+tempName);
//								Log.d("line", "tempValue = "+tempValue);
//								Cookie login_cookie = new BasicClientCookie(tempName,tempValue);
//								((BasicClientCookie) login_cookie).setDomain("aujourdhui.com");
//								cookieStore.addCookie(login_cookie);
//								i=0;
//							}
//							if(t!=cookie_parts.length){
//								Log.d("line", "cookie_parts["+t+"] = "+cookie_parts[t]);
//
//								if(i==0){
//									tempName = cookie_parts[t];
//
//								}else if (i==1){
//									tempValue = cookie_parts[t];
//
//								}
//							}
//
//						}
//
//						//debug_view.setText(" couldnt split cookies ");
//					}
//				}
//				else
//				{
//					//debug_view.setText(" no cookie ");
//				}
//
//				httpClient.setCookieStore(cookieStore);
//
////				HttpPost httpPost = new HttpPost("http://p-xr.com/xml");
//				HttpPost httpPost = new HttpPost("http://qc.savoir-maigrir.aujourdhui.com/iphone/compte/mon-profil-xml.asp");
//				Log.d("line", "parsing");
//				HttpResponse httpResponse = httpClient.execute(httpPost);
//				HttpEntity httpEntity = httpResponse.getEntity();
//				line = EntityUtils.toString(httpEntity);
//				Log.d("line", "line = "+line);
//
//			} catch (UnsupportedEncodingException e) {
//				line = "<results status=\"error\"><msg>Can't connect to server</msg></results>";
//			} catch (MalformedURLException e) {
//				line = "<results status=\"error\"><msg>Can't connect to server</msg></results>";
//			} catch (IOException e) {
//				line = "<results status=\"error\"><msg>Can't connect to server</msg></results>";
//			}
//			System.out.println("line"+line);
//			return line;
//
//	}
	 
	public static int numResults(Document doc){

		int res = -1;
		
		try{
			Node results = doc.getDocumentElement();
			res = Integer.valueOf(results.getAttributes().getNamedItem("count").getNodeValue());
		}catch(Exception e ){
			res = -1;
			Log.d("XMLfunctions", "error = "+e.toString());
		}
		
		return res;
	}

	public static String getValue(Element item, String str) {
		NodeList listing = item.getChildNodes();
		
		
		NodeList n = item.getElementsByTagName(str);
		return XMLfunctions.getElementValue(n.item(0));
	}
}
