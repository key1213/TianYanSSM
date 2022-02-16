<%--
  Created by IntelliJ IDEA.
  User: 15074
  Date: 2022/1/24
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/javascript/bootstrap-3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/javascript/bootstrap-table/bootstrap-table.css">
    <script src="${pageContext.request.contextPath}/javascript/jquery-1.12.4.js"></script>
</head>
<script src="${pageContext.request.contextPath}/javascript/bootstrap-3.4.1/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/javascript/bootstrap-table/bootstrap-table.js"></script>
<script src="${pageContext.request.contextPath}/javascript/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<script>
    $.ajax({
        url: 'selectAllEmployeeToChose',
        data:{},
        type:"get",
        dataType:"json",
        success:function(data){
            var s =  document.getElementById('emptype');
            for( var i =0;i<data.length;i++){
                var option = document.createElement("option");
                option.value = data[i].empid;
                option.innerHTML = data[i].username;
                s.appendChild(option);
            }
        }
    });
    $.ajax({
        url: 'selectAllRoleToChose',
        data:{},
        type:"get",
        dataType:"json",
        success:function(obj){
            var s =  document.getElementById('roleid');
            for( var i =0;i<obj.length;i++){
                var option = document.createElement("option");
                option.value = obj[i].roleId;
                option.innerHTML = obj[i].roleName;
                s.appendChild(option);
            }
        }
    });
</script>
<script>
    function $(id){
        return document.getElementById(id);
    }
    String.prototype.trim=function()
    {
        return this.replace(/(^\s*)|(\s*$)/g,"");
    }
    function checkEmail(obj) {
        var str = obj.value;
        var reg = /\w{4,}@[a-z|A-Z|0-9]{2,6}\.[com|cn|com.cn]/;
        var flag = reg.test(str);
        if(obj.value==""){
            obj.placeholder="电子邮箱不能为空!";
            return false;
        }else{
            if(flag){
                return true;
            }else{
                obj.value="";
                obj.placeholder="注意！邮箱格式错误!";
                return false;
            }
        }
    }
    function checkTel(obj){
        var str = obj.value;
        var teg = /^[0-9]{11}$/;
        if(obj.value==""){
            obj.placeholder="电话不能为空!";
            return false;
        }else{
            if(teg.test(str)){
                return true;
            }else{
                obj.value="";
                obj.placeholder="电话号码只能为11位纯数字!";
                return false;
            }
        }
    }
    function checkUsernameame(obj){
        if(obj.value.trim()==""){
            obj.placeholder="员工名称不能为空!";
            return false;
        }else{
            return true;
        }
    }
    function checkName(obj){
        if(obj.value.trim()==""){
            obj.placeholder="员工姓名不能为空!";
            return false;
        }else{
            return true;
        }
    }
    function checkAll(){
        if(checkUsernameame($("username"))&checkName($("name"))
            &checkTel($("tel"))&checkEmail($("email"))){
            return true;
        }
        alert("请确认您填写表格中不为空,并确定所输入的不全为空格！");
        return false;
    }
</script>

<body>
    <br/>
    <br/>
    <br/>
    <form class="form-horizontal" role="form" action="doEmployeeInsert" method="post">
        <div class="form-group">
            <label for="username" class="col-sm-2 control-label">员工姓名:</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" name="username" id="username" placeholder="请输入姓名" onblur="checkUsernameame(this)">
            </div>
        </div>
        <div class="form-group">
            <label for="emptype" class="col-sm-2 control-label">直属上司:</label>
            <div class="col-sm-7">
                <select class="col-sm-7" name="emptype" id="emptype">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">姓名:</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" name="name" id="name" placeholder="请输入姓名" onblur="checkName(this)">
            </div>
        </div>
        <div class="form-group">
            <label for="roleid" class="col-sm-2 control-label">职称:</label>
            <div class="col-sm-7">
                <select class="col-sm-7" name="roleid" id="roleid">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="tel" class="col-sm-2 control-label">电话号码:</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" name="tel" id="tel" placeholder="请输入电话号码" onblur="checkTel(this)">
            </div>
        </div>
        <div class="form-group">
            <label for="email" class="col-sm-2 control-label">电子邮件:</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" name="email" id="email" placeholder="请输入电子邮件" onblur="checkEmail(this)">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-6">
                <button type="submit" class="btn btn-warning btn-lg btn-block" onclick="return checkAll()">提交</button>
            </div>
            <div class="col-sm-12" ></div>
            <div class="col-sm-offset-1 col-sm-6">
                <button type="reset" class="btn btn-primary btn-lg btn-block">重置</button>
            </div>
        </div>
    </form>
</body>
</html>
