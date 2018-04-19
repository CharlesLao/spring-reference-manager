var Login = function() {

    var handleLogin = function() {

        $('#login-form').validate({
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true
                },
                remember: {
                    required: false
                }
            },

            messages: {
                username: {
                    required: "请输入用户名"
                },
                password: {
                    required: "请输入密码"
                }
            },

            errorPlacement: function(error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },

            submitHandler: function() {
                var username = $('#username').val();
                var password = $('#password').val();
                window.location="/user/loginPost?username="+username+"&password="+password;
                 // form validation success, call ajax form submit
                if($("#remember-me").attr("checked",true)) {
                    setCookies();
                }else {
                    removeCookies();
                }
            }
        });
    }

    var setCookies=function () {
        $.cookie('username',$("input[name='username']").val(),{expires:7});
        $.cookie('password',$("input[name='password']").val(),{expires:7});
    }

    var removeCookies=function () {
        $.removeCookie('username');
        $.removeCookie('password');
    }

    var getCookies=function(){
        var username=$.cookie('username');
        if(username){
            var password=$.cookie('password');
            $("input[name='username']").val(username);
            $("input[name='password']").val(password);
            $("input[name='remember-me']").attr("checked","checked");
        }
    }

    return {
        init: function() {
            handleLogin();
            getCookies();
        }

    };

}();

jQuery(document).ready(function() {
    Login.init();
});