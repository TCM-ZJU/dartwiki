package ccnt.client.view;

import java.util.Iterator;
import java.util.List;

public class ViewIterator implements Iterator {

	public static ViewIterator get(){
		if(iterator==null){
			iterator = new ViewIterator();
		}
		return iterator;
	}
	
	public ViewIterator(){}
	
	public ViewIterator(ViewItem[] vts){
		list = vts;
	}

	public ViewIterator(List input){
		list = new ViewItem[input.size()];
		for(int i=0; i<list.length; i++){
			list[i] = (ViewItem) input.get(i);
		}
	}
	
	public boolean hasNext() {
		return position < list.length;
	}

	public Object next() {		
		return list[position++];
	}	

	public void remove() {}
	
	public void remove(String name) {
		int index = indexOf(name);
		if(index >= 0)
			list[index].setAble(false);
	}
	
	public void add(String name){
		int index = indexOf(name);
		if(index >= 0)
			list[index].setAble(true);
	}
	
	public void setTier(String label, int iters){
		int i = 0;
		for(; i < list.length; i++){
			if(list[i].getType()==ViewType.TREE && ((TreeViewItem)list[i]).getLabel().equalsIgnoreCase(label))
				break;
		}
		if(i<list.length && list[i].getType() == ViewType.TREE)
			((TreeViewItem)list[i]).setTier(iters);
	}
	
	public void head(){
		position = 0;
	}

	private int indexOf(String name){
		for(int i = 0; i<list.length;i++)
			if(name.equalsIgnoreCase(list[i].getName()))
				return i;
		return -1;
	}
	
	public void clearAll(){
		for(head();hasNext();){
				ViewItem item = (ViewItem)next();
				item.clear();
		}
	}
	
	public void queryAll(){
	  for(head();hasNext();){
	  	ViewItem item = (ViewItem)next();
	  	if(item.isAble())
	  		item.queryWithLoading();
	  }
	}
	
	private static ViewIterator iterator = null;
	private ViewItem[] list = { //new PicViewItem(),
															//new PaperViewItem(),
															new ViewItem("定义",ViewType.TEXT),
															new ViewItem("异名",ViewType.LINK),
															new TreeViewItem("父类","上位词"),
															new TreeViewItem("子类","下位词"),
															new ViewItem("关联词",ViewType.TEXTLINK)};
	private int position = 0;	
}