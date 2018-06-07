<%@page contentType="text/html; charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="_header.jsp" />

	<title>My家計簿アプリ|TOP</title>
</head>
<body>

<jsp:include page="_nav.jsp" />

	<div class="container pt-6">

		<div class="row">
			<div class="offset-1 col">
				<h2 class="font-weight-bold">検索結果リスト</h2>
			</div>
		</div>

		<div class="row">
			<div class="offset-1 col">
				<span class="badge badge-info">日付：${param.dating1}～${param.dating2}</span>
				<span class="badge badge-info">カテゴリー：</span>
				<span class="badge badge-info">備考：${param.memo}</span>
			</div>
		</div>

		<hr class="mt-1">

		<div class="row">
			<div class="col">
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col" style="width: 90px;">#</th>
							<th scope="col" style="width: 120px;">日付</th>
							<th scope="col">カテゴリー</th>
							<th scope="col">備考</th>
							<th scope="col" style="width: 120px;">金額</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="myhab" items="${list}" varStatus="a">
						<c:set var="total" value="${total + myhab.money}"/>
							<tr class="table-light">
								<th scope="row">
									<div class="btn-group">
										<button type="button"
											class="btn btn-secondary btn-sm dropdown-toggle"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false">操作</button>
										<div class="dropdown-menu">
											<a class="dropdown-item" href="detail.html?id=${myhab.id}"><span
												class="oi oi-spreadsheet"></span> 詳細</a> <a
												class="dropdown-item" href="copy.html?id=${myhab.id}"><span
												class="oi oi-paperclip"></span> コピー</a>
											<div class="dropdown-divider"></div>
											<a class="dropdown-item delete-btn" href="delete.html?id=${myhab.id}"><span
												class="oi oi-trash"></span> 削除</a>
										</div>
									</div>
								</th>
								<td>${myhab.dating}</td>
								<td>${myhab.category}</td>
								<td>${myhab.memo}</td>
								<td class="text-right">${myhab.money}</td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>
		</div>
	</div>

<jsp:include page="_footer.jsp" />