<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${successes.size() > 0}">
	<div class="row">
		<div class="col">
			<div class="alert alert-success alert-dismissible fade show"
				role="alert">
				<h4 class="alert-heading h5 font-weight-bold">
					<span class="oi oi-pin"></span> 成功しました！
				</h4>
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<ul>
					<c:forEach var="successes" items="${successes}">
						<li>「${param.dating} ${param.category} ${param.money}」を ${successes}</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<%
		session.setAttribute("successes", null);
	%>

</c:if>