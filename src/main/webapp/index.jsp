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
        <h1>ALL Files</h1>
           <td><input type="submit" value="File" /></td>
       <form action="<%=request.getContextPath()%>/event" method="get">
        <h1>ALL Events</h1>
           <td><input type="submit" value="Event" /></td>
       <form action="<%=request.getContextPath()%>/user" method="get">
       <h1>ALL Users</h1>
           <td><input type="submit" value="User" /></td>
     </tr>
 </div>
</body>
</html>