$(document).ready(function () {
    init();
    var reply = $('#isReply').val();
    if(reply=="true"){
        $('#subject').val("Re:"+$('#origSubject').val());
    }
    var friendIdList = $('#friendIdList').val();
    var referenceIdList = $('#referenceIdList').val();
    friendIdList = friendIdList.slice(1,-1);
    referenceIdList = referenceIdList.slice(1,-1);
    changeToUserContent(friendIdList);
    changeShareReferenceContent(referenceIdList);

});

$('#sendMassage').click(function(){
    var friendIdList = [];
    var referenceIdList = [];
    var referenceList = $('#referenceListPanel').children();
    var friendList = $('#friendListPanel').children();
    for(var i=0;i<referenceList.length-1;i++){
        referenceIdList.push(referenceList[i].id);
    }
    for(var j=0;j<friendList.length-1;j++){
        friendIdList.push(friendList[j].id);
    }
    if(friendIdList==""){
        alert("请选择接收人");
    }else{
        var subject = $('#subject').val();
        var content = $('#content').val();
        window.location.href="/massage/saveMassage?subject="+subject+"&toUserId="+friendIdList+"&referenceId="+referenceIdList+"&content="+content;
    }
});

function removeThis(span){
    var id = span.parentNode.id;
    $('#'+id).remove();
}

function selectFriend(){
    var friendList = $('#friendListPanel').children();
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

function changeToUser(){
    var checkedNodes = $('#friendTree').jstree("get_checked");
    if(checkedNodes.length==0){
        swal("错误!", "至少要选择一个用户!", "error");
    }else{
        changeToUserContent(checkedNodes);
    }
}

function changeShareReference(){
    var checkedNodes = $('#referenceTree').jstree("get_checked");
    changeShareReferenceContent(checkedNodes);
}

function changeToUserContent(friendIdList){
    $.ajax({
        type:"POST",
        url: "/friend/getSelectFriendList",
        traditional:true,
        data: {friendIdList:friendIdList},
        dataType:"json",
        success: function(data){
            var selectFriendList = data.selectFriendList;
            $('#friendSelectPanel').hide();
            var str = "";

            for(var i=0;i<selectFriendList.length;i++){
                str+="<div class=\"am-btn am-btn-secondary am-radius\" style='cursor: default' value=\""+selectFriendList[i].id+"\" id='friend"+selectFriendList[i].id+"'>"+selectFriendList[i].username+"<span class=\"am-icon-remove\" onclick='removeThis(this)' style='cursor: pointer'/></div>";
            }
            str+="<div class=\"am-btn am-btn-success am-radius\" onclick='selectFriend()'>添加接收人</div>";
            $('#friendListPanel').html(str);
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