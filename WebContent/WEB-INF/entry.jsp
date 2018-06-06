<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="_header.jsp" />

	<title>My家計簿アプリ|登録フォーム</title>
</head>
<body>
	<nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
		<div class="container">
			<a class="navbar-brand mb-0" href="index.html">My家計簿アプリ</a>
			<form class="form-inline">
				<a class="btn btn-outline-success mr-2" href="search.html"><span class="oi oi-magnifying-glass"></span> 検 索</a>
				<a class="btn btn-outline-info" href="entry.html"><span class="oi oi-plus"></span> 登 録</a>
			</form>
		</div>
	</nav>

	<div class="container pt-6">

	<!-- start alerts -->
	<jsp:include page="_successes.jsp" />
	<jsp:include page="_errors.jsp" />
	<!-- end alerts -->

		<div class="row justify-content-between">
			<div class="offset-1 col">
				<h2 class="font-weight-bold">登録フォーム</h2>
			</div>
		</div>

		<hr class="mt-1">

		<form action="#" method="post">
			<div class="form-group row">
				<label for="date" class="offset-2 col-sm-2 col-form-label font-weight-bold">日付 <span class="badge badge-danger">必須</span></label>
				<div class="col-2">
					<input type="text" class="form-control" name ="dating" id="date" placeholder="日付" aria-describedby="dateHelp" value="${param.dating != null? param.dating : myhab.dating}">
				</div>
				<div class="col-4">
					<small id="dateHelp" class="text-muted align-bottom">「YYYY/MM/DD」形式で入力してください。</small>
				</div>
			</div>

			<fieldset class="form-group">
				<div class="row">
					<legend class="offset-2 col-form-label col-2 pt-0 font-weight-bold">区分</legend>
					<div class="col-sm-8">
						<div class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="division1" name="in_out" class="custom-control-input" value="1"${param.in_out}>
							<label class="custom-control-label" for="division1">支出</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="division2" name="in_out" class="custom-control-input"value="2" ${param.in_out}>
							<label class="custom-control-label" for="division2">収入</label>
						</div>
					</div>
				</div>
			</fieldset>

			<div class="form-group row">
				<label for="category" class="offset-2 col-sm-2 col-form-label font-weight-bold">カテゴリー <span class="badge badge-danger">必須</span></label>
				<div class="col-4">
					<select class="custom-select" id="category" name ="category">
						<option selected>選択して下さい</option>
						<option value="1">食費</option>
						<option value="2">日用品</option>
						<option value="3">交際費</option>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label for="note" class="offset-2 col-sm-2 col-form-label font-weight-bold">備考</label>
				<div class="col-6">
					<textarea class="form-control" name ="memo" id="note" placeholder="備考" rows="3">${param.memo}</textarea>
				</div>
			</div>
			<div class="form-group row">
				<label for="amount" class="offset-2 col-sm-2 col-form-label font-weight-bold">金額 <span class="badge badge-danger">必須</span></label>
				<div class="col-2">
					<input type="text" class="form-control" name ="money" id="amount" placeholder="金額" value="${param.money}">
				</div>
			</div>

			<div class="form-group row">
				<div class="offset-4 col-8">
					<a href="index.html" class="btn btn-secondary">キャンセル</a>
					<a href="index.html" class="btn btn-primary"><span class="oi oi-check"></span> 登録OK</a>
					<input type="submit" class="btn btn-primary glyphicon glyphicon-ok" value="登録OK" />
				</div>
			</div>
		</form>
	</div>

<jsp:include page="_footer.jsp" />