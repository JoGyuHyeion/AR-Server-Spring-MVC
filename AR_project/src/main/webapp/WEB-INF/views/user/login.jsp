<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>AdminLTE 2 | Log in</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.4 -->
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="/resources/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- iCheck -->
    <link href="/resources/plugins/iCheck/square/blue.css" rel="stylesheet" type="text/css" />
        
        <style media="screen">

            body {
                background-image: url("/resources/background.jpg");
                padding-top: 100px;
                background-color: #eee;
            }
            .form-signin {
                background-color: #eee;
                background: rgba(255, 255, 255, 0.5);
                max-width: 330px;
                padding: 15px;
                margin: 0 auto;
                border-radius: 10px;

            }
            input {
                margin-top:5px;
            }
            button {
                margin-top:5px;
            }
     
        </style>


  </head>
  <body >

        <div class="container">

            <form class="form-signin"action="./loginPost" method="post">
            	<input type='hidden' name='id_num' value="${param.id_num}">
				<input type='hidden' name='page' value="${param.page}">
				<input type='hidden' name='perPageNum' value="${param.perPageNum}">
		
				<center style="margin-top: 30px; margin-bottom: 30px"><img src ="/resources/logo.png"/></center>
			
				<label for="inputEmail" class="sr-only">ID</label>
                <input type="ID" name="id" class="form-control" placeholder="ID" required autofocus>
                <label  class="sr-only">Password</label>
                <input type="password" name="password" class="form-control" placeholder="Password" required readonly onfocus="this.removeAttribute('readonly');">
                 <div class="checkbox icheck">
        		<label>
          		<input type="checkbox" name="useCookie"> Remember Me
        		</label>
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            </form>

        </div>
  

    <!-- jQuery 2.1.4 -->
    <script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- iCheck -->
    <script src="/resources/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
    <script>
      $(function () {
        $('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
      });
    </script>
  </body>
</html>