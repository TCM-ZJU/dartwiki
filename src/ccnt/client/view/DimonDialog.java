package ccnt.client.view;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
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
public class DimonDialog extends Window{

	static DimonDialog dimonDialog = null;
	/**
	 * 单例模式
	 */
	public static DimonDialog getDialog(){
		if(dimonDialog == null){
			dimonDialog = new DimonDialog();
		}
		return dimonDialog;
	}
	
	private DimonDialog() {
		setTitle("二维可视化工具","settings-icon");
		add(buildViewDialog(), new RowLayoutData("90%"));
		add(buildActionPanel(),new RowLayoutData(40));
		setSize(350, 350);
	}


	//按键组	
	private Button saveButton = new Button("导出", new ButtonListenerAdapter() {   
    public void onClick(Button button, EventObject e) {
    }
  });
	private Button sureButton = new Button("确定", new ButtonListenerAdapter() {
    public void onClick(Button button, EventObject e) {
      hide();
    }
  });
	
	private CheckBox[][] cBoxes;

	//组织对话框内容
	private Grid buildViewDialog() {
		Grid g = new Grid(5,5);
		g.setBorderWidth(1);
		
		g.setWidget(0, 0, new HTML("<img src='image/zhongyao-jibing.png'/>"));
		g.setText(0, 1, "大黄");
		g.setText(0, 2, "丹皮");
		g.setText(0, 3, "黄岑");
		g.setWidget(0, 4, new Button("添加"));
		g.setText(1, 0, "便秘");
		g.setText(2, 0, "咽喉肿痛");
		g.setText(3, 0, "瘀血");
		g.setWidget(4, 0, new Button("添加"));
		
		cBoxes = new CheckBox[3][3];
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++)
				cBoxes[i][j] = new CheckBox();
		}
		
		g.setWidget(1, 1, cBoxes[0][0]);
		g.setWidget(2, 1, cBoxes[1][0]);
		g.setWidget(3, 1, cBoxes[2][0]);
		

		g.setWidget(1, 2, cBoxes[0][1]);
		g.setWidget(2, 2, cBoxes[1][1]);
		g.setWidget(3, 2, cBoxes[2][1]);		

		g.setWidget(1, 3, cBoxes[0][2]);
		g.setWidget(2, 3, cBoxes[1][2]);
		g.setWidget(3, 3, cBoxes[2][2]);
		
		cBoxes[0][0].setChecked(true);
		cBoxes[1][0].setChecked(true);
		cBoxes[2][0].setChecked(true);
		cBoxes[1][1].setChecked(true);
		cBoxes[2][1].setChecked(true);
		cBoxes[2][2].setChecked(true);	
		
		return g;
	}
  //组织按键
	private HorizontalPanel buildActionPanel() {
		HorizontalPanel actions = new HorizontalPanel();
		actions.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		actions.add(saveButton); 
		actions.add(sureButton);   
    return actions;
	}
}
