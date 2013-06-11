package ccnt.client;

import ccnt.client.view.ViewDialog;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ImageBundle;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Toolbar;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.ColumnLayoutData;

public class SearchPanel extends Panel {

	private SearchPanel() {
  }
	
  public interface Images extends ImageBundle {
    AbstractImagePrototype logo();
  }
  
	public static Panel getPanel(){
		/*------关键字框--------*/
		name.setVisibleLength(50);
		name.addKeyboardListener(new KeyboardListenerAdapter(){
		   public void onKeyPress(Widget sender, char keyCode, int modifiers) {
		      // Check for Enter key
		      if ((keyCode == 13) && (modifiers == 0)) {
		      	enterConcept();
		      }
		   }      
		});
		
		/*------进入按钮--------*/
		Button getinto = new Button("进入词条",new ButtonListenerAdapter() {
		  public void onClick(Button button, EventObject e) {
		  	enterConcept();
		  }
	  });
		getinto.setIcon("image/idea.png");
		getinto.focus();
		
	  /*------搜索按钮--------*/
		Button search = new Button("搜索词条",new ButtonListenerAdapter() {
		  public void onClick(Button button, EventObject e) {
		  	searchWord();
		  }
	  });
		search.setIcon("image/page_find.gif");
	  
		HorizontalPanel hp = new HorizontalPanel();
		hp.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		hp.setSize("550px","100px");
		hp.add(name);
	  hp.add(getinto);
	  hp.add(search);
	  hp.add(fuzzy);
		
		Toolbar toolbar = new Toolbar();
		ToolbarButton range = new ToolbarButton("设定展示区域");
		ToolbarButton help 	= new ToolbarButton("浏览在线帮助");
	  toolbar.addButton(range);
	  toolbar.addSeparator();
	  toolbar.addButton(help);
	  toolbar.addFill();
	  ToolbarButton homepageButton =new ToolbarButton("首页");
	  homepageButton.addListener(new ButtonListenerAdapter() {
		  public void onClick(Button button, EventObject e) {
		   	;
		  }
	  });
	  toolbar.addButton(new ToolbarButton("首页"));
	  toolbar.addButton(new ToolbarButton("登录"));
		
	  searchPanel.setLayout(new ColumnLayout());
	  searchPanel.setBorder(false);
	  searchPanel.setHeight(125);
	  searchPanel.setWidth(984);

		searchPanel.add(new HTML("<br><a href='Wiki.html'><img src='image/logo.png' width=200/></a>"), new ColumnLayoutData(.5));
	  searchPanel.add(hp);
	  //searchPanel.add(new Panel(), new ColumnLayoutData(.5));
	  searchPanel.setTopToolbar(toolbar);
	  
	  range.addListener(new ButtonListenerAdapter() {
		  public void onClick(Button button, EventObject e) {
		   	ViewDialog dlg = ViewDialog.getDialog();
		    dlg.show();
		  }
	  });
	  
	  help.addListener(new ButtonListenerAdapter() {
		  public void onClick(Button button, EventObject e) {
		  	MessageBox.alert("帮助信息",helpMessage());
		  }
	  });
		return searchPanel;
	}

	public static void setWord(String word){
		name.setText(word);
	}
	
	private static void searchWord(){
		if(!validateAndSubmit())
			return;
		History.newItem("$"+name.getText()+"&"+fuzzy.isChecked());
	}
	
	private static void enterConcept(){
		if(!validateAndSubmit())
			return;
		History.newItem(name.getText());
	}
	
	
	private static boolean validateAndSubmit(){
		String text = name.getText();
		if(text == null || text.equals("")){
			MessageBox.alert("用户信息","请输入要搜索的内容再提交");
			return false;
		}
		return true;
	}
	
	final static TextBox name = new TextBox();
	final static CheckBox fuzzy = new CheckBox("模糊");
	//final static TextBox fuzzyRange = new TextBox();
  private static Panel searchPanel	= new Panel();
  
  static private String helpMessage(){
  	return "<p> 帮助信息，开发中 </p>";
  }
}