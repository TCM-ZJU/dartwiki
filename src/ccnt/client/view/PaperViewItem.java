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

public class PaperViewItem extends ViewItem {

	public PaperViewItem() {
		super("文献", ViewType.PAPER);
	}

	public void queryWithLoading() {
		clear();
		final String concept = ConceptDetailPanel.getConcept();
		panel.add(new HTML("<div class=text style='padding:2 8px'>正在获取概念词\""
		    + concept + "\"的" + name + "...</div>"));
		GetContentService.Util.getInstance().getOtherService(concept, "Paper", new AsyncCallback() {
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
								 * <?xml version="1.0" encoding="gbk" ?>
								 * <paper-list>
								 * 	<paper-size>25454</paper-size> 
								 * 	<paper>
								 * 		<zuozhe>徐选明, 季瑛, 刘新, 郭益磊</zuozhe>
								 * 		<timing>大黄中大黄素提取工艺的优化</timing>
								 * 		<zhaiyao>目的:筛选大黄中大黄素提取的最佳工艺条件。方法:根据大黄素的理化特性,选取乙醇浓度、提取时间、提取次数等3个因素,进行正交优化试验。结果:采用50%乙醇加热回流3次,每次2h为最佳提取工艺条件。结论:本工艺合理可行,有利于生产。</zhaiyao>
								 * 		<kanming>中国中医药信息杂志</kanming>
								 * 		<nianfen>2004</nianfen>
								 * </paper> 
								 * ... 
								 * </paper-list>
								 */
						    NodeList contents = root.getElementsByTagName("paper");
						    int size = contents.getLength();
						    if (0 == size)
							    empty();
						    for (int j = 0; j < size; j++) {
							    Element paper = (Element) contents.item(j);
							    String zuozhe = ((Element) paper.getElementsByTagName("zuozhe").item(0)).getFirstChild().getNodeValue();
							    String timing = ((Element) paper.getElementsByTagName("timing").item(0)).getFirstChild().getNodeValue();
							    String zhaiyao = "";
							    NodeList zhaiyaoList = ((Element) paper.getElementsByTagName("zhaiyao").item(0)).getChildNodes();
							    if(zhaiyaoList.getLength() > 0){
							    	zhaiyao = zhaiyaoList.item(0).getNodeValue();
							    }						    
							    String kanming = ((Element) paper.getElementsByTagName("kanming").item(0)).getFirstChild().getNodeValue();
							    String nianfen = ((Element) paper.getElementsByTagName("nianfen").item(0)).getFirstChild().getNodeValue();
							    HTML html = new HTML("<img src='image/pdf16.jpg'>" +
							    		"<a href=\"javascript:void(0)\" " +
							    		"onclick=\"window.open('http://localhost:8080/MedTale/UrlQuery?keyword="+timing+"')\">《"+timing+"》</a>," +
							    		zuozhe+","+kanming+"("+nianfen+")<br>" +
							    		"<span>" + zhaiyao + "</span>");
							    //http://58.206.203.179:8080/MedTale/UrlQuery?keyword=
							    panel.add(html);
						    }
					    } catch (DOMParseException e) {
						    MessageBox.alert("用户信息", name+"返回信息中含有无法解析的字符，请联系管理员检查后端数据："
						        + e.getContents());
					    }
				    }
			    }
		    });
	}
}
