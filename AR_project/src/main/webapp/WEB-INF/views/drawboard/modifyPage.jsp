<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.parser.JSONParser"%>


<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<title>modifyPage</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->


<!-- 부트스트랩 -->
<link href="/resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/resources/bootstrap/css/file_input.css" rel="stylesheet">

<style media="screen">
.container {
	margin-top: 20px;
	padding: 20px;
}

.form-group {
	margin-top: 10px;
}

.file {
	visibility: hidden;
	position: absolute;
}
</style>
</head>
<body>

	<script>
      $(document).ready(function() {

         var formObj = $("form[role='form']");

         console.log(formObj);


         $("#confirm").on("click", function() {
            //alert('${drawBoardVO.id_num}');
            formObj.submit();
         });
         $("#goListBtn").on("click", function() {
            self.location = "/drawboard/list?page=${cri.page}&perPageNum=${cri.perPageNum}";
         });

      });
   </script>

	<div class="container">
		<form role="form" action="modifyPage" method="post"
			enctype="multipart/form-data">
			<input type='hidden' name='page' value="${cri.page}"> <input
				type='hidden' name='perPageNum' value="${cri.perPageNum}"> <input
				type="hidden" name="id_num" value="${drawBoardVO.id_num}">

			<div class="page-header">
				<h1>
					Draw수정<small>PictureMarker</small>
				</h1>
			</div>
			<div class='form-group'>
				<label><h4>
						장소<small>(Location Name)</small>
					</h4></label> <input type='text' class='form-control' name='title'
					placeholder='title' value="${drawBoardVO.title}">
			</div>
			<!-- 좌표 -->
			<div class='form-group'>
				<label><h4>
						좌표<small>(Location)</small>
					</h4></label>
				<div class='form-inline'>
					<input type='text' class='form-control' name='latitude'
						placeholder='위도' value="${drawBoardVO.latitude}"
						onkeydown='onlyNumDecimalInput();'> <input type='text'
						class='form-control' name='longitude' placeholder='경도'
						value="${drawBoardVO.longitude}"
						onkeydown='onlyNumDecimalInput();'>
				</div>
			</div>

			<script type='text/javascript'>
                            function onlyNumDecimalInput() {
                                var code = window.event.keyCode;
                                if ((code >= 48 && code <= 57) || (code >= 96 && code <= 105) || code == 110 || code == 190 || code == 8 || code == 9 || code == 13 || code == 46) {
                                    window.event.returnValue = true;
                                    return;
                                }
                                window.event.returnValue = false;
                                alert("숫자 및 소수점만 입력가능");
                            }
        </script>
			<!-- Marker DataSet -->
			<!-- .xml.dat파일만 볼수있게 accect -->
			<div class='form-group'>
				<label><h4>
						Marker Dataset<small>(Vuforia)</small>
					</h4></label> <br>
				<div class="form-inline" style='float: left'>
					<label>Xml </label>
					<div class="form-group-sm">
						<input id="xmlName" class="file_input_textbox form-control"
							readonly value="${drawBoardVO.xml}" />
						<div class="file_input_div">
							<input type="button" value="XML file"
								class="file_input_button btn btn-primary btn-sm" /> <input
								type="file" class="file_input_hidden" name='file'
								data-icon='false'
								onchange="javascript:var path = document.getElementById('xmlName').value = this.value.split('\\').pop().split('/').pop()"
								accept=".xml" />
						</div>
					</div>

				</div>
				<div class="form-inline">
					<label>Dat </label>
					<div class="form-group-sm">
						<input id="datName" class="file_input_textbox form-control"
							readonly value="${drawBoardVO.dat}" />
						<div class="file_input_div">
							<input type="button" value="DAT file"
								class="file_input_button btn btn-primary btn-sm" /> <input
								type="file" class="file_input_hidden" name='file'
								data-icon='false'
								onchange="javascript:var path = document.getElementById('datName').value = this.value.split('\\').pop().split('/').pop()"
								accept=".dat" />
						</div>
					</div>
				</div>

				<p class='help-block'>Vuforia Dataset 파일 압축 해체후</p>
			</div>
			<div class='form-group'>
				<label><h4>
						Picture<small> (최대 10개)</small>
					</h4></label>

				<table id="tb_list">

				</table>
			</div>

			<script type="text/javascript">
						   function add_item(){
							   var table = document.getElementById("tb_list");
								var row = table.rows.length;
							       if (row > 9)
			                            return alert("10개이상 안됨");
			                        false;
								var currentRow = table.insertRow(-1);
								var cell = currentRow.insertCell();
		                        cell.innerHTML = "<input id='pic"+ row +"' type = 'text' class ='form-control' readonly/>"; 
		                        var cell = currentRow.insertCell();
		                        cell.innerHTML = "<input id='btn' type ='button' value='Picture file' class='btn btn-danger btn-sm'  onClick = 'BtnClick("+row+")'/><input id = 'file"+row+"' type='file' class='file_input_hidden' name='img' onChange='ChangeText(this, "+ row +")' accept='image/*' />";         	

		                  			}
						   function remove_item(){
					             var table = document.getElementById('tb_list') ;
					                if(table.rows.length >1 )
					                table.deleteRow(table.rows.length-1);
					           
					            }
					  
						</script>

			<div style="float: right">
				<input type='button' value='+' class='btn btn-primary btn-sm'
					style='margin-top: 4px' onclick='add_item()' /> <input
					type='button' value='-' class='btn btn-danger btn-sm'
					style='margin-top: 4px' onclick='remove_item()' />
			</div>


			<!--pinmarker jstl처리 -->


			<c:forEach var="picture" items="${picName}" varStatus="status">
				<script>
				add_item();
				document.getElementById('pic' + '<c:out value="${status.index}"/>').placeholder = '<c:out value="${picture}"/>';
			</script>
			</c:forEach>

		</form>

		<div class="form-group">
			<br> <input id="confirm" class='btn btn-info' type="submit"
				value="수정"> <input type="reset" class='btn btn-warning'
				value="다시 작성" /> <input id="goListBtn" type="button"
				class='btn btn-success' value="목록으로" />
		</div>

	</div>
	<script type="text/javascript">
            		function BtnClick(row){
            			document.getElementById('file'+row).click()
            		}
            		
            		function ChangeText(oFileInput, sTargetID) {
            			   
            		    document.getElementById('pic'+sTargetID).value = oFileInput.value.split('\\').pop().split('/').pop();
            		}
            	</script>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script src="/resources/vendor/jquery/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="/resources/vendor/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="/resources/dist/js/sb-admin-2.js"></script>

</body>

</html>