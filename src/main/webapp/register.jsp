<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>新規登録</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div class="main">
		<h2>新規登録</h2>
		<form action="register" method="post">
			ユーザー名: <input type="text" name="username" required><br>
			パスワード: <input type="password" name="password" required><br>
			<div class="button-group">
				<input type="submit" value="登録">
			</div>
		</form>
		<c:if test="${not empty errorMessage}">
			<p style="color: red;">
				<c:out value="${errorMessage}" />
			</p>
		</c:if>
	</div>
</body>
</html>
