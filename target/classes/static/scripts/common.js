function init(){

    var str = "";
    $.ajax({
        type:"POST",
        url:"/user/sideMenuContent",
        traditional:true,
        dataType:"json",
        success: function(data){
            headerHtml(data.msgNumber,data.ydCommentNum);

            $('#hello').text("欢迎回来!"+data.username);
            $('#hello').val(data.username);
            str+="<h3 class=\"am-icon-flag\"><em></em> <a href=\"#\">研究管理</a></h3>";
            str+="<ul>";
            str+="<li><a href=\"/manager/allReference\">所有文献</a></li>";
            var researchList = data.researchVoList;
            for(var i=0;i<researchList.length;i++){
                str+="<li><a href=\"/manager/researchDetail?id="+researchList[i].id+"\">"+researchList[i].name+"</a></li>";
            }
            str+="</ul>";
            str+="<h3 class=\"am-icon-user\"><em></em> <a href=\"#\">好友列表</a></h3>";
            str+="<ul>";
            var friendList = data.friendList;
            if(friendList.length==0){
                str+="<li>暂无任何好友</li>";
            }
            for(var i=0;i<friendList.length;i++){
                str+="<li><a href=\"/friend/addMassage?friendId="+friendList[i].id+"\">"+friendList[i].username+"</a></li>";
            }
            str+="</ul>";
            $('#sideMenu').html(str);

            jQuery(".sideMenu").slide({
                titCell:"h3", //鼠标触发对象
                targetCell:"ul", //与titCell一一对应，第n个titCell控制第n个targetCell的显示隐藏
                effect:"slideDown", //targetCell下拉效果
                delayTime:300 , //效果时间
                triggerTime:150, //鼠标延迟触发时间（默认150）
                defaultPlay:true,//默认是否执行效果（默认true）
                returnDefault:false //鼠标从.sideMen移走后返回默认状态（默认false）
            });
        }
    });
}

function headerHtml(msgNumber,ydCommentNum){
    var msgSpan = "";
    var ydComSpan="";
    if(msgNumber!=0){
        msgSpan = "<span class=\"am-badge am-badge-danger am-round\">"+msgNumber+"</span>"
    }
    if(ydCommentNum!=0)
    {
        ydComSpan= "<span class=\"am-badge am-badge-danger am-round\">"+ydCommentNum+"</span>"
    }
    else
    {
        ydComSpan="";
    }

    $('#header').html("\n" +
        "    <div class=\"am-topbar-brand\"></div>\n" +
        "    <div class=\"am-collapse am-topbar-collapse\" id=\"topbar-collapse\">\n" +
        "        <ul class=\"am-nav am-nav-pills am-topbar-nav admin-header-list\">\n" +
        "            <li class=\"am-dropdown tognzhi\" data-am-dropdown=\"true\">\n" +
        "                <button class=\"am-btn am-btn-primary am-dropdown-toggle am-btn-xs am-radius am-icon-bell-o\" data-am-dropdown-toggle=\"true\" style='width: 94px;text-align: left' id='xxgl'> 消息管理"+msgSpan+"</button>\n" +
        "                <ul class=\"am-dropdown-content\">\n" +
        "                </ul>\n" +
        "            </li>\n" +
        "            <li class=\"kuanjie\"> <a href=\"/manager/allReference\">文献管理</a> <a href=\"/friend/manager?tabId=1\">好友管理</a> <a href=\"/moment/manager?tabId=1\">朋友圈"+ydComSpan+"</a> <a href=\"/user/userCenter\">个人中心</a>\n" +
        "\n" +
        "            <li class=\"am-hide-sm-only\" style=\"float: right;\"><a href=\"javascript:;\" id=\"logout\"><span class=\"admin-fullText\">退出登录</span></a></li>\n" +
        "        </ul>\n" +
        "    </div>");
    $('#logout').click(function(){
        window.location.href="/user/logout";
    });

    $('#xxgl').click(function(){
        window.location.href="/friend/manager?tabId=4";
    });
}




