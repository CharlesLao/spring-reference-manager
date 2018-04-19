$(document).ready(function () {

    $('#search').click(function(){
        var username = $('#username').val();
        $('#resultTitle').hide();
        $('#resultForm').hide();
        $('#resultTable').hide();
        $('#resultPanel').hide();
        $.ajax({
            type: "POST",
            data: {username:username},
            url: "/friend/friendSearchRequest",
            traditional: true,
            dataType: "json",
            success: function (data) {
                $('#resultTitle').show();
                $('#resultForm').show();
                if(data.userVoList.length!=0){
                    var str = "";
                    var userVoList = data.userVoList;

                    for(var i =0; i<userVoList.length; i++){
                        str+="<tr>\n" +
                            "<td>"+userVoList[i].username+"</td>\n" +
                            "<td><div class=\"am-btn-toolbar\">\n" +
                            "<div class=\"am-btn-group am-btn-group-xs\">\n" +
                            "<a class=\"am-btn am-btn-default am-btn-xs am-text-success am-round\" href='/friend/saveInvitation?toUser="+userVoList[i].id+"'><span class=\"am-icon-plus\" title=\"添加好友\"></span> </a>\n" +
                            "</div>\n" +
                            "</div></td>\n" +
                            "</tr>";
                    }
                    $('#resultTable').show();
                    $('#resultTableContent').html(str);
                }else{
                    $('#resultPanel').show();
                    $('#resultPanel').html("没有符合的结果");
                }
            }
        });
    });
});