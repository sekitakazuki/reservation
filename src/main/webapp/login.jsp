<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>ログイン</title>
<link rel="stylesheet" href="style.css">
<script src="https://kit.fontawesome.com/c187d503f4.js" crossorigin="anonymous"></script>
</head>
<body>
	<div class="main">
		<h2>ログイン</h2>
		<form action="login" method="post">
			ユーザー名: <input type="text" name="username" required><br>
			パスワード: <input type="password" name="password" required><br>
			<div class="button-group">
				<input type="submit" value="ログイン">
			</div>
		</form>
		<p>
			<a href="register.jsp">新規登録はこちら</a>
		</p>
		<c:if test="${not empty errorMessage}">
			<p style="color: red;">
				<c:out value="${errorMessage}" />
			</p>
		</c:if>
	</div>
</body>
</html>
