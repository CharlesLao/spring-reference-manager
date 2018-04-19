var expression = "";
$(document).ready(function (){
    init();
    var referenceIdList = $('#referenceIdList').val();

    $('#showAddStyle').click(function(){
        var newStyleForm = document.getElementById("newStyleForm");
        newStyleForm.style.display="";
        var saveStyle = document.getElementById("saveStyle");
        saveStyle.style.display="";
    });

    $('#output').click(function(){
        var styleId = $('#style').val();
        $.ajax({
            type:"POST",
            url: "output",
            traditional:true,
            data: {referenceIdList:referenceIdList ,style: styleId},
            dataType:"json",
            success: function(data){
                $('#dcwx').val(data.result);
            }
        });
    });


    //给各个字段添加
    $("#bt").click(function(){
        insertImg("WZBT");
    })

    $("#zz").click(function(){
        insertImg("ZZ");
    })

    $("#nf").click(function(){
        insertImg("NF");
    })

    $("#qsy").click(function(){
        insertImg("QSY");
    })

    $("#mwy").click(function(){
        insertImg("MWY");
    })

    $("#hym").click(function(){
        insertImg("HYM");
    })

    $("#qh").click(function(){
        insertImg("QH");
    })

    $("#jh").click(function(){
        insertImg("JH");
    })

    $("#hydd").click(function(){
        insertImg("HYDD");
    })

    $("#saveStyle").click(function(){
        var name = $('#newStyleName').val();
        var isNameExist = "";
        $.ajax({
            type:"POST",
            url: "isNameExist",
            traditional:true,
            data: {name: name},
            dataType:"json",
            success: function(data){
                isNameExist = data.result;
                if(isNameExist=="名称已经存在"){
                    alert("该风格名称已经存在");
                }else{
                    var expression=document.getElementById("expression").innerHTML;
                    var andLastAuthor = "";
                    if($('#andLastAuthor').is(':checked')) {
                        andLastAuthor = "true";
                    }
                    var etalAuthor = "";
                    if($('#etalAuthor').is(':checked')) {
                        etalAuthor = "true";
                    }
                    var upperFirstname = "";
                    if($('#upperFirstname').is(':checked')) {
                        upperFirstname = "true";
                    }
                    var upperMiddlename = "";
                    if($('#upperMiddlename').is(':checked')) {
                        upperMiddlename = "true";
                    }
                    if(expression.indexOf('<img src="/img/')==-1) {
                        alert('请输入正确的格式');
                    }else if(name==""){
                        alert('请输入名称');
                    }else{
                        $.ajax({
                            type:"POST",
                            url: "saveStyle",
                            traditional:true,
                            data: {expression: expression,name: name,andLastAuthor: andLastAuthor,etalAuthor: etalAuthor,upperFirstname: upperFirstname,upperMiddlename: upperMiddlename},
                            dataType:"json",
                            success: function(data){
                                window.location.reload();
                            }
                        });
                    }
                }
            }
        });
    });
});

function insertImg(name){
    var input = document.getElementById("expression");
    var img = document.createElement("IMG");
    var srcImgSrc = "/img/"+name+".png";
    img.src =srcImgSrc;
    input.appendChild(img);
    if (window.getSelection) {//ie11 10 9 ff safari
        input.focus(); //解决ff不获取焦点无法定位问题
        var range = window.getSelection();//创建range
        range.selectAllChildren(input);//range 选择obj下所有子内容
        range.collapseToEnd();//光标移至最后
    }
}



