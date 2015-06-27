$( document ).ready(function() {
	
   
	
	function searchAjax(query) {
		console.log(query);
		$.ajax({
			url : '/etnshop/product/searchajax/'+query,
			dataType : 'html',
			success : function(data) {
				$('#results').html(data);
				console.log(data);
			}
		});
	};
	
	
	$('#searchbox').on("keyup", function(){
		searchAjax($('#searchbox').val());
    });
	
	
	
});