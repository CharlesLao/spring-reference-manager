/**
 * Created by liujun on 2018/4/13.
 */
function output(){
    //alert("hello liujun");
    var password=document.getElementById("password");
    var newpassword=document.getElementById("newPassword");
    var newpasswordconfirm=document.getElementById("newPasswordConfirm");

    $.ajax({
        type:"POST",
        url:"/user/changePassword",
        traditional:true,
        data:{password: password.value,newpassword: newpassword.value,newpasswordconfirm:newpasswordconfirm.value},
        dataType:"json",
        success:function(data){
            //alert(data);
            //data=1;
            if(data==0)
            {alert("两次密码输入不同，请重新输入。");}

            if(data==2)
            {alert("原密码输入错误，请重新输入。");}
            if(data==3)
            {alert("密码不能为空！");}
            if(data==1)
            {
                swal({
                    title:"成功!",
                    type:"success",
                },function () {

                })
            }


        }
    });
}