package ccnt.client.view;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Content extends HorizontalPanel{
	public Content(Widget w, String db){
		this.add(new HTML("<img src='image/arrow.gif' />"));
		this.add(w);
		this.add(new HTML("<span style='font:italic 10pt; color: #9C0000'>&nbsp("+db+")</span>"));
	}
	
	public Content(Widget w1,Widget w2, String db){
		this.add(new HTML("<img src='image/arrow.gif' />"));
		this.add(w1);
		this.add(w2);
		this.add(new HTML("<span style='font:italic 10pt; color: #9C0000'>&nbsp("+db+")</span>"));
	}
}