<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.setAttribute("username", null);
    String contextPath = request.getContextPath();
    session.setAttribute("basePath", contextPath);
%>
<html>
<head>
    <title>Login</title>
    <meta charset="utf-8">
    <link href="css/style.css" rel='stylesheet' type='text/css' />
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<script>
    function $(id){
        return document.getElementById(id);
    }
    String.prototype.trim=function()
    {
        return this.replace(/(^\s*)|(\s*$)/g,"");
    }
    function checkUsername(obj){
        if(obj.value.trim()==""){
            obj.placeholder="用户名不能为空!";
            return false;
        }else{
            return true;
        }
    }
    function checkPassword(obj){
        if(obj.value==""){
            obj.placeholder="密码不能为空!";
            return false;
        }else{
            return true;
        }
    }
    function checkAll(){
        if(checkUsername($("user"))&checkPassword($("pass"))){
            return true;
        }else{
            alert("请确认您填写名称中不为空或所输入的全为空格！");
            return false;
        }
    }
</script>
<body>
<!-----start-main---->
<div class="main">
    <div class="login-form">
        <h1>Member Login</h1>
        <div class="head">
            <img src="images/用户图标.jpg" alt="">
        </div>
        <form action="shiro/login" method="post">
            <table>
                <input id="user" type="text" placeholder="请输入用户名" name="username" onblur="checkUsername(this)"/>
                <input id="pass" type="password" placeholder="请输入密码" name="password" onblur="checkPassword(this)"/>
                <div class="submit">
                    <input type="submit" value="登录" onclick="return checkAll()"/>
                </div>
                <p><a href="#">没有用户，注册一个?</a></p>
            </table>
        </form>
    </div>
</div>
</body>
</html>