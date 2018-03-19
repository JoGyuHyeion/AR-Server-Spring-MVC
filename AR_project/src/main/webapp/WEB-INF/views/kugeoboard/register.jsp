<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title></title>

<!-- 부트스트랩 -->
<link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/bootstrap/css/file_input.css" rel="stylesheet">

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<style media="screen">
.container {
  margin-top: 20px;
  padding: 20px;
}
.form-group {
  margin-top: 10px;
}
td {
  padding:2px;
}
</style>

</head>
<body>
   <script>
      $(document).ready(function() {
                     var formObj = $("form[role='form']");

                     console.log(formObj);

                     $("#goListBtn").on("click",function() {
                        //alert("test");
                        self.location = "/kugeoboard/list?page=${cri.page}&perPageNum=${cri.perPageNum}";
                     });
                  });
   </script>
   <div class="container">
     <div class="page-header">

        <h1>KudanGeo<small>PinMarker</small></h1>
    </div>
      <form role="form" method="post" enctype="multipart/form-data">

         <div class="form-group">
            <label>장소(Location)</label> <input type="text" class="form-control" id ="title" name="title" placeholder="title">
         </div>
         <!-- 좌표 -->
         <div class='form-group'>
           <label><h4>좌표<small>(Location)</small></h4></label>
           <div class='form-inline'>
             <input type='text' class='form-control' name='latitude' placeholder='위도' id ='Lat'>
             <input type='text' class='form-control' name='longitude' placeholder='경도' id='lon'>
           </div>
         </div>

         <!-- Marker DataSet -->
         <!-- .xml.dat파일만 볼수있게 accect -->
         <div class='form-group'>
           <label><h4>Marker Dataset<small>(Vuforia)</small></h4></label>
           <br>
           <div class="form-group">
           <label>karmarker </label>
           <div class="form-group-sm">
               <input id="xmlName" class="file_input_textbox form-control" readonly />
           <div class="file_input_div">
               <input type="button" value="Karmarker file" class="file_input_button btn btn-primary btn-sm"/>
             <input type="file" class="file_input_hidden" name="file" data-icon='false'
                    onchange="javascript:var path =document.getElementById('xmlName').value = this.value.split('\\').pop().split('/').pop()" accept=".karmarker" id ="karmarker"/>
             </div>
           </div>

           </div>
           

         </div>

       <script type='text/javascript'>
    		function add_item(){
                // pre_set 에 있는 내용을 읽어와서 처리..
                var table = document.getElementById("tb_list");
                var row = table.rows.length;
                if(row > 9) return alert("10개이상 안됨");false;
                var currentRow = table.insertRow(-1);
                var cell = currentRow.insertCell();
                cell.innerHTML = "<input type='text' class='form-control' name='pName' id='pName"+row+"' value='' placeholder='이름'>";
                cell = currentRow.insertCell();
                cell.innerHTML ="<input type='text' class='form-control' name='pLat' id='pLat"+row+"' value='' placeholder='위도'>";
                cell = currentRow.insertCell();
                cell.innerHTML ="<input type='text' class='form-control' name='pLon' id='pLon"+row+"' value='' placeholder='경도'>";
                  }
            function remove_item(){
             var table = document.getElementById('tb_list') ;
                if(table.rows.length >1 )
                table.deleteRow(table.rows.length-1);
                  }
            function check(){
        		var title = document.getElementById('title').value;
        		var lat = document.getElementById('lat').value;
        		var lon = document.getElementById('lon').value;
        		var karmarker = document.getElementById('karmarker').value;
        		
        		var table = document.getElementById("tb_list");
                var row = table.rows.length;
      					
                if(title == ""||lat == ""||lon ==""||karmarker == ""){
        			alert("null 값 존재");
        			return false;
        		}
                if(isFinite(lat) != true || isFinite(lon) != true ){
                	alert("좌표 값 이상");
        			return false;
                }
                 
                for(i = 0; i <= row-1; i++){
					if(document.getElementById("pName"+i).value == ""){
						alert("PinMarker null 값 존재");
						return false;
					}
					if(isFinite(document.getElementById("pLat"+i).value) != true){
						alert("PinMarker 좌표 값 이상");
						return false;
					} 
					if(isFinite(document.getElementById("pLon"+i).value) != true){
						alert("PinMarker 좌표 값 이상");
						return false;
					} 
				}
                return true;				
         }
            
            </script>
					<div class='form-group'>
                        <label><h4>PinMarker<small> (최대 10개)</small></h4></label>

                          <table id = "tb_list">
                          <tr>
                            <td><input type='text' class='form-control' name='pName' value='' placeholder='이름' id='pName0'></td>
                            <td><input type='text' class='form-control' name='pLat' value='' placeholder='위도' id='pLat0'></td>
                            <td><input type='text' class='form-control' name='pLon' value='' placeholder='경도' id='pLon0'></td>
                          </tr>
                          </table>
                          <div style="float: right">
                            <input type='button' value='+' class='btn btn-primary btn-sm' style='margin-top: 4px' onclick='add_item()'>
                            <input type='button' value='-' class='btn btn-danger btn-sm' style='margin-top: 4px' onclick='remove_item()'>

                          </div>

					</br>
				

                  <button type='submit' class='btn btn-info' onclick="return check()">Submit</button>
                  <input id = "goListBtn" type="button" class='btn btn-success' value="목록으로" />
                  
                </form>
       </body>


     </html>