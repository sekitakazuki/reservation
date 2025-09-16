<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>簡易予約システム</title>
<link rel="stylesheet" href="style.css">
<script src="https://kit.fontawesome.com/c187d503f4.js" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
			<h1>予約入力</h1>
			<form action="reservation" method="post">
			<input type="hidden" name="action" value="add">
			<c:choose>
				<c:when test="${not empty sessionScope.user}">
					<input type="hidden" name="name" value="${sessionScope.user}" />
					<p>名前:<c:out value="${sessionScope.user}" /></p>
				</c:when>
				<c:otherwise>
					<p>
						<label for="name">名前:</label> <input type="text" id="name" name="name" value="<c:out value='${param.name}'/>" required>
						<span class="error-message"><c:out value="${errorMessage}" /></span>
					</p>
				</c:otherwise>
			</c:choose>
			<p>
					<label for="reservation_time">希望日時:</label> 
					<input type="datetime-local" id="reservation_time" name="reservation_time" value="<c:out value="${param.reservation_time}"/>" required>
					<span class="error-message"><c:out value="${errorMessage}" /></span>
				</p>
				<div class="button-group">
					<input type="submit" value="予約する">
				</div>
			</form>
			<hr>
			<h2>CSV インポート</h2>
			<form action="reservation" method="post" enctype="multipart/form-data">
				<input type="hidden" name="action" value="import_csv">
				<p>
					<label for="csvFile">CSV ファイルを選択:</label> <input type="file"
						id="csvFile" name="csvFile" accept=".csv" required>
				</p>
				<div class="button-group">
					<input type="submit" value="インポート">
				</div>
			</form>
			<p class="error-message">
				<c:out value="${errorMessage}" />
			</p>
			<p class="success-message">
				<c:out value="${successMessage}" />
			</p>
	</div>
		<div class="user button-group">
			<c:choose>
			<c:when test="${not empty sessionScope.user}">
				<p>ようこそ、<c:out value="${sessionScope.user}"/> さん！</p>
				<p><a href="logout" class="btn btn-flat2"><span>ログアウト</span></a></p>
				<hr>
				<p><a href="reservation?action=list" class="btn btn-flat"><span>予約一覧へ</span></a></p>
			</c:when>
			<c:otherwise>
				<p><a href="login.jsp" class="btn btn-flat2"><span>ログイン</span></a></p>
				<p><a href="register.jsp" class="btn btn-flat3"><span>新規登録</span></a></p>
				<hr>
				<p><a href="reservation?action=list" class="btn btn-flat"><span>予約一覧へ</span></a></p>
			</c:otherwise>
	    </c:choose>
		</div>
</body>
</html>