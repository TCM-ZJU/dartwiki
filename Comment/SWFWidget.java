package ccnt.client.view;

import com.google.gwt.user.client.ui.HTMLPanel;

public class SWFWidget extends HTMLPanel{
	/*SWFWidget(String url, String label){
		this();
		loadMapData(label, url);
	}*/
	
	SWFWidget(String label){
		super(
				/*"<script language='JavaScript'>" +
				"<!--" +
				"var isSwfCreated = false;" +
				"var pendLoadMap = false;" +
				"var concept = '';" +
				"var typeUri = '';" +
				"loadMapData('"+label+"','');" +
				"function swfCreated(){" +
				"	isSwfCreated = true;" +
				"	if (pendLoadMap){" +
				"		loadMapData(concept, typeUri);" +
				"	}" +
				"}" +
				"function getMyApp(appName){" +
				" if (navigator.appName.indexOf ('Microsoft') !=-1){" +
				"  return window[appName];" +
				" }	else {" +
				"  return document[appName];" +
				" }" +
				"}" +
				"function loadMapData(c, t){" +
				"	if (isSwfCreated){" +
				"  getMyApp('mv').loadMap(c,t);" +
				" }	else {" +
				"  pendLoadMap = true;" +
				"  concept = c;" +
				"  typeUri = t;" +
				" }" +
				"}" +
				"function changeMap(concept, typeUri){" +
				" alert('concept: ' + concept + '\ntypeUri: ' + typeUri);" +
				"}" +
				"// -->" +
				"</script>" +*/
				"<object id='mv' width='490' height='600'" +
				"codebase='http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab'>" +
				"<param name='movie' value='mv.swf' />" +
				"<param name='quality' value='high' />" +
				"<param name='bgcolor' value='#f8f8f8' />" +
				"<param name='allowScriptAccess' value='sameDomain' />" +
				"<embed src='mv.swf' quality='high' bgcolor='#f8f8f8'" +
				"	width='100%' height='100%' name='mv' align='middle'" +
				"	play='true'	loop='false' quality='high'	allowScriptAccess='sameDomain'" +
				"	type='application/x-shockwave-flash'" +
				"	pluginspage='http://www.adobe.com/go/getflashplayer'>" +
				"</embed>" +
				"</object>");		
	}
	
	public native void loadMapData(String c, String t)/*-{
		var player = $doc.getElementById("mv");
		return player.loadMapData(c, t);
	}-*/;
}
