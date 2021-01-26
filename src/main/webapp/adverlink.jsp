<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/1/23 0023
  Time: 下午 1:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="id">${id}</div>
</body>
<script src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script>
    $(function () {
        var id=$(".id").html();

        $.ajax({
            type:'post',
            dataType:'json',
            data:{

                'id':id
            },
            url:'advertotal',
            success:function (data) {
                if (data.type==="ok"){
         console.log();
                }
            },
            error:function () {
                alert("系统错误");
            }
        });
    })
</script>
</html>
