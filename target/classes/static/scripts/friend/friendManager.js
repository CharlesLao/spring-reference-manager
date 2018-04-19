$(document).ready(function () {
    init();
    $('#friendSearch').click(function(){
        window.location.href="/friend/friendSearch";
    });
    //控制好友申请表格
    var invitationVoListSize = $('#invitationListSize').val();
    $('#checkAllInvitation').change(function (){
        if($('#checkAllInvitation').is(':checked')) {
            for (var i = 1; i <= invitationVoListSize; i++) {
                $('#checkInvitation' + i).prop("checked","checked");
            }
        }else{
            for (var i = 1; i <= invitationVoListSize; i++) {
                $('#checkInvitation' + i).removeAttr("checked");
            }
        }
    });

    $('#deleteAllInvitation').click(function(){
        var deleteList = [];
        for(var i=0;i<=invitationVoListSize;i++) {
            if($('#checkInvitation'+i).is(':checked')) {
                deleteList.push($('#checkInvitation'+i).val());
            }
        }
        if(deleteList.length>0){
            if(confirm("确定要删除这"+deleteList.length+"条申请记录吗？")){
                window.location.href="/friend/deleteAllInvitation?invitationList="+deleteList;
            }
        }
    });

    //如果没有申请记录，将表格隐藏
    if($("#invitationListSize").length == 0) {
        $('#invitationTable').hide();
        $('#deleteAllInvitation').hide();
        $('#invitationPanel').html("暂时没有任何申请");
    }



    //控制好友列表
    var friendVoListSize = $('#friendListSize').val();
    $('#checkAllFriend').change(function (){
        if($('#checkAllFriend').is(':checked')) {
            for (var i = 1; i <= friendVoListSize; i++) {
                $('#checkFriend' + i).prop("checked","checked");
            }
        }else{
            for (var i = 1; i <= friendVoListSize; i++) {
                $('#checkFriend' + i).removeAttr("checked");
            }
        }
    });

    $('#deleteAllFriend').click(function(){
        var deleteList = [];
        for(var i=0;i<=friendVoListSize;i++) {
            if($('#checkFriend'+i).is(':checked')) {
                deleteList.push($('#checkFriend'+i).val());
            }
        }
        if(deleteList.length>0){
            if(confirm("确定要删除这"+deleteList.length+"个朋友吗？")){
                window.location.href="/friend/deleteAllFriend?friendList="+deleteList;
            }
        }
    });

    //如果没有好友，将表格隐藏
    if($("#friendListSize").length == 0) {
        $('#friendTable').hide();
        $('#deleteAllFriend').hide();
        $('#friendPanel').html("暂时没有任何好友");
    }

    //控制消息列表
    var massageVoListSize = $('#massageListSize').val();
    $('#checkAllMassage').change(function (){
        if($('#checkAllMassage').is(':checked')) {
            for (var i = 1; i <= massageVoListSize; i++) {
                $('#checkMassage' + i).prop("checked","checked");
            }
        }else{
            for (var i = 1; i <= massageVoListSize; i++) {
                $('#checkMassage' + i).removeAttr("checked");
            }
        }
    });

    $('#deleteAllMassage').click(function(){
        var deleteList = [];
        for(var i=0;i<=massageVoListSize;i++) {
            if($('#checkMassage'+i).is(':checked')) {
                deleteList.push($('#checkMassage'+i).val());
            }
        }
        if(deleteList.length>0){
            if(confirm("确定要删除这"+deleteList.length+"个消息吗？")){
                window.location.href="/massage/deleteAllMassage?massageList="+deleteList;
            }
        }
    });

    //如果没有消息，将表格隐藏
    if($("#massageListSize").length == 0) {
        $('#massageTable').hide();
        $('#deleteAllMassage').hide();
        $('#massagePanel').html("暂时没有任何消息");
    }

    var initTabId = $('#tabId').val();
    //tab切换逻辑
    for (var j = 1; j <= 4; j++) {
        $('#li'+j).removeClass("am-active");
        $('#tab'+j).removeClass("am-active");
        $('#tab'+j).removeClass("am-in");
    }
    $('#li'+initTabId).addClass("am-active");
    $('#tab'+initTabId).addClass("am-active");
    $('#tab'+initTabId).addClass("am-in");

    $('#li1').click(function() {
        window.location.href="/friend/manager?tabId=1";
    });
    $('#li2').click(function() {
        window.location.href="/friend/manager?tabId=2";
    });
    $('#li3').click(function() {
        window.location.href="/friend/manager?tabId=3";
    });
    $('#li4').click(function() {
        window.location.href="/friend/manager?tabId=4";
    });

});