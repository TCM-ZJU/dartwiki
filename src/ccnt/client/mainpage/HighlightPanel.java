package ccnt.client.mainpage;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Tool;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.RowLayoutData;
import com.gwtext.client.widgets.layout.RowLayout;

public class HighlightPanel extends Panel {
	public static HighlightPanel getPanel(){
		if(panel == null)
			panel = new HighlightPanel();
		return panel;
	}
	static HighlightPanel panel = null;
	
	private HighlightPanel(){
		setTitle("精彩词条");
		setHeight(400);
		this.setTools(new Tool[]{new MoreTool("highlight")});
		setCls("left");
		setLayout(new RowLayout());
		Panel hotone = new Panel();
		hotone.setLayout(new ColumnLayout());
		hotone.setPaddings(10);
		hotone.add(new HTML("<div style='text-align:center;vertical-align:middle;width:220;height:175;background:#ececec'>" +
				"<br><img src='imgsrc/dahuang.jpg' width='200'/></div>"));
		Panel hottext = new Panel();
		hottext.setLayout(new RowLayout());
		hottext.setBorder(false);
		hottext.setPaddings(5);
		Hyperlink dh = new Hyperlink("大黄", "大黄");
		dh.setStyleName("hot_dt");
		hottext.add(dh);
		hottext.add(new HTML("<img src='image/arrow.gif' />【定义】为<a href=#>蓼科</a>植物<a href=#>掌叶大黄</a>，<a href=#>唐古特大黄</a>或<a href=#>药用大黄</a>的<a href=#>根茎</a>。具有<a href=#>攻下</a>、<a href=#>泻火</a>的功效<span style='font:italic 10pt; color: #9C0000'>&nbsp(中医临床术语集)</span>"));
		hottext.add(new HTML("<img src='image/arrow.gif' />【定义】<a href=#>蓼科</a>植物<a href=#>掌叶大黄</a>，<a href=#>唐古特大黄</a>或<a href=#>药用大黄</a>的干燥根及<a href=#>根茎</a>。<span style='font:italic 10pt; color: #9C0000'>&nbsp(中医药学名词)</span>"));
		hottext.add(new HTML("<img src='image/arrow.gif' />【异名】肤如、黄良、川军、锦纹、生军、火参、将军、..."));
		hottext.add(new HTML("<img src='image/arrow.gif' />【属种】蓼科植物掌叶大黄、唐古特大黄或药用大黄的干燥根及根茎。"));
		hotone.add(hottext);
		add(hotone,new RowLayoutData("60%"));
		add(new HTML("<p style='background:#ececec;margin:10px 10px 0 10px;*margin-top:13px;padding:6px 5px 5px 10px;line-height:17px'>" +
				"<b>其他精彩词条</b>：<a href=#>山大黄</a>、 <a href=#>水大黄</a>、 <a href=#>小大黄</a>、 <a href=#>腹泻</a></p>"),new RowLayoutData("50"));
		add(new HTML("<div style='text-align:center'><div class=\"slide_mask\" id=\"slidePic\"><div class=\"slide_holder\"><ul id=\"holder1\">" +
				"&nbsp&nbsp&nbsp&nbsp<li><div><img src='imgsrc/shuidahuang.jpg' width='78'></div><h4><a href=#>水大黄</a></h4></li>" +
				"&nbsp&nbsp&nbsp&nbsp<li><div><img src='imgsrc/zhangyedahuang.jpg' width='78'></div><h4><a href=#>掌叶大黄</a></h4></li>" +
				"&nbsp&nbsp&nbsp&nbsp<li><div><img src='imgsrc/shandahuang.jpg' width='78'></div><h4><a href=#>山大黄</a></h4></li>" +
				"&nbsp&nbsp&nbsp&nbsp<li><div><img src='imgsrc/shuiguo.jpg' width='78'></div><h4><a href=#>水果</a></h4></li>" +
				"&nbsp&nbsp&nbsp&nbsp<li><div><img src='imgsrc/shiliao.jpg' width='78'></div><h4><a href=#>食疗</a></h4></li>" +
				"&nbsp&nbsp&nbsp&nbsp<li><div><img src='imgsrc/yinyang.jpg' width='78'></div><h4><a href=#>阴阳</a></h4></li>" +
				"&nbsp&nbsp&nbsp&nbsp<li><div><img src='imgsrc/xinnaoxueguan.jpg' width='78'></div><h4><a href=#>心脑血管</a></h4></li>" +
				"&nbsp&nbsp&nbsp&nbsp<li><div><img src='imgsrc/aizheng.jpg' width='78'></div><h4><a href=#>癌症</a></h4></li>" +
				"</ul></div></div></div>"));
	}
}
