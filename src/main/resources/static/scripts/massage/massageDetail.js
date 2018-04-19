$(document).ready(function () {
    init();
});

function selectResearch(referenceId){
    $.ajax({
            type:"POST",
            url:"/manager/getAllResearch",
            traditional:true,
            dataType:"json",
            success: function(data){
                var researchList = data.AllResearch;
                var str = "";
                for(var i=0;i<researchList.length;i++){
                    str+="<option value='"+researchList[i].id+"' id='"+researchList[i].id+"'>"+researchList[i].name+"</option>"
                }
                swal({
                    title: "<small>请选择想要加入到的研究</small>!",
                    text: "<form class=\"am-form\">"+
                    "<select class=\"am-input-sm\" id=\"value\" name=\"value\" th:value=\"${referenceVo.lx}\">\n" +
                    str+
                    "</select></form>",
                    html: true,
                    closeOnConfirm: false,
                    closeOnCancel: false,
                },function(){
                    var researchId = $('#value').val();
                    var referenceIdList = [];
                    referenceIdList.push(referenceId);
                    saveReferenceToResearch(researchId,referenceIdList);
                    swal("成功！", "已经将文献加入到研究！","success");
                })
            }
        })
}

function saveReferenceToResearch(researchId,referenceIdList) {
    $.ajax({
        type:"POST",
        url:"/manager/saveReferenceToResearch",
        traditional:true,
        dataType:"json",
        data:{researchId:researchId,referenceIdList:referenceIdList},
        success: function(data){
        }
    })
}