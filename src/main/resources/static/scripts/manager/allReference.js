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

$('#addResearch').click(function(){
    var referenceIdList = [];
    var referenceVoListSize = $('#referenceVoListSize').val();

    for(var i=0;i<=referenceVoListSize;i++) {
        if($('#check'+i).is(':checked')) {
            referenceIdList.push($('#check'+i).val());
        }
    }
    var researchName = prompt("请输入研究的名字")
    if(researchName!=null){
        window.location.href="/manager/addResearch?researchName="+researchName+"&referenceIdList="+referenceIdList;
    }else{
        alert("研究名不能为空");
    }
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