<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <div align="center">
  <h1>REST API</h1>
    <tr>
       <form action="<%=request.getContextPath()%>/file" method="get">
          <input type="submit" value="File" />
       <form action="<%=request.getContextPath()%>/event" method="get">
                 <input type="submit" value="Event" />
       <form action="<%=request.getContextPath()%>/user" method="get">
                        <input type="submit" value="User" />
     </tr>
 </div>
</body>
</html>