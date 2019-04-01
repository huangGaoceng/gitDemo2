<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/27
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/ebook" method="post">
    <input type="hidden" name="method" value="saveamend">
    <input type="hidden" name="id" value="${sessionScope.EB.id}">
    <input type="hidden" name="Typeid" value="${sessionScope.EB.categoryId}">
    <table border="1" cellspacing="0" cellpadding="5" width="700">
        <tr><th colspan="2"><h2>更新电子图书</h2></th></tr>
        <tr>
            <td>图书编号</td>
            <td><input type="text" name="BookId"  value="${sessionScope.EB.categoryId}" /></td>
        </tr>
        <tr>
            <td>图书名称(*)</td>
            <td><input type="text" name="bookName"  value="${sessionScope.EB.title}" /></td>
        </tr>
        <tr>
            <td>图书摘要(*)</td>
            <td><textarea name="bookZY" rows="5px" cols="60px">${sessionScope.EB.summary}</textarea></td>
        </tr>
        <tr>
            <td>上传人</td>
            <td><input type="text" name="onPepole"  value="${sessionScope.EB.uploaduser}" /></td>
        </tr>
        <tr>
            <td>上传时间(*)</td>
            <td><input type="text" name="onTime"  value="${sessionScope.EB.createdate}" />(yyyy-MM-dd)</td>
        </tr>
        <tr><td colspan="2" align="center"><input type="button"  value="返回" onclick="bk()"/>  <input type="submit" name="" id="" value="提交" /></td></tr>
    </table>
</form>
</body>
<script type="text/javascript">
    function bk() {
        window.history.back();
    }
</script>
</html>
