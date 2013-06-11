package ccnt.client.view;

//import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.widgets.tree.TreeNode;

public class MyTreeNode extends TreeNode {
	
	MyTreeNode(Widget w, int t){
		super();
		tier = t;
	}
	
	MyTreeNode(String content, String db, int t){
		init(content,db,t);
	}
	
	MyTreeNode(String content, MyTreeNode parent){
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
		//super.removeItems();
	}
	
	public void close(){
		//super.removeItems();
		//super.addItem("");
	}
	
	private void init(String content, String db, int t){
		//Hyperlink link = new Hyperlink(content,content);
		//link.setTitle(content);
		//link.addClickListener(new SearchListener());
		//super.setWidget(link);
		//super.setTitle(content);
		dbname = db;
		tier = t;
		if(tier > 1)
			//super.addItem("")
			;
	}
	
	private int tier;
	private String dbname;
	
}
