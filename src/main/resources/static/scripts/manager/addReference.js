$(document).ready(function (){
    init();

    // $("#dropBox, #fileUpload").html5Uploader({
    //     name: "foo",
    //     postUrl: "/manager/uploadFile"
    // });


    $(function(){
        $('#referenceForm').validate({
            rules:{
                name:{
                    required:true,  //必填。如果验证方法不需要参数，则配置为true
                },
                authors:{
                    required:true,
                },
                year:{
                    required:true,
                    number:true,
                    min:0
                },
                conference:{
                    required:true,
                },jh:{
                    required:true,
                    number:true,
                    min:0
                },qh:{
                    required:false,
                    number:true,
                    min:0
                },
                beginPage:{
                    required:true,
                    number:true,
                    min:0
                },
                endPage:{
                    required:true,
                    number:true,
                    min:'#beginPage'
                }
            }
        })
    })


});



