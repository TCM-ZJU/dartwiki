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

public class PicViewItem extends ViewItem {

	public PicViewItem() {
		super("图片", ViewType.PIC);
	}

	public void queryWithLoading() {
		clear();
		final String concept = ConceptDetailPanel.getConcept();
		panel.add(new HTML("<div class=text style='padding:2 8px'>正在获取概念词\""
		    + concept + "\"的" + name + "...</div>"));
		GetContentService.Util.getInstance().getOtherService(concept, "Pic", new AsyncCallback() {
			    public void onFailure(Throwable arg0) {
				    MessageBox.alert("用户信息", name + "页查询失败，请刷新页面");
			    }

			    public void onSuccess(Object response) {
				    if (concept.equals(ConceptDetailPanel.getConcept())) {
					    clear();
					    System.out.println((String) response);
					    try {
						    Document xmlDoc = XMLParser.parse((String) response);
						    XMLParser.removeWhitespace(xmlDoc);
						    Element root = xmlDoc.getDocumentElement();

						    /***************************************************************
								 * <picture-list> <time>1297</time> <picture-size>95</picture-size>
								 * <picture> 
								 * 		<name>大头黄花蒿</name>
								 * 		<source-path>http://10.214.33.123/search_pic/Picture_source/1660.jpg</source-path>
								 * 		<abbr-path>http://10.214.33.123/search_pic/Picture_abbr/1660.jpg</abbr-path>
								 * </picture> ... </picture-list>
								 */
						    NodeList contents = root.getElementsByTagName("picture");
						    int size = contents.getLength();
						    if (0 == size)
							    empty();
						    for (int j = 0; j < size; j++) {
							    Element picture = (Element) contents.item(j);
							    String src = ((Element) picture.getElementsByTagName("source-path").item(0)).getFirstChild().getNodeValue();
							    String name = ((Element) picture.getElementsByTagName("name").item(0)).getFirstChild().getNodeValue();
							    HTML html = new HTML("<img src='" + src
									    + "' width=180><br><span>(" + name + ")</span>");
									panel.add(html);
						    }
					    } catch (DOMParseException e) {
						    MessageBox.alert(name+"用户信息", "返回信息中含有无法解析的字符，请联系管理员检查后端数据："
						        + e.getContents());
					    }
				    }
			    }
		    });
	}
}
