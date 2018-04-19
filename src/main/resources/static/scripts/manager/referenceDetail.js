$(document).ready(function () {
    init();
    var attachmentListSize = $('#attachmentVoListSize').val();
    if(attachmentListSize>0) {
        $('#attachmentForm').show();
    }
});

function doChange(file){
    var upload_file = $.trim($("#uploadFile").val());
    var str = upload_file.split(".");
    var type = str[str.length-1];
    if(type=="pdf"){
        $('#filePath').val(upload_file);
        $('#submit').show();
    }else{
        alert("请选择pdf格式的文件");
        $('#filePath').val(upload_file);
        $('#submit').hide;
    }
}