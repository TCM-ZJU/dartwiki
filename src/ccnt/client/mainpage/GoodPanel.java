package ccnt.client.mainpage;

import com.gwtext.client.widgets.PaddedPanel;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Tool;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.ColumnLayoutData;
import com.gwtext.client.widgets.layout.TableLayout;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;

public class GoodPanel extends Panel {
	public static GoodPanel getPanel(){
		if(panel == null)
			panel = new GoodPanel();
		return panel;
	}
	static GoodPanel panel = null;
	
	public GoodPanel(){
		setTitle("优质词条");
		setSize(391, 300);
		setLayout(new TableLayout(2));
		this.setTools(new Tool[]{new MoreTool("good")});
		add(new PaddedPanel(new GoodOne("dahuang.jpg","大黄"),4));
		add(new PaddedPanel(new GoodOne("dahuang.jpg","大黄"),4));
		add(new PaddedPanel(new GoodOne("dahuang.jpg","大黄"),4));
		add(new PaddedPanel(new GoodOne("dahuang.jpg","大黄"),4));
		add(new PaddedPanel(new GoodOne("dahuang.jpg","大黄"),4));
		add(new PaddedPanel(new GoodOne("dahuang.jpg","大黄"),4));
	}
	
	class GoodOne extends Panel {
		GoodOne(String picsrc, String concept){
			this.setHeader(false);
			this.setLayout(new ColumnLayout());
			this.setBorder(false);
			this.setMargins(10, 10,10, 10);
			setWidth(150);
			add(new HTML("<img src='imgsrc/" + picsrc + "' width='75'/>"), new ColumnLayoutData(.5));
			add(new Hyperlink(concept,concept));
		}
	}
}
