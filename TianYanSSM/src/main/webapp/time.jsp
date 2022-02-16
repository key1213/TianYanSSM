<%--
  Created by IntelliJ IDEA.
  User: 15074
  Date: 2022/1/11
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="<%=request.getContextPath()%>/javascript/DateHelper/js/html5shiv.min.js"></script>
    <script src="<%=request.getContextPath()%>/javascript/DateHelper/js/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/javascript/DateHelper/css/bootstrap.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/javascript/DateHelper/css/bootstrap-datetimepicker.css">
</head>
<body>
<div class="container">
    <form action="" class="form-horizontal"  role="form">

        <fieldset>
            <legend>bootstrap-datetimepicker</legend>

            <div class="form-group">
                <label for="dtp_input1" class="col-md-2 control-label">时间戳(日期带时间)：</label>
                <div class="input-group date form_datetime col-md-5"
                     data-date-format="yyyy-mm-dd hh:ii:ss" data-link-field="dtp_input1" >
                    <input class="form-control" size="16" type="text"  readonly>

                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                </div>
                <input type="hidden" id="dtp_input1" value="" /><br/>
            </div>


            <div class="form-group">
                <label for="dtp_input2" class="col-md-2 control-label">日期：</label>
                <div class="input-group date form_date col-md-5" data-date=""
                     data-date-format="yyyy-MM-dd HH:mm:ss" data-link-field="dtp_input2" >


                    <input class="form-control" size="16" type="text"  readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
                <input type="hidden" id="dtp_input2" value="" /><br/>
            </div>


            <div class="form-group">
                <label for="dtp_input3" class="col-md-2 control-label">时间：</label>
                <div class="input-group date form_time col-md-5" data-date="" data-date-format="hh:ii:ss" data-link-field="dtp_input3" >

                    <input class="form-control" size="16" type="text"  readonly>

                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-time"></span></span>
                </div>

                <input type="hidden" id="dtp_input3" value="" /><br/>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>
<script src="<%=request.getContextPath()%>/javascript/DateHelper/js/jquery-3.2.1.js"></script>
<script src="<%=request.getContextPath()%>/javascript/DateHelper/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/javascript/DateHelper/js/bootstrap-datetimepicker.js"></script>
<script src="<%=request.getContextPath()%>/javascript/DateHelper/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=request.getContextPath()%>/javascript/DateHelper/js/locales/bootstrap-datetimepicker.bn.js"></script>
<script>
    $('.form_datetime').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });
    $('.form_date').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
    $('.form_time').datetimepicker({
        language:  'en',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 1,
        minView: 0,
        maxView: 1,
        forceParse: 0
    });
</script>