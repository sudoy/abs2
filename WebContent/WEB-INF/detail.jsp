<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="abs2.utils.HTMLUtils"%>


<jsp:include page="_header.jsp" />

	<title>My家計簿アプリ|詳細フォーム</title>
</head>
<body>

<jsp:include page="_nav.jsp" />

	<div class="container pt-6">
	
<!-- start alerts -->
<jsp:include page="_successes.jsp" />
<jsp:include page="_errors.jsp" />
<!-- end alerts -->
	

		<div class="row justify-content-between">
			<div class="offset-1 col">
				<h2 class="font-weight-bold">詳細フォーム</h2>
			</div>
		</div>

		<hr class="mt-1">

		<form action="#" method="post">
			<div class="form-group row">
				<label for="date" class="offset-2 col-sm-2 col-form-label font-weight-bold">日付</label>
				<div class="col-2">
					<input type="text" class="form-control" id="date" name="dating" placeholder="日付" aria-describedby="dateHelp" value="${param.dating != null ? param.dating : myhab.dating}" readonly>
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
							<input type="radio" id="division1" name="in_out" class="custom-control-input" value="1" ${HTMLUtils.checkInOut(param.inOut != null? param.inOut : myhab.inOut, '1')} disabled>
							<label class="custom-control-label" for="division1">支出</label>
						</div>
						<div class="custom-control custom-radio custom-control-inline">
							<input type="radio" id="division2" name="in_out" class="custom-control-input" value="2" ${HTMLUtils.checkInOut(param.inOut != null? param.inOut : myhab.inOut, '2')}  disabled>
							<label class="custom-control-label" for="division2">収入</label>
						</div>
					</div>
				</div>
			</fieldset>

			<div class="form-group row">
				<label for="category" class="offset-2 col-sm-2 col-form-label font-weight-bold">カテゴリー</label>
				<div class="col-4">
					<select class="custom-select" id="category" name="category" disabled>
						<option value="0" ${HTMLUtils.checkCategory(param.category != null? param.category : myhab.category, '0')}>選択して下さい</option>
						<option value="1" ${HTMLUtils.checkCategory(param.category != null? param.category : myhab.category, '1')}>食費</option>
						<option value="2" ${HTMLUtils.checkCategory(param.category != null? param.category : myhab.category, '2')}>日用品</option>
						<option value="3" ${HTMLUtils.checkCategory(param.category != null? param.category : myhab.category, '3')}>交際費</option>
					</select>
				</div>
			</div>
			<div class="form-group row">
				<label for="note" class="offset-2 col-sm-2 col-form-label font-weight-bold">備考</label>
				<div class="col-6">
					<textarea class="form-control" id="note" name="memo" placeholder="備考" rows="3" readonly>${myhab.memo}</textarea>
				</div>
			</div>
			<div class="form-group row">
				<label for="amount" class="offset-2 col-sm-2 col-form-label font-weight-bold">金額</label>
				<div class="col-2">
					<input type="text" class="form-control" id="amount" name="money" placeholder="金額" value="${myhab.money}" readonly>
				</div>
			</div>

			<div class="form-group row">
				<div class="offset-4 col-6">
					<a href="index.html" class="btn btn-secondary">キャンセル</a>
					<a href="update.html?id=${myhab.id}" class="btn btn-primary"><span class="oi oi-pencil"></span> 修 正</a>
				</div>
				<div class="col-2 text-right">
					<a href="index.html" class="btn btn-danger delete-btn"><span class="oi oi-trash"></span> 削 除</a>
				</div>
			</div>
		</form>
	</div>

<jsp:include page="_footer.jsp" />