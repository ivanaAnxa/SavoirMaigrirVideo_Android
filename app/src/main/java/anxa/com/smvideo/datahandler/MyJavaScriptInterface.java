package anxa.com.smvideo.datahandler;


import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import anxa.com.smvideo.ApplicationData;

public class MyJavaScriptInterface {

	static String HTMLResponse = "";
	public void showHTML(String html){
        //parse the string. String is nothing but the HTML. Look for 
		//needed tags and post an intent to my service. 
		HTMLResponse = html;
		
		HTMLResponse = HTMLResponse.replace("<![CDATA[", "");
		HTMLResponse = HTMLResponse.replace("]]>", "");
		Log.d("HTMLResponse", "HTMLResponse  B = "+HTMLResponse);
		parseOnlineXML();
    } 
	public static void parseOnlineXML(){
	//	String xml = XMLfunctions.getXML();
		String xml = MyJavaScriptInterface.HTMLResponse;
		Log.d("parseOnlineXML@MYJava", "xml 1 = "+xml);
        Document doc = XMLfunctions.XMLfromString(xml);
                
        int numResults = XMLfunctions.numResults(doc);
        
        if((numResults <= 0)){
     //   	Toast.makeText(XMLDataSet.this, "Geen resultaten gevonden", Toast.LENGTH_LONG).show();  
     //   	finish();
        }
        NodeList nodes = doc.getElementsByTagName("profile_data");
        Log.d("nodes", "nodes = "+nodes+" "+nodes.getLength());
        
        for (int i = 0; i < nodes.getLength(); i++) { 							
			Element e = (Element)nodes.item(i);
			
			Log.d("nodes",i+" "+e.getNodeName()+" "+e.getNodeValue());
			
			
//			ApplicationData.getInstance().regid = XMLfunctions.getValue(e, "regid");
//			ApplicationData.getInstance().prenom = XMLfunctions.getValue(e, "prenom");
//			ApplicationData.getInstance().nom = XMLfunctions.getValue(e, "nom");
//			ApplicationData.getInstance().username = XMLfunctions.getValue(e, "username");
//			ApplicationData.getInstance().program = XMLfunctions.getValue(e, "program");
//			ApplicationData.getInstance().profileimg = XMLfunctions.getValue(e, "profileimg");
//
////			MainActivity.proFileImageURL = XMLfunctions.getValue(e, "profileimg");
//
//			Log.d("nodes", "profileimg = "+XMLfunctions.getValue(e, "profileimg"));
//			Log.d("nodes", "regid = "+XMLfunctions.getValue(e, "regid"));
//			Log.d("nodes", "program = "+XMLfunctions.getValue(e, "program"));
//			Log.d("nodes", "username = "+XMLfunctions.getValue(e, "username"));
//
//			ApplicationData.getInstance().height = XMLfunctions.getValue(e, "height");
//			ApplicationData.getInstance().weight = XMLfunctions.getValue(e, "weight");
		}
        
	
	}
	
	
	public void getTitle(String title){
		Log.d("HTMLResponse", "title = "+title);
        ApplicationData.getInstance().pageTitle = title;
    } 
	
}
/* An instance of this class will be registered as a JavaScript interface */  
/*class MyJavaScriptInterface  
{  
    @SuppressWarnings("unused")  
    public void showHTML(String html)  
    {  
	        new AlertDialog.Builder(myApp)  
            .setTitle("HTML")  
            .setMessage(html)  
            .setPositiveButton(android.R.string.ok, null)  
        .setCancelable(false)  
        .create()  
        .show();  
			Log.d("html", "html"+html);
    }  
}*/