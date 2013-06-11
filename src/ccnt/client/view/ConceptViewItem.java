package ccnt.client.view;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;

import com.google.gwt.xml.client.NodeList;

public class ConceptViewItem extends ViewItem {

	public ConceptViewItem() {
	  super("概念词", ViewType.CONCEPT);
	  // TODO Auto-generated constructor stub
  }

	/**
	 * 在显示单元里插入内容
	 * @param content 内容
	 */
	public void insert(String content){
		HorizontalPanel contentPanel = new HorizontalPanel();
		contentPanel.add(new HTML("<img src='image/arrow.gif' />"));
		contentPanel.add(new Hyperlink(content,content));
		panel.add(contentPanel);
	}
	
	public void insertAll(NodeList contents, String exclude){
		panel.clear();
		for(int i=0; i<contents.getLength(); i++){
			String content = contents.item(i).getFirstChild().getNodeValue();
			if(!content.equals(exclude))
				insert(content);
		}
	}
}
