<%@ page language="java" contentType="text/html; charset=UTF-8"%>

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
<link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
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
            //alert('${aquariumBoardVO.id_num}');
            formObj.submit();
         });
         $("#goListBtn").on("click", function() {
            self.location = "/aquariumboard/list?page=${cri.page}&perPageNum=${cri.perPageNum}";
         });

      });
   </script>

   <div class="container">
      <form role="form" action="modifyPage" method="post" enctype="multipart/form-data">
         <input type='hidden' name='page' value="${cri.page}">
         <input type='hidden' name='perPageNum' value="${cri.perPageNum}">
         <input type="hidden" name="id_num" value="${aquariumBoardVO.id_num}">

         <div class="page-header">
                   <h1>Aquarium수정<small>Marker</small></h1>
        </div>
         <div class='form-group'>
            <label><h4>장소<small>(Location Name)</small></h4></label>
            <input type='text' class='form-control' name='title' id ="title" placeholder='title' value="${aquariumBoardVO.title}" >
          </div>
          <!-- 좌표 -->
          <div class='form-group'>
              <label><h4>좌표<small>(Location)</small></h4></label>
              <div class='form-inline'>
                  <input type='text' class='form-control' name='latitude' placeholder='위도' value="${aquariumBoardVO.latitude}"  id ="lat">
                  <input type='text' class='form-control' name='longitude' placeholder='경도' value="${aquariumBoardVO.longitude}" id = "lon">
              </div>
          </div>

         <script type='text/javascript'>

                            function check(){
                        		var title = document.getElementById('title').value;
                        		var lat = document.getElementById('lat').value;
                        		var lon = document.getElementById('lon').value;
                        		var xml = document.getElementById('xml').value;
                        		var dat = document.getElementById('dat').value;
                				
                        		if(title == ""||lat == ""||lon ==""){
                        			alert("null 값 존재");
                        			return false;
                        		}else{
                        			return true;
                        		}			
                         }
        </script>
        <!-- Marker DataSet -->
                        <!-- .xml.dat파일만 볼수있게 accect -->
                        <div class='form-group'>
                            <label><h4>Marker Dataset<small>(Vuforia)</small></h4></label>
                            <br>
                            <div class="form-inline" style='float: left'>
                                <label>Xml </label>
                                <div class="form-group-sm">
                                    <input id="xmlName" class="file_input_textbox form-control" readonly  value="${aquariumBoardVO.xml}"/>
                                    <div class="file_input_div">
                                        <input type="button" value="XML file" class="file_input_button btn btn-primary btn-sm" />
                                        <input type="file" class="file_input_hidden" name='file' data-icon='false'
                                               onchange="javascript:var path = document.getElementById('xmlName').value = this.value.split('\\').pop().split('/').pop()" accept=".xml" id = "xml"/>
                                    </div>
                                </div>

                            </div>
                            <div class="form-inline" >
                                <label>Dat </label>
                                <div class="form-group-sm">
                                    <input id="datName" class="file_input_textbox form-control" readonly value="${aquariumBoardVO.dat}" />
                                    <div class="file_input_div">
                                        <input type="button" value="DAT file" class="file_input_button btn btn-primary btn-sm" />
                                        <input type="file" class="file_input_hidden" name='file' data-icon='false'
                                               onchange="javascript:var path = document.getElementById('datName').value = this.value.split('\\').pop().split('/').pop()" accept=".dat" id = "dat" />
                                    </div>
                                </div>
                            </div>

                            <p class='help-block'>Vuforia Dataset 파일 압축 해체후</p>
                        </div>


      </form>

         <div class="form-group">
                      <br>
                      <input id="confirm" class='btn btn-info' type="submit" value="수정" onclick="return check()">
                      <input type="reset" class='btn btn-warning'  value="다시 작성"/>
                      <input id = "goListBtn" type="button" class='btn btn-success' value="목록으로" />
          </div>
   </div>
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