package ccnt.client.view;

import ccnt.client.ConceptDetailPanel;
import ccnt.client.service.GetContentService;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;
import com.google.gwt.xml.client.impl.DOMParseException;
import com.gwtext.client.widgets.MessageBox;

public class PropertyViewItem extends ViewItem {

	public PropertyViewItem(ViewTab parent, String name, int type, String uri) {
	  super(name, type);
	  this.parent = parent;
	  this.uri = uri;
  }

	public void queryWithLoading(){
		clear();
		final String concept = ConceptDetailPanel.getConcept();
		panel.add(new HTML("<div class=text style='padding:2 8px'>正在获取概念词\""+concept+"\"的"+name+"...</div>"));
		GetContentService.Util.getInstance().getRemote("getUriProperty?prefLabel="+concept+"&propertyUri="+uri+"&isProperty="+(type==ViewType.TEXT), new AsyncCallback(){
			public void onFailure(Throwable arg0) {
				MessageBox.alert("用户信息",name+"页查询失败，请刷新页面");
      }

			public void onSuccess(Object responseText) {
				if(concept.equals(ConceptDetailPanel.getConcept())){
					clear();
					insertAll((String) responseText);
				}
      }
		});
	}
	
	protected void insertAll(String responseText) {
		try{
			Document xmlDoc = XMLParser.parse(responseText);
			XMLParser.removeWhitespace(xmlDoc);
			Element root = xmlDoc.getDocumentElement();
			NodeList contents = root.getChildNodes();
			int size = contents.getLength();
			if(0 == size){
				parent.removeOne();
				return;
			}
			parent.add(this);
			for(int j = 0; j < size; j++){
				Element content = (Element)contents.item(j);
			  insert(content.toString(), content.getAttribute("db"));
			}
		} catch(DOMParseException e){
			//MessageBox.alert("用户信息","返回信息中含有无法解析的字符，请联系管理员检查后端数据："+e.getContents());
		}
	}
	
	ViewTab parent;
	String uri;
}
