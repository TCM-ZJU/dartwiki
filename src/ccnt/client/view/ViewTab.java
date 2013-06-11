package ccnt.client.view;

import java.util.ArrayList;
import java.util.List;

import ccnt.client.ConceptDetailPanel;

import com.google.gwt.user.client.Window;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;
import com.gwtext.client.widgets.TabPanel;

public class ViewTab extends TabPanel {

	public ViewTab() {
		setHeight(574);
		this.setEnableTabScroll(true);
	  iter = ViewIterator.get();
	  for(iter.head();iter.hasNext();){
	  	ViewItem item = (ViewItem)iter.next();
	  	if(item.isAble()==true){
	  		add(item);
	  	}
	  }
  }
	
	public ViewTab(OmicsItem parent, String propertyXml){
		this.parent = parent;
		setHeight(574);
		this.setEnableTabScroll(true);
		System.out.println(propertyXml);
		List tabList = new ArrayList();
		try{
			Document xmlDoc = XMLParser.parse(propertyXml);
			Element e = xmlDoc.getDocumentElement();
			NodeList list = e.getChildNodes();
			for(int i=0; i<list.getLength(); i++){
				Element tabContent = (Element) list.item(i);
				String tabName = tabContent.getNodeName();
				PropertyViewItem item;
				if(tabName.equals("property")){
					item = new PropertyViewItem(this,tabContent.getAttribute("label"),ViewType.TEXT,tabContent.getAttribute("uri"));
				} else {
					item = new PropertyViewItem(this,tabContent.getAttribute("property"),ViewType.LINK,tabContent.getAttribute("propertyUri"));
				}
				tabList.add(item);
			}
		} catch(com.google.gwt.xml.client.impl.DOMParseException e) {
			propertyXml = propertyXml.substring(propertyXml.indexOf("<type"));
			propertyXml = propertyXml.substring(propertyXml.indexOf("><")+2);			
			while(propertyXml.indexOf("><")>-1){
				String next = propertyXml.substring(0,propertyXml.indexOf("><"));
				propertyXml = propertyXml.substring(propertyXml.indexOf("><")+2);
				//Window.alert(next);
				String tabName = next.substring(0, next.indexOf(' '));
				PropertyViewItem item;
				if(tabName.equals("property")){
					item = new PropertyViewItem(this,next.substring(next.indexOf('"')+1, next.indexOf("\" ")),
							ViewType.TEXT,next.substring(next.indexOf("uri")+5, next.indexOf("\"/")));
				} else {
					item = new PropertyViewItem(this,next.substring(next.indexOf('"')+1, next.indexOf("\" ")),
							ViewType.LINK,next.substring(next.indexOf("propertyUri")+13, next.indexOf("\" object")));
				}
				tabList.add(item);
			}
		}
		iter = new ViewIterator(tabList);
		size = tabList.size();
		iter.queryAll();
	}
	
	public void queryAll(){
		iter.queryAll();
	}
	
	public void removeOne(){
		size--;
		if(size==0)
			ConceptDetailPanel.getPanel().remove(parent,true);
	}
	ViewIterator iter;
	OmicsItem parent;
	int size;
}