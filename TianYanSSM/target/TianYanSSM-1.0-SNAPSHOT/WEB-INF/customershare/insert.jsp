<%--
  Created by IntelliJ IDEA.
  User: 15074
  Date: 2022/1/24
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
    <title>Title</title>
    <script src="<%=request.getContextPath()%>/javascript/My97DatePicker/WdatePicker.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/javascript/bootstrap-3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/javascript/bootstrap-table/bootstrap-table.css">
    <script src="${pageContext.request.contextPath}/javascript/jquery-1.12.4.js"></script>
</head>
<script src="${pageContext.request.contextPath}/javascript/bootstrap-3.4.1/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/javascript/bootstrap-table/bootstrap-table.js"></script>
<script src="${pageContext.request.contextPath}/javascript/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<script>
    $.ajax({
        url: 'selectCustomerToChoseByName',
        data:{},
        type:"get",
        dataType:"json",
        success:function(data){
            var s =  document.getElementById('cusname');
            for( var i =0;i<data.length;i++){
                var option = document.createElement("option");
                option.value = data[i].cid;
                option.innerHTML = data[i].cusname;
                s.appendChild(option);
            }
        }
    });
    $.ajax({
        url: 'selectEmployeeToChoseWithoutName',
        data:{},
        type:"get",
        dataType:"json",
        success:function(data){
            var s =  document.getElementById('empid');
            for( var i =0;i<data.length;i++){
                var option = document.createElement("option");
                option.value = data[i].empid;
                option.innerHTML = data[i].username;
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
    function checkName(obj){
        if(obj.value.trim()==""){
            obj.placeholder="客户姓名不能为空!";
            return false;
        }else{
            return true;
        }
    }
    function checkContent(obj){
        if(obj.value.trim()==""){
            obj.placeholder="谈话记录不能为空!";
            return false;
        }else{
            return true;
        }
    }
    function checkDate(obj){
        var reg=/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
        if(obj.value.trim()==""){
            obj.placeholder="谈话日期不能为空!";
            return false;
        }else{
            if(obj.match(reg)){
                return true;
            }
            obj.placeholder="谈话日期格式不正确!";
            return false;
        }
    }
    function checkAll(){
        if(checkDate($("date"))&checkName($("cusname"))&checkContent($("content"))){
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
    <form class="form-horizontal" role="form" action="doCustomershareInsert" method="post">
        <div class="form-group">
            <label for="empid" class="col-sm-2 control-label">目标员工姓名:</label>
            <div class="col-sm-7">
                <select class="col-sm-7" name="empid" id="empid">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="cusname" class="col-sm-2 control-label">客户姓名:</label>
            <div class="col-sm-7">
                <select class="col-sm-7" name="cid" id="cusname">
                    <option value="">请选择</option>
                </select>
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
