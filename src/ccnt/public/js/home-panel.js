Ext.onReady(function() {

var catelogPanel = new Ext.Panel({
    title: '按开放分类浏览',
    applyTo: 'catelog',
    cls:'selectable',
    height:200,
    html: '<ul class="kind">' +
	'<li><h2><a href="/class/1.html">疾病</a></h2><h3><a href="/class/1408.html#34531">癌症</a><a href="/class/1408.html#34608">心脑血管</a><a href="/class/1408.html">...</a></h3></li>' +
	'<li><h2><a href="/class/2.html">症候</a></h2><h3><a href="/class/17.html#34551">文学</a><a href="/class/17.html#34553">文物古迹</a><a href="/class/17.html">...</a></h3></li>' +
	'<li><h2><a href="/class/3.html">治法</a></h2><h3><a href="/class/671.html#34572">针灸</a><a href="/class/671.html#34573">按摩</a><a href=\"/class/671.html#34575\">西药</a><a href="/class/671.html">...</a></h3></li>' +
	'<li><h2><a href="/class/4.html">方剂</a></h2><h3><a href="/class/707.html#34538">世界历史</a><a href="/class/707.html#34537">中国近现代史</a><a href="/class/707.html">...</a></h3></li>' +
	'<li><h2><a href="/class/5.html">中药</a></h2><h3><a href="/class/670.html#34556">建筑</a><a href="/class/670.html#34596">工艺品</a><a href="/class/670.html">...</a></h3></li>' +
	'<li><h2><a href="/class/6.html">化学</a></h2><h3><a href="/class/669.html#34581">饮食</a><a href="/class/669.html#34583">休闲</a><a href="/class/669.html#34585">交通</a><a href="/class/669.html">...</a></h3></li>' +
	'<li><h2><a href="/class/5.html">基因</a></h2><h3><a href="/class/670.html#34556">建筑</a><a href="/class/670.html#34596">工艺品</a><a href="/class/670.html">...</a></h3></li>' +
	'<li><h2><a href="/class/6.html">蛋白质</a></h2><h3><a href="/class/669.html#34581">饮食</a><a href="/class/669.html#34583">休闲</a><a href="/class/669.html#34585">交通</a><a href="/class/669.html">...</a></h3></li>' +
	'</ul>'
});
var taskPanel = new Ext.Panel({
    title: '科研任务',
    applyTo: 'task',
    cls:"task",
    height:220,
    html: '<img height=80 width=195 align="center" title="点击进入本期任务" src="imgsrc/zhongyao.jpg"><br>' +
    '<b>本期任务: 定义中药类别</b><br>中药种类成千上万，组合变幻，是中医药研究的基础，让我们一起找到中药的类别！<br>' +
	'<b><a href="/task/511" target="_blank">进入本期任务&gt;&gt;</a></b>'        
});
var postPanel = new Ext.Panel({
    title: '公告区',
    applyTo: 'post',
    cls:"notice",
    height:180,
    tools:[{id:"right",qtip:'更多',handler:function(){Ext.MessageBox.alert("工具栏按钮","工具栏上的更多按钮时间被激发了")}}],
    html: '<li><a href="http://www.baidu.com/search/baike_help.html" target="_blank" class="grn">如何使用百科</a></li>' +
	'<li><a href="http://hi.baidu.com/c.html" target="_blank">树形位置加入子类型扩展功能</a><sup>new</sup></li>' +
	'<li><a href="http://hi.baidu.com/1.html" target="_blank">基本信息加入图片域</a><sup>new</sup></li>' +
	'<li><a href="http://hi.baidu.com/2.html" target="_blank">"多术语融合平台"正式更名为"中医药知识百科"</a></li>'
});
var toolPanel = new Ext.Panel({
        title: '工具箱',
        applyTo: 'tool',
        height:150,
        html: "<ul class=\"kind\">" +
		'<li><h3><img src="image/logo-table.png" width=23/>&nbsp;<a href="/class/1408.html#34608">二维可视化工具</a></h3></li>' +
		'<li><h3><img src="image/logo-graph.png" width=23/>&nbsp;<a href="http://www.dart.zju.edu.cn/mashup/Mashup2.html" target="_blank">本体网络图</a></h3></li>' +
		'<li><h3><img src="image/logo-download.png" width=23/>&nbsp;<a href="/class/1408.html#34608">本体下载</a></h3></li>' +
		'</ul>'
    });
var toplistPanel = new Ext.Panel({
        title: '排行榜',
        applyTo: 'toplist',
        height:200,
        tools:[{id:"right",qtip:'更多',handler:function(){Ext.MessageBox.alert("工具栏按钮","工具栏上的更多按钮时间被激发了")}}],
        html: "<table width=\"160\" valign=botton border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" id=\"phbCon1\" class=\"phb_con\">" +
		"<tr><td height\"'16\" width=\"5\">1</td><td width=\"20\">&nbsp;<img src=\"image/user_female.gif\" style=\"vertical-align:middle\" border=\"0\"></td><td><a href=\"http://passport.baidu.com/?business&aid=7&default_tab=2&un=%B3%AF%D1%F4%C9%BD%C8%CB#3\" target=\"_blank\">宓宓</a></td><td width=\"40\">1602</td></tr>" +
		"<tr><td width=\"5\">2</td><td width=\"20\">&nbsp;<img src=\"image/user.gif\" border=\"0\"></td><td><a href=\"http://passport.baidu.com/?business&aid=7&default_tab=2&un=%C4%CF%CD%F9#3\" target=\"_blank\">宾1984</a></td><td width=\"40\">1589</td></tr>" +
		"<tr><td width=\"5\">3</td><td width=\"20\">&nbsp;<a href=\"http://www.baidu.com/search/baike/kedoutuan/index.html\" target=\"_blank\"><img src=\"http://img.baidu.com/img/baike/tadpole.gif\" title=\"百度百科蝌蚪团\" border=\"0\"></a></td><td><a href=\"http://passport.baidu.com/?business&aid=7&default_tab=2&un=%D1%EE%BF%A11981#3\" target=\"_blank\">花无缺</a></td><td width=\"40\">1455</td></tr>" +
		"<tr><td width=\"5\">4</td><td width=\"20\">&nbsp;<img src=\"image/user_female.gif\" border=\"0\"></td><td><a href=\"http://passport.baidu.com/?business&aid=7&default_tab=2&un=%C2%DE%B1%BE%C9%AD#3\" target=\"_blank\">zqz</a></td><td width=\"40\">1302</td></tr>" +
		"<tr><td width=\"5\">5</td><td width=\"20\">&nbsp;<img src=\"image/user.gif\" border=\"0\"></td><td><a href=\"http://passport.baidu.com/?business&aid=7&default_tab=2&un=zy19842006#3\" target=\"_blank\">贱贱</a></td><td width=\"40\">1068</td></tr><tr><td colspan=\"4\" class=\"ver\"><a href=\"/star/contribution/total.html?func=contotal\" target=\"_blank\">查看总贡献榜&gt;&gt;</a></td></tr></table>"
    });
var linkPanel = new Ext.Panel({
        title: '友情链接',
        applyTo: 'link',
        cls:'center',
        height:150,
        html: 	'<p><a href=\"/class/1.html\"><img height=60 width=160 src="imgsrc/logo_dartSearch.gif"></a></p>' +
				'<p><a href=\"/class/1.html\"><img height=60 width=160 src="imgsrc/logo_dartQuery.jpg"></a></p>'
    });
});
