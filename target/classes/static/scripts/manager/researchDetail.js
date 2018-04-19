$(document).ready(function (){
    init();
    var researchId = $('#researchId').val();
    var referenceVoListSize = $('#referenceVoListSize').val();
    $('#addReference').click(function(){
        window.location.href="/manager/addReference?researchId="+researchId;
    });
    // var perPage = 6;
    // for(var j=1;j<=perPage;j++){
    //     $('#p'+j).addClass('_current').show();
    // }
    // $("#page").paginate({
    //     count 		: referenceVoListSize/perPage,
    //     start 		: 1,
    //     display     : perPage,
    //     border					: true,
    //     border_color			: '#fff',
    //     text_color  			: '#fff',
    //     background_color    	: 'black',
    //     border_hover_color		: '#ccc',
    //     text_hover_color  		: '#000',
    //     background_hover_color	: '#fff',
    //     images					: false,
    //     mouse					: 'press',
    //     onChange     			: function(page){
    //         $('._current','#referenceTable').removeClass('_current').hide();
    //         for(var i = (page-1)*perPage+1;i<=page*perPage;i++){
    //             $('#p'+i).addClass('_current').show();
    //         }
    //     }
    // });
    $('#checkAll').change(function (){
        if($('#checkAll').is(':checked')) {
            for (var i = 1; i <= referenceVoListSize; i++) {
                $('#check' + i).prop("checked","checked");
            }
        }else{
            for (var i = 1; i <= referenceVoListSize; i++) {
                $('#check' + i).removeAttr("checked");
            }
        }
    });

    $('#deleteAllReference').click(function(){
        var deleteList = [];
        for(var i=0;i<=referenceVoListSize;i++) {
            if($('#check'+i).is(':checked')) {
                deleteList.push($('#check'+i).val());
            }
        }
        if(deleteList.length>0){
            if(confirm("确定要删除这"+deleteList.length+"条文献吗？")){
                window.location.href="/manager/deleteAllReference?referenceList="+deleteList;
            }
        }
    });

    $('#deleteResearch').click(function(){
        if(confirm("确定要删除这项研究吗？")){
            window.location.href="/manager/deleteResearch?researchId="+researchId;
        }
    });

    $('#exportReference').click(function(){
        var exportList = [];
        for(var i=0;i<=referenceVoListSize;i++) {
            if($('#check'+i).is(':checked')) {
                exportList.push($('#check'+i).val());
            }
        }
        if(exportList.length>0){
            window.location.href="/export/exportPage?referenceIdList="+exportList;
        }
    });
});

$('#shareReference').click(function(){
    var referenceIdList = [];
    var referenceVoListSize = $('#referenceVoListSize').val();

    for(var i=0;i<=referenceVoListSize;i++) {
        if($('#check'+i).is(':checked')) {
            referenceIdList.push($('#check'+i).val());
        }
    }
    if(referenceIdList.length!=0){
        window.location.href="/massage/createShareMassage?referenceIdList="+referenceIdList;
    }else{
        alert("请选择至少一条文献");
    }

});

