<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.1.2/handlebars.min.js" ></script>
<script id="reply-template" type="text/x-handlebars-template">
	{{#each .}}
		<div class="itemdiv dialogdiv replyLi">
			<div class="user">
				<img alt="Jennifer's Avatar" src="<%=request.getContextPath() %>/resources/images/avatar4.png">
			</div>
			<div class="body">
				<div class="name" style="cursor: pointer;">
					<a class="maninfo" rel="20051014164401" data-hasqtip="2">{{replyer}}</a>
				</div>
				<div class="time">
					 <a class="btn btn-primary btn-xs" id="modifyReplyBtn" style="display:{{loginUserCheck replyer}};"
	    				data-replyer={{replyer}} data-toggle="modal" data-target="#modifyModal">Modify</a>
					<i class="ace-icon fa fa-clock-o"></i>
					<span class="blue">{{prettifyDate regdate}}</span>
				</div>
				<div class="text">
					<i class="ace-icon fa fa-quote-left" style="color:#555;"></i>
					<span style="white-space:pre-wrap;line-height:130%;">{{replytext}}</span>
						&nbsp;<i class="ace-icon fa fa-quote-right" style="color:#555;"></i>
				</div>
			</div>
		</div>	
	{{/each}}
</script>
<script>

var bno=${board.bno}; 

Handlebars.registerHelper("prettifyDate",function(timeValue){
	var dateObj=new Date(timeValue);
	var year=dateObj.getFullYear();
	var month=dateObj.getMonth()+1;
	var date=dateObj.getDate();
	return year+"/"+month+"/"+date;
});

Handlebars.registerHelper("loginUserCheck",function(replyer){
	var result="none";
	
	if(replyer=="${loginUser.id}"){
		result="visible";
	}	
	return result;
});

var printData=function(replyArr,target,templateObject){
	var template=Handlebars.compile(templateObject.html());
	var html=template(replyArr);
	$('.replyLi').remove();
	target.append(html);
};
var replyPage=1;
function getPage(replyUrl){		
	$.getJSON(replyUrl,function(data){
		printData(data.replyList,$('#repliesDiv'),$('#reply-template'));
		printPaging(data.pageMaker,$('.pagination'));
	});
}

getPage("<%=request.getContextPath()%>/replies/"+bno+"/"+replyPage);

var lastPageNum=-1;
	
var printPaging=function(pageMaker,target){
	var str="";
	lastPageNum=pageMaker.realEndPage;
	if(pageMaker.prev){
		str+="<li><a href='"+(pageMaker.startPage-1)
				+"'> << </a></li>";
	}
	for(var i=pageMaker.startPage,len=pageMaker.endPage;i<=len;i++){
		var strClass = pageMaker.cri.page==i?'class=active':'';
		str+="<li "+strClass+"><a href='"+i+"'>"+i+"</a></li>";
	}
	if(pageMaker.next){
		str+="<li><a href='"+(pageMaker.endPage+1)
			+"'> >> </a></li>";
	}
	target.html(str);
}

$('.pagination').on('click','li a',function(event){
	event.preventDefault();
	replyPage=$(this).attr("href");
	getPage("<%=request.getContextPath()%>/replies/"+bno+"/"+replyPage);
});

function reply_regist_go(){

	var replyer = "${loginUser.id}";
	var replyText=$('#newReplyText').val();
	
	if(replyText==""){
		alert('댓글 내용은 필수입니다.');
		$('#newReplyText').focus().css("border-color","red");					
		return;
	}
	
	var data={	
			"bno":bno,
			"replyer":replyer,
			"replytext":replyText,
	}
	
	$.ajax({
		url:"<%=request.getContextPath()%>/replies",
		type:"post",
		data:JSON.stringify(data),	
		headers:{
			"Content-Type":"application/json",
			"X-HTTP-Method-Override":"post"
		},
		success:function(data){
			if(data="SUCCESS"){
				alert('댓글이 등록되었습니다.');
				getPage("<%=request.getContextPath()%>/replies/"+bno+"/"+lastPageNum);			
				$('#newReplyText').val("");
			}else{
				alert('댓글 등록이 취소되었습니다.');
			}	
		},
		error:function(error){
			alert('서버 오류로 인하여 댓글 등록을 실패했습니다.');
		}
	});
}


$("div#repliesDiv").on('click','#modifyReplyBtn',function(event){	
	var replyer=$(event.target).attr("data-replyer");
	if(replyer!="${loginUser.id}"){
		alert("수정이 불가합니다.");
		$(this).attr("data-toggle","");
	}
});
</script>










