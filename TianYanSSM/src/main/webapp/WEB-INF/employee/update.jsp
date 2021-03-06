<%--
  Created by IntelliJ IDEA.
  User: 15074
  Date: 2022/1/22
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>showAll</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/javascript/bootstrap-3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/javascript/bootstrap-table/bootstrap-table.css">
    <script src="${pageContext.request.contextPath}/javascript/jquery-1.12.4.js"></script>
</head>
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
<body>
    <%--<input type="button" onclick="getJson()"/>--%>
    <table id="mytable">
    </table>
    <script>
        $(function (){
            let a =  new Mytable();
            a.init();
        })
        var Mytable = function (){
            let obj = new Object();
            obj.init = function (){
                $('#mytable').bootstrapTable({
                    url: 'showEmployeeQueryIn',         //???????????????URL???*???
                    method: 'post',                      //???????????????*???
                    // contentType: "application/json;charset=utf-8",
                    striped: true,                      //????????????????????????
                    pagination: true,                   //?????????????????????*???
                    sidePagination: "server",           //???????????????client??????????????????server??????????????????*???
                    pageNumber:1,                       //??????????????????????????????????????????
                    pageSize: 5,                       //????????????????????????*???
                    pageList: [5,10, 25, 50, 100],        //?????????????????????????????????*???
                    strictSearch: true,
                    showColumns: true,                  //????????????????????????
                    minimumCountColumns: 2,             //?????????????????????
                    clickToSelect: true,                //???????????????????????????
                    columns: [{
                        checkbox: true
                    }, {
                        field: 'empid',
                        title: '??????ID',
                    }, {
                        field: 'username',
                        title: '??????',
                    }, {
                        field: 'tel',
                        title: '????????????',
                    },{
                        field: 'name',
                        title: '????????????',
                    },{
                        field: 'tel',
                        title: '????????????',
                    },{
                        field:'email',
                        title:'????????????',
                    },{
                        field:'boss',
                        title:'????????????',
                    },{
                        field:'roleName',
                        title:'????????????',
                    },{
                        field:'empid',
                        title:'??????',
                        formatter:editAndDel
                    }]
                })
            }
            return obj;
        }
        function editAndDel(value,row,index){
            return `
                        <a href="#" class="btn btn-sm btn-success" data-toggle="modal" data-target="#myModal" onclick="edit(`+value+`)">??????</a>
                    `;
        }
        function edit(id){
            // alert("yes,you are editing-------->"+id);
            // console.log(id);
            $.ajax({
                url: "findEmployeeById",
                data:{
                    "id":id
                },
                type:"get",
                dataType:"json",
                success:function (jsonObj){
                    $("#name").val(jsonObj.name);
                    $("#empid").val(jsonObj.empid);
                    $("#username").val(jsonObj.username);
                    $("#tel").val(jsonObj.tel);
                    $("#email").val(jsonObj.email);
                    $("#roleid").val(jsonObj.roleid);
                    $("#emptype").val(jsonObj.emptype);
                }

            })
        }
    </script>
    <script src="${pageContext.request.contextPath}/javascript/bootstrap-3.4.1/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/javascript/bootstrap-table/bootstrap-table.js"></script>
    <script src="${pageContext.request.contextPath}/javascript/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        ????????????????????????
                    </h4>
                </div>
                <div class="modal-body">
                    <form id="updateform" class="form-horizontal">
                        <input type="text" hidden name="empid" id="empid">
                        <div class="form-group">
                            <label for="username" class="col-sm-2 control-label">????????????:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="username" name="username" placeholder="?????????????????????" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="emptype" class="col-sm-2 control-label">????????????:</label>
                            <div class="col-sm-7">
                                <select class="col-sm-7" name="emptype" id="emptype">
                                    <option value="">?????????</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">????????????:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="name" name="name" placeholder="?????????????????????" >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="tel" class="col-sm-2 control-label">????????????:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="tel" name="tel" placeholder="???????????????????????????"  >
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="roleid" class="col-sm-2 control-label">??????:</label>
                            <div class="col-sm-7">
                                <select class="col-sm-7" name="roleid" id="roleid">
                                    <option value="">?????????</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">????????????:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="email" name="email" placeholder="???????????????????????????"  >
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <span id="returnMessage" class="glyphicon"> </span>
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        ??????
                    </button>
                    <button id="submitBtn" type="button" class="btn btn-primary" >
                        ????????????
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</body>
<script src="https://cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.js"></script>
<script type='text/javascript'>
    var form = $('#updateform');
    $(function (){
        form.bootstrapValidator({
            message: '??????????????????',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                username: {
                    message: '?????????????????????',
                    validators: {
                        notEmpty: {
                            message: '????????????????????????'
                        },
                        stringLength: {
                            min: 1,
                            max: 30,
                            message: '?????????1???30?????????'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_\. \u4e00-\u9fa5 ]+$/,
                            message: '?????????????????????????????????????????????????????????????????? '
                        }
                    }
                },name: {
                    message: '???????????????',
                    validators: {
                        notEmpty: {
                            message: '???????????????'
                        },
                        stringLength: {
                            min: 1,
                            max: 30,
                            message: '?????????1???30?????????'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_\. \u4e00-\u9fa5 ]+$/,
                            message: '??????????????????????????????????????????????????????????????? '
                        }
                    }
                },
                tel: {
                    validators: {
                        notEmpty: {
                            message: '?????????????????????'
                        },
                        regexp: {
                            regexp: /^([0-9]{11})?$/,
                            message: '????????????????????????'
                        }
                    }
                },
                email: {
                    validators: {
                        notEmpty: {
                            message: '????????????????????????'
                        },
                        emailAddress: {
                            message: '????????????????????????'
                        }
                    }
                }
            }
        });
    });
    $("#submitBtn").click(function () {
        //??????????????????
        var bv = form.data('bootstrapValidator');
        bv.validate();
        if (bv.isValid()) {
            console.log(form.serialize());
            $.ajax({
                url: 'doEmployeeUpdate',
                type: 'post',//PUT DELETE POST
                data: form.serialize(),
                success: function (result) {
                    console.log(result);
                    if (result == 'ok') {
                        alert("???????????????");
                        window.location.reload();
                    }else if (result == 'repeat') {
                        $("#returnMessage").hide().html('<label class="label label-danger">??????????????????!</label>').show(300);
                    } else {
                        $("#returnMessage").hide().html('<label class="label label-danger">????????????!</label>').show(300);
                    }
                }, error: function () {
                    $("#returnMessage").hide().html('<label class="label label-danger">????????????!</label>').show(300);
                }
            })
        }
    });
</script>
</html>
