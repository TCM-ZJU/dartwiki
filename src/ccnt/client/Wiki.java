/**
 * 
 */
package ccnt.client;

import ccnt.client.mainpage.GoodPanel;
import ccnt.client.mainpage.HighlightPanel;
import ccnt.client.mainpage.TodoPanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * @author ccnt
 *
 */
public class Wiki implements EntryPoint, HistoryListener {

	/* (non-Javadoc)
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	public void onModuleLoad() {
  	homepage=true;
    RootPanel.get("searchPanel").add(SearchPanel.getPanel());
    RootPanel.get("highlight").add(HighlightPanel.getPanel());
    HorizontalPanel goodhp = new HorizontalPanel();
    goodhp.add(GoodPanel.getPanel());
    goodhp.add(TodoPanel.getPanel());
    RootPanel.get("good").add(goodhp);
    sp = ShowPanel.getPanel();
  	RootPanel.get("show").add(sp);
  	RootPanel.get("show").setVisible(false);
  	
		//RootPanel.get("searchPanel").add(SearchPanel.getPanel());
		//sp = ShowPanel.getPanel();
  	//RootPanel.get("show").add(sp);
  	History.addHistoryListener(this);
  	initJavaScriptApi();
	}

	private native void initJavaScriptApi() /*-{
  	$wnd.newHistoryItem = function (concept) { @ccnt.client.Wiki::newHistoryItem(Ljava/lang/String;)(concept) }; 
	}-*/; 


	// This method will not be discarded since 
	// (1) initJavaScriptApi() calls this method from JSNI 
	// (2) initJavaScriptApi() is definitely called 
	public static void newHistoryItem(String concept) {
		History.newItem(concept);
	}

	/**
   * This is the method for forward and backward button
   */
	public void onHistoryChanged(String historyToken) {
		if(historyToken == null || historyToken.equals("") || historyToken.equals("null"))
			return;
		swapToShow();
		if(historyToken.startsWith("$")){//fuzzy parameter
			String word = historyToken.substring(1, historyToken.indexOf('&'));
			String isFuzzy = historyToken.substring(historyToken.indexOf('&')+1);
	  	sp.search(word,isFuzzy.equals("true"));
		}
		else if(historyToken.startsWith("!")){//graph double click
			String word = historyToken.substring(1, historyToken.length());
			sp.showDetail(word,false);
		}
		else
			sp.showDetail(historyToken);
  }
	static ShowPanel sp;
	
	public static void swapToShow(){
  	if(homepage){
  		RootPanel.get("main").setVisible(false);
  		RootPanel.get("show").setVisible(true);
  		homepage=false;
  	}
  }	
	static boolean homepage;

}
