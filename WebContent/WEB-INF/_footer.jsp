<%@ page pageEncoding="UTF-8" %>

	<hr>

	<footer class="footer">
		<div class="container">
			<p class="text-muted small">&copy; 2018 SIE Inc.</p>
		</div>
	</footer>

	<script src="js/jquery-3.3.1.slim.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
	<script>
$(function(){
	$('.delete-btn').on('click', function(){
		return confirm('削除してよろしいですか？');
	});
	
	$('.category-all').on('click', function(){
		// allのチェック状態と他の選択肢のチェック状態をリンク
		$('.category').prop('checked', $(this).prop('checked'));
	});
	
	$('.category').on('click', function(){
		if(!$(this).prop('checked')){
			// チェックが外れたときは、allのチェックも外す
			$('.category-all').prop('checked', false);
			
		}else{
			// チェックが入ったときは、
			// 他の選択肢もすべてチェックだった場合に、allをチェックする
			var isChange = true;
			
			$('.category').each(function(){
				if(!$(this).prop('checked')){
					isChange = false;
				}
			});
			if(isChange){
				$('.category-all').prop('checked', true);
			}
		}
	});
});
</script>
</body>
</html>