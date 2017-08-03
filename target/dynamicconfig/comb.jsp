<%--
  Created by IntelliJ IDEA.
  User: bbking
  Date: 17-7-31
  Time: 上午10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>长连接测试</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
    <script type="text/javascript">
        $(function(){
            function longPolling2(){
                $.get('${pageContext.request.contextPath }/async/test', function(data){
                    console.log(data);
                    $('#n2').html(data);
                    longPolling2();
                });
            }
            longPolling2();
        });
    </script>
</head>

<body>
<h1>长连接测试</h1>
<h2 id="n1"></h2>
<h2 id="n2"></h2>
</body>
</html>