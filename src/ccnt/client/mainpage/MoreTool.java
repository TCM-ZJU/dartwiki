package ccnt.client.mainpage;

import com.google.gwt.user.client.Window;
import com.gwtext.client.core.Function;
import com.gwtext.client.widgets.Tool;

public class MoreTool extends Tool {

	public MoreTool(final String panelID) {
	  super(Tool.RIGHT, new Function(){
			public void execute() {
	      Window.alert(panelID);
      }	  	
	  }, "更多");
  }
}
