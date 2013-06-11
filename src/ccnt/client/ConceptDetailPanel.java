package ccnt.client;

import ccnt.client.service.AsyncCallbackWithMask;
import ccnt.client.service.GetContentService;
import ccnt.client.view.OmicsItem;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;
import com.gwtext.client.widgets.Component;
import com.gwtext.client.widgets.TabPanel;

public class ConceptDetailPanel extends TabPanel {
	
	public static ConceptDetailPanel getPanel(){
		if(panel == null)
			panel = new ConceptDetailPanel();
		return panel;
	}
	
	private ConceptDetailPanel(){
		setCls("left_align");
		setTitle("词条内容");
		setHeight(600);
		add(basic);
	}	
	
	OmicsItem basic = new OmicsItem();
	static ConceptDetailPanel panel = null;
	
	void showDetail(final String concept, NodeList concepts, boolean loadMap){
		setConcept(concept);
		basic.set(concepts, loadMap);
		Component[] children = this.getComponents();
		for(int i=1; i<children.length; i++)
			this.remove(children[i], true);
    GetContentService.Util.getInstance().getRemote("getType?prefLabel="+concept, new AsyncCallbackWithMask("获取概念词\""+concept+"\"的组学信息"){		  
      protected void onSuccessInternal(Object result) {
      	System.out.println(((String) result));
      	try{
	      	Document xmlDoc = XMLParser.parse((String) result);
	    		NodeList types = xmlDoc.getElementsByTagName("type");
	    		System.out.println(types.getLength());
	    		for(int i=0;i<types.getLength();i++){
	    			Element type = (Element) types.item(i);
	    			OmicsItem omics = new OmicsItem(type.getAttribute("label"),type.getAttribute("uri"));
	    			add(omics);
	    			setActiveItem(i+1);
	    			omics.set();
	    		}
      	} catch(com.google.gwt.xml.client.impl.DOMParseException e) {
      		//<Results>
      		//<type label="中药" uri="http://cintcm.ac.cn/onto#herb"/>
      		//<type label="藏药" uri="http://cintcm.ac.cn/onto#TibetanMedicine"/>
      		//<type label="动植物" uri="http://cintcm.ac.cn/onto#animal&amp;plant"/>
      		//</Results>
      		System.out.println("unparsed");
    			String typeXml = (String) result;
    			typeXml = typeXml.substring(typeXml.indexOf("<Results"));
    			typeXml = typeXml.substring(typeXml.indexOf("><")+2);
    			int i = 0;
    			while(typeXml.indexOf("><")>-1){
    				String next = typeXml.substring(0,typeXml.indexOf("><"));
    				typeXml = typeXml.substring(typeXml.indexOf("><")+2);
    				//Window.alert(next);
	    			OmicsItem omics = new OmicsItem(next.substring(next.indexOf('"')+1, next.indexOf("\" ")),next.substring(next.indexOf("uri")+5, next.indexOf("\"/")));
	    			add(omics);
	    			setActiveItem(++i);
	    			omics.set();
    			}
      	}
  			setActiveItem(0);
  			ShowPanel.getPanel().setActiveItem(2);
	    }
    });
	}
	
	static String concept;
	public static String getConcept(){
		return concept;
	}
	private static void setConcept(String c){
		concept = c;
	}
}
