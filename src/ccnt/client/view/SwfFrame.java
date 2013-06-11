package ccnt.client.view;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Frame;

public class SwfFrame extends Frame{
	SwfFrame(){
		super("http://localhost:8080/mv/mv/index.html");
		this.setPixelSize(486, 574);
	}
	

	SwfFrame(String concept, String typeUri){
		super("http://localhost:8080/mv/mv/index.html?concept="+concept+"&typeUri="+typeUri);
		this.setPixelSize(486, 574);
	}
	public void set(String label){
		try{
			setIFrameContent(getElement(), label, ""); 
		} catch (com.google.gwt.core.client.JavaScriptException e){
			System.out.println(label);
		}
	}	

	public void set(String label, String url){		
		try{
			setIFrameContent(getElement(), label, url); 
		} catch (com.google.gwt.core.client.JavaScriptException e){
			System.out.println(label+" "+url);
		}
	}
	
  private static native void setIFrameContent(Element element, String s, String url) /*-{  	
  	//$wnd.swf.window.loadMapData(s, url);
  	//$wnd.alert(element.contentWindow.document.location);
  	element.contentWindow.loadMapData(s, url);
  }-*/;
}
