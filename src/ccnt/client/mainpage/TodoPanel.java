package ccnt.client.mainpage;

import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Tool;
import com.gwtext.client.widgets.layout.FitLayout;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;

public class TodoPanel extends Panel {

	public static TodoPanel getPanel(){
		if(panel == null)
			panel = new TodoPanel();
		return panel;
	}
	static TodoPanel panel = null;
	
	public TodoPanel(){
		setTitle("待完善词条");
		this.setTools(new Tool[]{new MoreTool("todo")});
		setSize(390, 300);
		Grid g = new Grid(8,2);
		setLayout(new FitLayout());
		g.setWidget(0, 0, new HTML("<b>词条名称</b>"));
		g.setWidget(0, 1, new HTML("<b>更新时间</b>"));
		
		g.setWidget(1, 0, new Hyperlink("老年人","老年人"));
		g.setText(1, 1, "2007-09-09");
		g.setWidget(2, 0,new Hyperlink("CCNT","CCNT"));
		g.setText(2, 1, "2008-09-09");
		g.setWidget(3, 0, new Hyperlink("便秘","便秘"));
		g.setText(3, 1, "2008-11-23");		
		g.setWidget(4, 0, new Hyperlink("老年人","老年人"));
		g.setText(4, 1, "2007-09-09");
		g.setWidget(5, 0,new Hyperlink("CCNT","CCNT"));
		g.setText(5, 1, "2008-09-09");
		g.setWidget(6, 0, new Hyperlink("便秘","便秘"));
		g.setText(6, 1, "2008-11-23");		
		g.setWidget(7, 0, new Hyperlink("老年人","老年人"));
		g.setText(7, 1, "2007-09-09");
		add(g);
	}
}
