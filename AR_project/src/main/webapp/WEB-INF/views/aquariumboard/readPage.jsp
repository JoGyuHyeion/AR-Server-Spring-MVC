<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
   request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>RoboPrint AR</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

    <!-- Bootstrap Core CSS -->
    <link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/vendor/bootstrap/css/bootstrap.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/resources/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

</head>

<body>
  <script>
     $(document)
           .ready(
                 function() {

                    var formObj = $("form[role='form']");

                    console.log(formObj);

                    $("#modifyBtn").on("click", function() {
                       formObj.attr("action", "./modifyPage");
                       formObj.attr("method", "get");
                       formObj.submit();
                    });

                    $("#removeBtn").on("click", function() {
                       formObj.attr("action", "./removePage");
                       formObj.submit();
                    });

                    $("#goListBtn")
                          .on(
                                "click",
                                function() {
                                	//alert("test");
                                   self.location = "./list?page=${cri.page}&perPageNum=${cri.perPageNum}";
                                });

                    $("#logout").on("click", function() {
                       self.location = "/user/logout";
                    });
                    $("#login").on("click", function() {
                       self.location = "/user/login?page=${cri.page}&perPageNum=${cri.perPageNum}&id_num=${aquariumBoardVO.id_num}";
                    });
                 });
     function fileName(file){
    	 var fileName = file.substring(file.lastIndexOf("_") + 1);
    	 return fileName
     }
  </script>
  <form role="form" method="post">
     <input type='hidden' name='id_num' value="${aquariumBoardVO.id_num}">
     <input type='hidden' name='page' value="${cri.page}"> <input
        type='hidden' name='perPageNum' value="${cri.perPageNum}"> <input
        type='hidden' name='searchType' value="${cri.searchType}"> <input
        type='hidden' name='keyword' value="${cri.keyword}">
  </form>
         <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
                <a class="navbar-brand" href="/">RoboPrint AR</a>
                <!-- 제목 -->

            </div>
            <!-- /.navbar-header -->
        </nav>
            <div class="container">
         <div class="page-header">
             <h1>${aquariumBoardVO.title}<small>Aquarium</small></h1>
        </div>
                                 <c:choose>
          					 <c:when test="${login.id != null}">
				            	  <div class="form-group " align="right">
				            	     <span class="detail">"${login.id}" 님이 로그인 하였습니다.</span>
				                 <button class="btn btn-primary" id="logout" type="submit">로그아웃</button>
				              </div>
				           </c:when>
				           <c:otherwise>
				              <div class="form-group" align="right">
				                 <button class= "btn btn-primary" id="login" type="submit">로그인</button>
				              </div>
				           </c:otherwise>
				        </c:choose>
            <!-- 좌표 -->
            <div class='form-group'>
              <label><h4>좌표<small>(Location)</small></h4></label>
              <div class='form-inline'>
                <input type='text' class='form-control' name='Lat' placeholder="${aquariumBoardVO.latitude}" disabled>
                <input type='text' class='form-control' name='Lon' placeholder="${aquariumBoardVO.longitude}" disabled>
              </div>
            </div>
            <!-- Marker DataSet -->
            <!-- .xml.dat파일만 볼수있게 accect -->
             <label><h4>Marker Dataset<small>(Vuforia)</small></h4></label>
            <div class='form-group'>
              <label>Xml </label>
              <div class="form-inline" style="display: inline">
                  <input type = 'text' id="xmlName" class=" form-control" placeholder="${aquariumBoardVO.xml}" disabled />
                  
              </div>
              <label>Dat </label>
              <div class="form-inline" style="display: inline">
                  <input  type = 'text' id="datName" class=" form-control " placeholder="${aquariumBoardVO.dat}" disabled />
              </div>
            </div>

            <div id="button" class="form-group-sm">
              <c:if test="${login.id != null}">
                <button class="btn btn-info" style="display: inline" id ="modifyBtn" type="submit">수정</button>
                        <input id ="removeBtn" class="btn btn-default" style="display: inline" type="button" value="삭제" type="submit"/>
                  </c:if>
               <button class="btn btn-success" style="display: inline" id="goListBtn" type="submit">목록보기</button>
               
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