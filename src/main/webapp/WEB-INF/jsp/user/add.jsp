<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<c:url value='/user/addres'/>" method="post">
    <table align="center" align="center" width="80%" cellpadding="0" cellspacing="0" style=";height: 30px;font-size: 24px;">
    <tr>
        <td align="center">
            新增用户
        </td>
    </tr>
    </table>
    <table align="center" align="center" width="30%" cellpadding="0" cellspacing="0" style="height: 20px; font-size: 14px;">
        <tr style="height: 20px;font-size: 14px;">
            <td align="right" width="42%" style="border:1px solid black;font-size: 14px;">用户名：</td>
            <td align="left" width="58%" style="border:1px solid black;font-size: 14px;"><input type="text" name="usrNm" /></td>
        </tr>
        <tr style="height: 20px;font-size: 14px;">
            <td align="right" style="border:1px solid black;font-size: 14px;">密码：</td>
            <td align="left" style="border:1px solid black;font-size: 14px;"><input type="password" name="passWd" /></td>
        </tr>
        <tr style="height: 30px;font-size: 14px;">
            <td align="right" style="border:1px solid black;font-size: 14px;"><input type="reset"  value="重置" />
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td style="border:1px solid black;font-size: 14px;" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="submit" value="提交 " />
            </td>
        </tr>
    </table>
</form>
</body>
</html>
