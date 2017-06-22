<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table align="center" width="80%"  border="0" style="height:50px; font-size: 24px;">
        <tr>
            <td align="center">
                用户列表
            </td>
        </tr>
    </table>
    <table align="center" width="80%" border="1px" style="border: black;font-size: 14px;" cellpadding="0" cellspacing="0">
    <tr bgcolor="Silver" style="height: 24px;">
        <td align="center" style="border: black;">序号</td>
        <td align="center" style="border: black;">用户名</td>
        <td align="center" style="border: black;">密码</td>
        <td align="center" style="border: black;">修改用户</td>
        <td align="center" style="border: black;">删除用户</td>
    </tr>
    <c:forEach var="item" items="${users}" varStatus="s">
        <tr style="height: 24px;" target="id" rel="${item.id }">
            <td>${item.id }</td>
            <td>${item.usrNm }</td>
            <td>${item.passWd }</td>
            <td><a href="<c:url value='/user/update/${item.id }' />">修改用户</a></td>
            <td><a href="<c:url value='/user/delete/${item.id }' />">删除用户</a></td>
        </tr>
    </c:forEach>
    </table>
    <table align="center" align="center" width="80%" style="height: 30px;font-size: 14px;" cellpadding="0" cellspacing="0">
        <tr>
            <td align="left"><a href="<c:url value='/user/add'/>">新增</a></td>
            <td align="right">
                <c:if test="${vo.pageNum > 1}">
                    <a href="<c:url value='/user/list'/>?pageNum=1">首页</a>
                    <a href="<c:url value='/user/list'/>?pageNum=${vo.pageNum-1}">上一页</a>
                </c:if>
                <c:if test="${vo.pageNum < vo.pageCount}">
                    <a href="<c:url value='/user/list'/>?pageNum=${vo.pageNum+1}">下一页</a>
                    <a href="<c:url value='/user/list'/>?pageNum=${vo.pageCount}">尾页</a>
                </c:if>
            </td>
        </tr>
    </table>
</body>
</html>
