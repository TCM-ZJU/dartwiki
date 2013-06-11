package ccnt.client.view;

import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

public class MyTreeItem extends TreeItem {
	
	MyTreeItem(Widget w, int t){
		super(w);
		tier = t;
	}
	
	MyTreeItem(String content, String db, int t){
		init(content,db,t);
	}
	
	MyTreeItem(String content, MyTreeItem parent){
		int t = parent.getTier() - 1;
		String db = parent.getDB();
		init(content, db, t);
	}
	
	public int getTier(){
		return tier;
	}
	
	public String getDB(){
		return dbname;
	}
	
	public void open(){
		super.removeItems();
	}
	
	public void close(){
		super.removeItems();
		super.addItem("");
	}
	
	private void init(String content, String db, int t){
		Hyperlink link = new Hyperlink(content,content);
		super.setWidget(link);
		super.setTitle(content);
		dbname = db;
		tier = t;
		if(tier > 1)
			super.addItem("");
	}
	
	private int tier;
	private String dbname;
	
}
