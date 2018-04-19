$(document).ready(function () {
    init();
    changeBlockListContent([]);
    changeShareReferenceContent([]);
    var initTabId = $('#tabId').val();
    //tab切换逻辑
    for (var j = 1; j <= 3; j++) {
        $('#li'+j).removeClass("am-active");
        $('#tab'+j).removeClass("am-active");
        $('#tab'+j).removeClass("am-in");
    }
    $('#li'+initTabId).addClass("am-active");
    $('#tab'+initTabId).addClass("am-active");
    $('#tab'+initTabId).addClass("am-in");



    $('#li1').click(function() {
        window.location.href="/moment/manager?tabId=1";
    });
    $('#li2').click(function() {
        window.location.href="/moment/manager?tabId=2";
    });
    $('#li3').click(function() {
        window.location.href="/moment/manager?tabId=3";
    });
})


function like(momentid,userid) {

   // alert("liujun");
    $.ajax({
        type: "POST",
        url: "/moment/like",
        traditional: true,
        data:{momentid:momentid,userid:userid},
        dataType:"json",
        success:function(data){
            //
            // alert(data);
            if(data==0)
            {
                swal({
                    title:"警告!",
                    text:"您已点赞，请勿重复点赞。",
                    type:"success",
                },function () {
                    window.location.href="/moment/manager?tabId=1";
                })

            }
            else
            {
                swal({
                    title:"成功!",
                    text:"点赞成功",
                    type:"success",
                },function () {
                    window.location.href="/moment/manager?tabId=1";
                })
            }

        }
    });

}

function comment(momentid,userid){
    var commenttype=0;
    var content=[];
    swal({
        title: "请输入！",
        text: "填写一些信息",
        type: "input",
        showCancelButton: true,
        closeOnConfirm: false,
        animation: "slide-from-top",
        inputPlaceholder: "请输入..."
    }, function(inputValue) {
        if (inputValue === false) {
            return false;
        }
        if (inputValue === "") {
            swal.showInputError("内容不能为空！");
            return false;
        }
        content=inputValue;
        $.ajax({
            type: "POST",
            url: "/moment/saveComment",
            traditional: true,
            data: {momentid: momentid,userid: userid,content: content,commenttype: commenttype},
            dataType:"json",
            success:function(){

            }
        });

        swal({
            title:"成功!",
            text:"评论成功",
            type:"success",
        },function () {
            window.location.href="/moment/manager?tabId=1";
        })
        //swal("Nice!", "你输入的是：" + inputValue, "success")
    })
}


function replycomment(momentid,fromuserid,touserid)
{
    var commenttype=1;
    var content=[];
    swal({
        title: "请输入！",
        text: "填写回复的信息",
        type: "input",
        showCancelButton: true,
        closeOnConfirm: false,
        animation: "slide-from-top",
        inputPlaceholder: "请输入..."
    }, function(inputValue) {
        if (inputValue === false) {
            return false;
        }
        if (inputValue === "") {
            swal.showInputError("内容不能为空！");
            return false;
        }
        content=inputValue;
        $.ajax({
            type: "POST",
            url: "/moment/replyComment",
            traditional: true,
            data: {momentid: momentid,fromuserid: fromuserid,touserid: touserid,content: content,commenttype: commenttype},
            dataType:"json",
            success:function(){

            }
        });

        swal({
            title:"成功!",
            text:"回复成功",
            type:"success",
        },function () {
            window.location.href="/moment/manager?tabId=1";
        })
    })
}


function deletecomment(commentid)
{
    swal({
        title: "提醒",
        text: "确定删除该评论么？",
        showCancelButton: true,
        closeOnConfirm: false,
        animation: "slide-from-top",
    }, function() {
        $.ajax({
            type:"POST",
            url:"/moment/deleteComment",
            data:{commentid: commentid},
            dataType:"json",
            success:function(){}
        });
        swal({
            title:"成功!",
            text:"删除成功",
            type:"success",
        },function () {
            window.location.href="/moment/manager?tabId=1";
        })
    });

}

function showReference(momentid) {
    var moment=momentid;
    var id='reference'+momentid;
    $.ajax({
        type:"POST",
        url: "/manager/getReferenceList",
        traditional:true,
        data: {momentid: momentid},
        dataType:"json",
        success: function(data){
            var ReferenceList = data.referencelist;
           // $('#referenceInfoPanel').hide();
            var str = "";
            for(var i=0;i<ReferenceList.length;i++){
                str+="<div class=\"you-comment am-radius\" style='float: left' value=\""+ReferenceList[i].id+"\" id='reference"+ReferenceList[i].id+"'>"+'id: '+ReferenceList[i].id+"</div>";
                str+="<div class=\"you-comment am-radius\" style='float: left' value=\""+ReferenceList[i].id+"\" id='reference"+ReferenceList[i].id+"'>"+'name: '+ReferenceList[i].name+"</div>";
                str+="<div class=\"you-comment am-radius\" style='float: left' value=\""+ReferenceList[i].id+"\" id='reference"+ReferenceList[i].id+"'>"+'authors: '+ReferenceList[i].authors+"</div>";
                str+="<div class=\"you-comment am-radius\" style='float: left' value=\""+ReferenceList[i].id+"\" id='reference"+ReferenceList[i].id+"'>"+'beginPage: '+ReferenceList[i].beginPage+"</div>";
                str+="<div class=\"you-comment am-radius\" style='float: left' value=\""+ReferenceList[i].id+"\" id='reference"+ReferenceList[i].id+"'>"+'endPage: '+ReferenceList[i].endPage+"</div>";
                str+="<div class=\"you-comment am-radius\" style='float: left' value=\""+ReferenceList[i].id+"\" id='reference"+ReferenceList[i].id+"'>"+'conference: '+ReferenceList[i].conference+"</div>";
                str+="<div class=\"you-comment am-radius\" style='float: left' value=\""+ReferenceList[i].id+"\" id='reference"+ReferenceList[i].id+"'>"+'hydd: '+ReferenceList[i].hydd+"</div>";
                str+="<div class=\"you-comment am-radius\" style='float: left' value=\""+ReferenceList[i].id+"\" id='reference"+ReferenceList[i].id+"'>"+'jh: '+ReferenceList[i].jh+"</div>";
                str+="<div class=\"you-comment am-radius\" style='float: left' value=\""+ReferenceList[i].id+"\" id='reference"+ReferenceList[i].id+"'>"+'qh: '+ReferenceList[i].qh+"</div>";
                str+="<div class=\"you-comment am-radius\" style='float: left' value=\""+ReferenceList[i].id+"\" id='reference"+ReferenceList[i].id+"'>"+'fulltext: '+ReferenceList[i].fulltext+"</div>";
                str+="<div class=\"you-comment am-radius\" style='float: left' value=\""+ReferenceList[i].id+"\" id='reference"+ReferenceList[i].id+"'>"+'researchid: '+ReferenceList[i].researchId+"</div>";
                str+="<div class=\"you-comment am-radius\" style='float: left' value=\""+ReferenceList[i].id+"\" id='reference"+ReferenceList[i].id+"'>"+'lx: '+ReferenceList[i].lx+"</div>";
                str+="<div class=\"you-comment am-radius\" style='float: left' value=\""+ReferenceList[i].id+"\" id='reference"+ReferenceList[i].id+"'>"+'year: '+ReferenceList[i].year+"</div>";
            }
            str+="<div class=\"am-btn am-btn-warning am-radius\" style='float: left'  onclick='hideReference("+moment+")'>隐藏文献列表</div>";
            $('#'+id).html(str);
        }
    });
}

function hideReference(moment) {
    var str="";
    var id='reference'+moment;
    str+="<div class=\" am-radius\" style='float: left'></div>";
    $('#'+id).html(str);
}


function selectBlockFriend(){
    var friendList = $('#blockListPanel').children();
    var selectedIdList = [];
    for(var j=0;j<friendList.length-1;j++){
        selectedIdList.push(friendList[j].id);
    }
    $.ajax({
        type:"POST",
        url: "/friend/getAllFriendList",
        data: {selectedIdList: selectedIdList},
        traditional:true,
        dataType:"json",
        success: function(data){
            $('#friendSelectPanel').show();
            $('#friendTree').jstree({
                'core' : {
                    'data' : data.firstNode
                },
                plugins: ["checkbox", "themes"]
            });
        }
    })
}

function selectReference(){
    var referenceList = $('#referenceListPanel').children();
    var selectedIdList = [];
    for(var j=0;j<referenceList.length-1;j++){
        selectedIdList.push(referenceList[j].id);
    }
    $.ajax({
        type:"POST",
        url: "/manager/getSelectReferenceList",
        data: {selectedIdList: selectedIdList},
        traditional:true,
        dataType:"json",
        success: function(data){
            $('#referenceSelectPanel').show();
            $('#referenceTree').jstree({
                'core' : {
                    'data' : data.firstNode
                },
                plugins: ["checkbox", "themes"]
            });
        }
    })
}

function changeBlockList(){
    var checkedNodes = $('#friendTree').jstree("get_checked");
    changeBlockListContent(checkedNodes);
}


function changeShareReference(){
    var checkedNodes = $('#referenceTree').jstree("get_checked");
    changeShareReferenceContent(checkedNodes);
}

function changeBlockListContent(blockIdList){
    $.ajax({
        type:"POST",
        url: "/friend/getSelectFriendList",
        traditional:true,
        data: {friendIdList:blockIdList},
        dataType:"json",
        success: function(data){
            var selectFriendList = data.selectFriendList;
            $('#friendSelectPanel').hide();
            var str = "";
            for(var i=0;i<selectFriendList.length;i++){
                str+="<div class=\"am-btn am-btn-secondary am-radius\" style='cursor: default' value=\""+selectFriendList[i].id+"\" id='friend"+selectFriendList[i].id+"'>"+selectFriendList[i].username+"<span class=\"am-icon-remove\" onclick='removeThis(this)' style='cursor: pointer'/></div>";
            }
            str+="<div class=\"am-btn am-btn-warning am-radius\" onclick='selectBlockFriend()'>添加屏蔽好友</div>";
            $('#blockListPanel').html(str);
        }
    });
}

function changeShareReferenceContent(referenceIdList){
    $.ajax({
        type:"POST",
        url: "/manager/getShareReferenceList",
        traditional:true,
        data: {referenceIdList:referenceIdList},
        dataType:"json",
        success: function(data){
            var str = "";
            var shareReferenceList = data.shareReferenceList;
            $('#referenceSelectPanel').hide();
            for(var i=0;i<shareReferenceList.length;i++){
                str+="<div class=\"am-btn am-btn-secondary am-radius\" style='cursor: default' value=\""+shareReferenceList[i].id+"\" id='reference"+shareReferenceList[i].id+"'>"+shareReferenceList[i].name+"<span class=\"am-icon-remove\" onclick='removeThis(this)' style='cursor: pointer'/></div>";
            }
            str+="<div class=\"am-btn am-btn-success am-radius\" onclick='selectReference()'>添加文献</div>\n";
            $('#referenceListPanel').html(str);
        }
    });
}



function removeThis(span){
    var id = span.parentNode.id;
    $('#'+id).remove();
}

$('#sendMoment').click(function(){
    var blockIdList = [];
    var referenceIdList = [];
    var referenceList = $('#referenceListPanel').children();
    var blockList = $('#blockListPanel').children();
    for(var i=0;i<referenceList.length-1;i++){
        referenceIdList.push(referenceList[i].id);
    }
    for(var j=0;j<blockList.length-1;j++){
        blockIdList.push(blockList[j].id);
    }
    var content = $('#content').val();
    $.ajax({
        type:"POST",
        url: "/moment/saveMoment",
        traditional:true,
        data: {content:content,blockIdList:blockIdList,referenceIdList:referenceIdList},
        dataType:"json",
        success: function(data){
            swal({
                title:"成功!",
                text:"已经发送朋友圈",
                type:"success",
            },function () {
                window.location.href="/moment/manager?tabId=1";
            })
        }
    })
})