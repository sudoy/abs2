<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="_header.jsp" />

<title>My家計簿アプリ|TOP</title>
</head>
<body>

	<jsp:include page="_nav.jsp" />

	<div class="container pt-6">

	<!-- start alerts -->
	<jsp:include page="_successes.jsp" />
	<!-- end alerts -->

		<div class="row pt-4">
			<div class="col">
				<nav class="float-left">
					<ul class="pagination">
						<li class="page-item"><a class="page-link" href="#"><span
								class="oi oi-chevron-left"></span><span
								class="oi oi-chevron-left"></span> 前年</a></li>
						<li class="page-item"><a class="page-link" href="#"><span
								class="oi oi-chevron-left"></span> 前月</a></li>
					</ul>
				</nav>
			</div>

			<div class="col text-center">
				<h2 class="font-weight-bold">
					<span class="oi oi-calendar"></span>
					${today}
				</h2>
			</div>

			<div class="col">
				<nav class="float-right">
					<ul class="pagination">
						<li class="page-item disabled"><a class="page-link" href="#">翌月
								<span class="oi oi-chevron-right"></span>
						</a></li>
						<li class="page-item disabled"><a class="page-link" href="#">翌年
								<span class="oi oi-chevron-right"></span><span
								class="oi oi-chevron-right"></span>
						</a></li>
					</ul>
				</nav>
			</div>
		</div>

		<!-- 支出別合計金額の計算部分、ここ美しくない -->
		<c:forEach var="myhab" items="${list}">
		<c:if test="${myhab.money >= 0}">
		<c:set var="totalplus" value="${totalplus + myhab.money}"/>
		</c:if>
		<c:if test="${myhab.money <= 0}">
		<c:set var="totalminus" value="${totalminus + myhab.money}"/>
		</c:if>
		</c:forEach>



		<div class="row pt-1">
			<div class="col">
				<div class="card bg-light border-info mb-4">
					<div class="card-header text-center">
						<span class="oi oi-yen"></span> 今月の収入合計 <small class="text-muted">（先月比）</small>
					</div>
					<div class="card-body">
						<p class="card-text text-center">
							${totalplus} <small class="text-info">（+ 0）</small>
						</p>
					</div>
				</div>
			</div>

			<div class="col">
				<div class="card bg-light border-dark mb-4">
					<div class="card-header text-center">
						<span class="oi oi-yen"></span> 今月の支出合計 <small class="text-muted">（先月比）</small>
					</div>
					<div class="card-body">
						<p class="card-text text-center">
							${totalminus} <small class="text-danger">（- 0）</small>
						</p>
					</div>
				</div>
			</div>
		</div>
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
						<c:forEach var="myhab" items="${list}">
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
											<a class="dropdown-item delete-btn" href="index.html?id=${myhab.id}"><span
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