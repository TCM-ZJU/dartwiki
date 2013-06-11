package ccnt.client;

import ccnt.client.service.AsyncCallbackWithMask;
import ccnt.client.service.GetContentService;
import ccnt.client.view.ViewItem;
import ccnt.client.view.ViewType;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.CardLayout;
import com.gwtext.client.widgets.layout.RowLayout;
import com.gwtext.client.widgets.layout.RowLayoutData;

public class ShowPanel extends Panel {
	public static ShowPanel getPanel(){
		if(panel == null)
			panel = new ShowPanel();
		return panel;
	}
	static ShowPanel panel = null;
	
	private ShowPanel(){
		setHeader(false);
		setCls("wrap");
		setLayout(new CardLayout());
		setId("show-panel");
		
		add(buildEmptyPage());
		add(buildSearchResultPage());
		add(ConceptDetailPanel.getPanel());
		
		setActiveItem(0);
	}
	
	Panel nonePanel = new Panel();
	Panel searchPanel = new Panel();
	
	void showDetail(String c){
		showDetail(c, true);
	}
	void showDetail(String c, final boolean loadMap){
		//Window.alert(c);
		setActiveItem(0);
		setConcept(c);
		GetContentService.Util.getInstance().getRemote("getConcept?word="+concept, new AsyncCallbackWithMask("检测概念词\""+concept+"\""){		  
      protected void onSuccessInternal(Object result) {
	      System.out.println(result);
      	Document xmlDoc = XMLParser.parse((String) result);
    		NodeList concepts = xmlDoc.getElementsByTagName("concept");
    		boolean isC = false;
    		for(int i=0;i<concepts.getLength();i++){
    			String conceptString = concepts.item(i).getFirstChild().getNodeValue();
    			if(conceptString.equals(concept)){
    				isC = true;
    				ConceptDetailPanel.getPanel().showDetail(concept,concepts, loadMap);
    				break;
    			}
    		}
      	if(!isC)
      		searchResult(concepts, true);
		  }});
	}
	
	void search(String c, boolean isFuzzy){
		setConcept(c);
		GetContentService.Util.getInstance().getRemote("getConcept?word="+concept+"&isFuzzy="+isFuzzy, new AsyncCallbackWithMask("搜索概念词\""+concept+"\""){		  
      protected void onSuccessInternal(Object result) {
      	System.out.println(result);
      	Document xmlDoc = XMLParser.parse((String) result);
    		NodeList concepts = xmlDoc.getElementsByTagName("concept");
      	searchResult(concepts, false);
		  }});
	}
		
	private void searchResult(NodeList concepts, boolean notConcept){
		searchPanel.clear();
		nonePanel.clear();
		if(notConcept){
			nonePanel.add(new HTML("<div style='align:center;background:#acf9ef;height:50px;width:450px;margin:10px'><br>&nbsp&nbsp<b>"+concept+"</b>没有作为概念词收录在百科中，欢迎您来<a href=#>创建词条</a>促进中国医药信息化。<br>&nbsp&nbsp以它为异名的概念词如下，请点击了解详情:<div>"));
			searchPanel.add(nonePanel, new RowLayoutData(100));
		}
		else{
			nonePanel.add(new HTML("<div style='align:center;line-height:17px;width:450px;'>&nbsp&nbsp中医百科搜索，共找到"+concepts.getLength()+"个概念词条<div>"));
			searchPanel.add(nonePanel, new RowLayoutData(30));
		}
		setActiveItem(1);
		for(int i=0; i<concepts.getLength(); i++){
			String c = (String) concepts.item(i).getFirstChild().getNodeValue();
			searchPanel.add(new SearchResult(c));
		}
	}
	
	private VerticalPanel buildEmptyPage(){
		VerticalPanel empty = new VerticalPanel();
		empty.setHeight("600");
		empty.add(new HTML("请稍后..."));
		return empty;
	}
	
	private Panel buildSearchResultPage(){
		searchPanel.setTitle("搜索结果");
		searchPanel.setHeight(600);
		searchPanel.setLayout(new RowLayout());
		searchPanel.setCls("left_align");
		searchPanel.setPaddings(10);
		
		nonePanel.setCls("left-align");
		nonePanel.setPaddings(10);
		nonePanel.setBorder(false);
		searchPanel.setAutoScroll(true);
		return searchPanel;
	}
	
	class SearchResult extends VerticalPanel{
		SearchResult(String c){
			System.out.println(c);
			Hyperlink conceptLink= new Hyperlink(c,c);
			conceptLink.setStyleName("hot_dt");
			add(conceptLink);
			ViewItem vi = new ViewItem("定义", ViewType.TEXT);
			vi.setHeader(false);
			add(vi);
			vi.queryWithLoading(c);
		}
	}
	
	private void setConcept(String c){
		concept = c;
		SearchPanel.setWord(c);
	}
	
	String concept;
}
