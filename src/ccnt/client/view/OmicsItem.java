package ccnt.client.view;

import ccnt.client.ConceptDetailPanel;
import ccnt.client.service.GetContentService;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.xml.client.NodeList;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.ColumnLayoutData;

public class OmicsItem extends Panel{
	public OmicsItem(){
		init("基本信息");
		isBasic = true;
		rightPanel = new ViewTab();
		rightPanel.add(conceptItem);
		add(rightPanel, new ColumnLayoutData(.5));
	}
	
	public OmicsItem(final String label,final String uri){
		init(label);
		this.uri = uri;
	}
	
	protected void init(String label){
		this.label = label;
		setHeight(574);
    setIconCls("view-list");
    setCls("left_align");
    setLayout(new ColumnLayout());
		setTitle(label);
    leftPanel.add(swfWidget);
		add(leftPanel, new ColumnLayoutData(.5));
	}
	
	public void set(NodeList concepts, boolean loadMap){
		if(isBasic){
			String concept = ConceptDetailPanel.getConcept();
			conceptItem.insertAll(concepts, concept);
			rightPanel.queryAll();
			if(loadMap)
				swfWidget.set(concept);
		}
	}
	
	public void set(){
		if(!isBasic){
			GetContentService.Util.getInstance().getRemote("getClassObject?uri="+uri, new AsyncCallback(){
				public void onFailure(Throwable arg0) {
			    Window.alert("获取"+label+"组学属性信息失败");
			  }
				public void onSuccess(Object resp) {
					String properties = (String) resp;
					rightPanel = new ViewTab(self,properties);
					add(rightPanel, new ColumnLayoutData(.5));
					swfWidget.set(ConceptDetailPanel.getConcept(),uri);
				}
		  });
		}
	}
	
	OmicsItem self = this;
	ViewTab rightPanel;
	protected String label;
	protected String uri;
	protected VerticalPanel panel;
	private boolean isBasic = false;
	ConceptViewItem conceptItem = new ConceptViewItem();
	VerticalPanel leftPanel = new VerticalPanel();
  SwfFrame swfWidget = new SwfFrame();
}