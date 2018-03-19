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
                    $("#logout").on("click", function() {
                       self.location = "/user/logout";
                    });
                    $("#login").on("click", function() {
                       self.location = "/user/login?page=1&perPageNum=10&id_num=1";
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
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Home</h1>
                           <div class="row">
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
				        </div>
                    </div>
                 
				        
                      <div class="row">
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-table fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${count.aquarium}</div> 
                                    <div>Aquarium</div>
                                </div>
                            </div>
                        </div>
                        <a href="/aquariumboard/list">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-table fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${count.draw}</div>
                                    <div>Draw</div>
                                </div>
                            </div>
                        </div>
                        <a href="/drawboard/list">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-table fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${count.geo}</div>
                                    <div>Geo</div>
                                </div>
                            </div>
                        </div>
                        <a href="/geoboard/list">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-red">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-table fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${count.sign}</div>
                                    <div>Sign</div>
                                </div>
                            </div>
                        </div>
                        <a href="/signboard/list">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
				<div class="col-lg-3 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-table fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${count.kuAquarium}</div> 
                                    <div>KuAquarium</div>
                                </div>
                            </div>
                        </div>
                        <a href="/kuaquariumboard/list">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
				<div class="col-lg-3 col-md-6">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-table fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${count.kuGeo}</div> 
                                    <div>KudanGeo</div>
                                </div>
                            </div>
                        </div>
                        <a href="/kugeoboard/list">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
				<div class="col-lg-3 col-md-6">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-table fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${count.kuSign}</div> 
                                    <div>KudanSign</div>
                                </div>
                            </div>
                        </div>
                        <a href="/kusignboard/list">
                            <div class="panel-footer">
                                <span class="pull-left">View Details</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>


            </div>
            <!-- /.row -->


                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->

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