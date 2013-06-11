package ccnt.client.view;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.layout.RowLayoutData;

/**
 * 展现范围的选择对话框，
 * 包括"定义"，"异名"，"关联词"复选框，"父类"和"子类"复选框组，
 * 提供"全选"和"全不选"功能，单例模式，默认全选，
 * @author MiJinhua
 */
public class ViewDialog extends Window{

	static ViewDialog rangeDialog = null;
	/**
	 * 单例模式
	 */
	public static ViewDialog getDialog(){
		if(rangeDialog == null){
			rangeDialog = new ViewDialog();
		}
		return rangeDialog;
	}
	
	private ViewDialog() {
		setTitle("展示视图选择对话框","settings-icon");
		add(buildViewDialog(), new RowLayoutData("90%"));
		add(buildActionPanel(),new RowLayoutData(40));
		setSize(200, 350);
		AllChecked();
	}

	//按键动作
	ButtonListenerAdapter bListener = new ButtonListenerAdapter() {   
    public void onClick(Button button, EventObject e) {
      String btnLabel = button.getText();
      if(btnLabel == "全选"){
      	AllChecked();
      } 
  		else if (btnLabel == "全不选") {
  			NoneChecked();
      }	
  		else if (btnLabel == "保存") {
  			SetRange();
      	close();
      }
    }

  };   

	//全选
	private void AllChecked(){
		for(int i = 0; i < length; i++)
			cBoxes[i].setChecked(true);
		for(int i = 0; i < treeNum; i++){
			cTrees[i][cTrees[i].length-1].setChecked(true);
			Filiation(cTrees[i],cTrees[i].length-1,true);			
		}
	}
	//全不选
	private void NoneChecked(){
		for(int i = 0; i<length; i++)
			cBoxes[i].setChecked(false);
		for(int i = 0; i < treeNum; i++){
			cTrees[i][cTrees[i].length-1].setChecked(false);
			Filiation(cTrees[i],cTrees[i].length-1,false);
		}
	}
	//设定展示区域
	private void SetRange(){
		for(int i = 0; i<length; i++){
			if(cBoxes[i].isChecked())
				iter.add(cBoxes[i].getText());
			else
				iter.remove(cBoxes[i].getText());
		}
		for(int i = 0; i < treeNum; i++){
			String label = cTrees[i][0].getText();
			int j = cTrees[i].length-1;
			while(j>= 0){
				if(cTrees[i][j].isChecked())
					break;
				j--;
			}
			iter.setTier(label, j+1);
		}
	}	
	
	//父子关系复选框逻辑
	private void Filiation(CheckBox[] array, int index, boolean checked){
		for( int i=0; i<index; i++ ){
    	array[i].setChecked(checked);
    	array[i].setEnabled(!checked);
    }
	}
	//父子关系复选框监听器
	private class FiliationListener implements ClickListener {

    private CheckBox[] array;
    private int index;

    public FiliationListener(CheckBox[] array, int index ) {
        this.array = array;
        this.index = index;
    }

    public void onClick(Widget sender) {
    	boolean checked = array[index].isChecked();
    	Filiation(array,index,checked);
    }
	}
	//按键组
	private Button allButton = new Button("全选", new ButtonListenerAdapter() {
    public void onClick(Button button, EventObject e) {
       	AllChecked();
      }
  	});
	private Button noneButton = new Button("全不选", new ButtonListenerAdapter() {
    public void onClick(Button button, EventObject e) {
      NoneChecked();
    }
  });
	private Button saveButton = new Button("保存", new ButtonListenerAdapter() {   
    public void onClick(Button button, EventObject e) {
  		SetRange();
      hide();
    }
  });
	private int length;
	private CheckBox[] cBoxes;
	private int treeNum;
	private CheckBox[][] cTrees;
	private ViewIterator iter = ViewIterator.get();

	//组织对话框内容
	private VerticalPanel buildViewDialog() {		
		VerticalPanel views = new VerticalPanel();
		cBoxes = new CheckBox[20];
		cTrees = new CheckBox[5][];
		for( iter.head(),length=0,treeNum=0; iter.hasNext(); ){
		  ViewItem item = (ViewItem)iter.next();
		  String name = item.getName();
		  if(item.getType() != ViewType.TREE){
			  CheckBox cb = new CheckBox(name);
	      cBoxes[length++] = cb;
	      views.add(cb);
		  }
		  else{
		  	HTML parentLabel = new HTML("<B>"+name+"</B>");
		  	views.add(parentLabel);
		  	TreeViewItem tItem = (TreeViewItem)item;
		  	int tiers = tItem.getTier();
		  	CheckBox[] cTiers = new CheckBox[tiers];
		  	cTrees[treeNum++] = cTiers;
		  	name = tItem.getLabel();
		  	for (int i = 0; i < tiers; i++) {
		  		String label;
		  		switch(i){
			  		case 0:
			  			label = name;
			  			break;
			  		case 1:
			  			label = "二级" + name;
			  			break;
			  		case 2:
			  			label = "三级" + name;
			  			break;
			  		default:
			  			label = name;
		  		}
		  		CheckBox cb = new CheckBox(label);
		  		cTiers[i] = cb;
		      cTiers[i].addClickListener(new FiliationListener(cTiers,i));
		      views.add(cb);
		    }
		  }
		}
		return views;
	}
  //组织按键
	private HorizontalPanel buildActionPanel() {
		HorizontalPanel actions = new HorizontalPanel();
		actions.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		actions.add(allButton);
		actions.add(noneButton);
		actions.add(saveButton);    
    return actions;
	}
}
