/**
 * @author mjh {@link mjh@zju.edu.cn}
 */
package ccnt.client.view;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.TreePanel;

public class TreeViewItem extends ViewItem{

	/**
	 * Constructor
	 * @param name 名称，如“父类”
	 * @param label 显示词，如“上位词”
	 */
	public TreeViewItem(String name, String label) {
	  super(name, type);
	  this.label = label;
	  ableTiers = tiers;
	}

	/**
	 * 设置ableTiers的值
	 * @param t 设置ableTiers的值，并在View Iterator里进行增减
	 */
	public void setTier(int t){
		ableTiers = t;
		if(t==0 && able){
			ViewIterator.get().remove(name);
		}
		else if(t>0 && !able){
			ViewIterator.get().add(name);			
		}
	}
	
	public int getAbleTier(){
		return ableTiers;
	}
	
	public int getTier(){
		return tiers;
	}
	
	public String getLabel(){
		return label;
	}
	
	/**
	 * 在显示单元里插入内容
	 * @param content 内容
	 * @param db 来源数据库
	 */
	public void insert(String content, String db){
		Document xmlDoc = XMLParser.parse(content);
		NodeList l = xmlDoc.getChildNodes();
		for(int i=0;i<l.getLength();i++){
			Node node = l.item(i);
			TreeNode root = new TreeNode(node.getFirstChild().getNodeValue() + "<span style='font:italic 10pt; color: #9C0000'>("+db+")</span>");
			insertChildren(root,node);
	    TreePanel tp = new TreePanel();
	    tp.setBodyBorder(false);
	    tp.setRootNode(root);
			panel.add(tp);
		}
	}
	
	private void insertChildren(TreeNode root, Node pnode){
		NodeList l = pnode.getChildNodes();		
		for(int i=1;i<l.getLength();i++){
			Node node = l.item(i);
			TreeNode tnode = new TreeNode(node.getFirstChild().getNodeValue());
			root.appendChild(tnode);
			insertChildren(tnode,node);
		}		
	}
	
	private int tiers = 3;
	private int ableTiers;
	private String label;
	private static int type = ViewType.TREE;
}
