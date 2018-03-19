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
      var result = '${msg}';

      if (result != "") {
         alert(result);
      }
      $(document).ready(function() {

               var formObj = $("form[role='form']");

               console.log(formObj);

               $("#btn-register").on("click", function() {
                  self.location = "/kusignboard/register${pageMaker.makeSearch(cri.page)}";
               });

               $('#searchBtn').on(
                     "click",
                     function(event) {
                        self.location = "list"
                              + '${pageMaker.makeQuery(1)}'
                              + "&searchType="
                              + $("select option:selected").val()
                              + "&keyword="
                              + $('#keywordInput').val();

                     });
            });
   </script>
   <div id="wrapper">

     <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">RoboPrint AR</a>
                <!-- 제목 -->
            </div>
            <!-- /.navbar-header -->


            <div class="navbar-default sidebar" role="navigation">
                                 <div class="sidebar-nav navbar-collapse">
                       <ul class="nav" id="side-menu">

	   				 <li>
	                      <a href="/"><i class="fa fa-dashboard fa-fw"></i> Home</a>
	                  </li>
	
	 				<li>
	 				
	                   <b> <center><i class="fa fa-th-list fa-fw"></i> Vuforia </center> </b>
	              	</li>
	                  <li>
	                      <a href="/aquariumboard/list"><i class="fa fa-list fa-fw"></i> Aquarium</a>
	                  
	                  </li>
	
	                  <li>
	                      <a href="/drawboard/list"><i class="fa fa-list fa-fw"></i> Draw</a>
	                </li>
	                   <li>
	                	<a href="/geoboard/list"><i class="fa fa-list fa-fw"></i> Geo</a>
	                  
	                </li>
	                 <li>
	                     <a href="/signboard/list"><i class="fa fa-list fa-fw"></i> Sign</a>
	                </li>
	             
	              <li>
	                   <b> <center><i class="fa fa-th-list fa-fw"></i> Kudan </center> </b>
	              	</li>
	
	    			<li>
	                    <a href="/kuaquariumboard/list"><i class="fa fa-list fa-fw"></i> Aquarium</a>
	              	</li>
	                  <li>
	                    <a href="/kugeoboard/list"><i class="fa fa-list fa-fw"></i> Geo</a>
	                </li> 
	                 <li>
	                    <a href="/kusignboard/list"><i class="fa fa-list fa-fw"></i>  Sign</a>
	              </li>
	              </ul>
          </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

  <!-- Page Content -->
  <div id="page-wrapper">

      <div class="container-fluid">
        <div class="col-lg-12">
              <h1 class="page-header">KudanSign</h1>
               </div>
               <div class="row">

                   <div class="col-lg-12">
                  <div class="row">
                               </div>
                       <div class="panel panel-default">

                           <!-- /.panel-heading -->
                           <div class="panel-body">


                                   <table width="100%"  class="table table-striped table-bordered table-hover">
                                      <thead>
                                        <tr>
                                             <th><center><b>Num</b></center></th>
                                             <th><center><b>Title</b></center></th>
                                             <th><center><b>Latitude</b></center></th>
                                             <th><center><b>Longitude</b></center></th>
                                             <th><center><b>karmarker</b></center></th>
                                             <th><center><b>Regdate</b></center></th>
                                         </tr>
                                   </thead>

         <c:forEach items="${list}" var="kuSignBoardVO">
            <tbody>
               <tr align="center">
                  <td>${kuSignBoardVO.id_num}</td>
                  <td><a
                     href='/kusignboard/readPage${pageMaker.makeQuery(pageMaker.cri.page) }&id_num=${kuSignBoardVO.id_num}'>
                        ${kuSignBoardVO.title}</a></td>
                  <td>${kuSignBoardVO.latitude}</td>
                  <td>${kuSignBoardVO.longitude}</td>
                  <td>${kuSignBoardVO.karmarker}</td>
                  <td>${kuSignBoardVO.regdate}</td>
               </tr>
            </tbody>

         </c:forEach>
      </table>
      <table class="ui celled table">

         <tfoot style="text-align: center;">
            <tr>
               <th colspan="3">
                  <div class="ui pagination menu">
                     <c:if test="${pageMaker.prev}">
                        <a class="icon item"
                           href="/kusignboard/list${pageMaker.makeSearch(startPage - 1)}">&laquo;</a>
                     </c:if>

                     <c:forEach begin="${pageMaker.startPage }"
                        end="${pageMaker.endPage }" var="idx">
                        <div
                           <c:out value="${pageMaker.cri.page == idx? 'class = active':''}" />>
                           <a class="icon item"
                              href="/kusignboard/list${pageMaker.makeSearch(idx)}">${idx}</a>
                        </div>
                     </c:forEach>

                     <c:if test="${pageMaker.next && pageMaker.endPage > 0}">
                        <a class="icon item"
                           href="/kusignboard/list${pageMaker.makeSearch(pageMaker.endPage +1)}">&raquo;</a>
                     </c:if>
                  </div>
               </th>
            </tr>
         </tfoot>
      </table>
      <TABLE>keywordInput
         <TR>
            <TD >
               <TABLE>
                  <div class="form-group-sm">
                    <input type='text'  placeholder="Search..." class="form-control" id ="keywordInput" size='20' maxlength='30' style ="width: 200px; display: inline">
                    <select name="searchType" class="form-control" style ="width: 100px; display: inline">
                        <option value="n"
                           <c:out value="${cri.searchType == null?'selected':''}"/>>
                           ---</option>
                        <option value="t"
                           <c:out value="${cri.searchType eq 't'?'selected':''}"/>>
                           Title</option>
                        <option value="c"
                           <c:out value="${cri.searchType eq 'c'?'selected':''}"/>>
                           Content</option>
                        <%--       <option value="w"
                                 <c:out value="${cri.searchType eq 'w'?'selected':''}"/>>
                                 Writer</option> --%>
                        <option value="tc"
                           <c:out value="${cri.searchType eq 'tc'?'selected':''}"/>>
                           Title OR Content</option>
                        <%--       <option value="cw"
                                 <c:out value="${cri.searchType eq 'cw'?'selected':''}"/>>
                                 Content OR Writer</option>
                              <option value="tcw"
                                 <c:out value="${cri.searchType eq 'tcw'?'selected':''}"/>>
                                 Title OR Content OR Writer</option> --%>
                     </select>


                  </div>
               </TABLE>
            </TD>

            <TD class ="form-group-sm" >
               <button id='searchBtn' type="submit" class="btn btn-info">Search</button>
               <button id="btn-register" class='btn btn-primary' style='display: inline' type="submit">등록</button>
            </TD>



         </TR>
      </TABLE>

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