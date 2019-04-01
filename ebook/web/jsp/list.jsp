<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/27
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/ebook" method="post" id="form1">
    <table border="0" cellspacing="0" cellpadding="5"width="700">
        <input type="hidden" name="method" value="showlist">
        <input type="hidden" name="PC" id="pcNO" value="${sessionScope.pb.pc}">
        <tr>
            <td>图书分类：<select name="CategoryId" id="select1">
                <option value="0">===全部===</option>
                <c:forEach var="cates" items="${sessionScope.CATES}">
                    <option value=${cates.id}
                            <c:if test="${sessionScope.cid==cates.id}">
                                    selected="selected"
                    </c:if>
                    >${cates.name}</option>
                </c:forEach>
            </select><input type="submit" value="查询"/></td>
            <td><input type="button" name="" id="" value="新增电子图书" onclick="window.location.href='${pageContext.request.contextPath}/jsp/add.jsp?typeid='+document.getElementById('select1').value"/></td>
        </tr>
    </table>
    <table border="1" cellspacing="0" cellpadding="5" width="700">
        <tr><th colspan="6"><h2>电子图书列表</h2></th></tr>
        <tr>
            <th>图书编号</th>
            <th>图书名称</th>
            <th>图书摘要</th>
            <th>上传人</th>
            <th>上传时间</th>
            <th>操作</th>
        </tr>
        <c:forEach var="pb" items="${sessionScope.pb.list}" varStatus="vs">
        <tr <c:if test="${vs.count%2==1}">
            bgcolor="aqua"
        </c:if>>
                <td>${pb.categoryId}</td>
                <td>${pb.title}</td>
                <td>${pb.summary}</td>
                <td>${pb.uploaduser}</td>
                <td>${pb.createdate}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/ebook?method=amend&ID=${pb.id}">修改</a>  <a href="${pageContext.request.contextPath}/ebook?method=delete&ID=${pb.id}" onclick="dl()">删除</a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${sessionScope.I!=null}" >
            <tr><td colspan="6"><font color="red">${sessionScope.I}</font></td></tr>
        </c:if>
    </table>
    <c:choose>
        <c:when test="${sessionScope.pb.pc==1}">
            首页
        </c:when>
        <c:otherwise>
            <a href="javascript:void(0)" onclick="go(1)">首页</a>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${!sessionScope.pb.hasprevious}">
            上一页
        </c:when>
        <c:otherwise>
            <a href="javascript:void(0)" onclick="go(${sessionScope.pb.previous})">上一页</a>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${!sessionScope.pb.hasnext}">
            下一页
        </c:when>
        <c:otherwise>
            <a href="javascript:void(0)" onclick="go(${sessionScope.pb.next})">下一页</a>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${sessionScope.pb.pc==sessionScope.pb.tp}">
            尾页
        </c:when>
        <c:otherwise>
            <a href="javascript:void(0)" onclick="go(${sessionScope.pb.tp})">尾页</a>
        </c:otherwise>
    </c:choose>
    第${sessionScope.pb.pc}页/共${sessionScope.pb.tp}页
</form>
</body>
<script type="text/javascript">
    function go(pcno){
        document.getElementById("pcNO").value=pcno;
        document.getElementById("form1").submit();
    }
    function dl() {
        alert('确认删除吗?')
    }
</script>
</html>
