<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>

  <script type="text/javascript">
  alert("id 혹은 password가 일치 하지 않습니다.");
    self.location = "./login?&id_num=${param.id_num}&page=${param.page}&perPageNum=${param.perPageNum}";
   //해당 경로로 이동하는 것은 LoginInterceptor에서 처리해준다  
  </script>
</body>
</html>

