<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
	$('#searchBtn').on('click',function(e){
		var form = $('form#search');
				
		var searchType=$('select#searchType');
		if(searchType.val()==""){
			alert("검색구분을 선택하세요.");
			searchType.focus();
			return;
		}
		form.submit();
	});
	
	$('#first_grid-pager>span').on('click',function(e){
		alert("first btn click");
	});
	$('#prev_grid-pager>span').on('click',function(e){
		alert("prev btn click");
	});
	$('#next_grid-pager>span').on('click',function(e){
		alert("next btn click");
	});
	$('#last_grid-pager>span').on('click',function(e){
		alert("last btn click");
	});
	
	$('input#pageNum').on('change',function(e){
		alert('page num changed');
	});
	$('select#perPageNum').on('change',function(e){
		alert('perPageNum changed');
	});

</script>