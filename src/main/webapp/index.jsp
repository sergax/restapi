<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <div align="left">
  <h1>REST API</h1>
    <tr>
       <form action="<%=request.getContextPath()%>/file" method="get">
        <h3>ALL Files</h3>
           <td><input type="submit" value="File" /></td>
       <form action="<%=request.getContextPath()%>/event" method="get">
        <h3>ALL Events</h3>
           <td><input type="submit" value="Event" /></td>
       <form action="<%=request.getContextPath()%>/user" method="get">
       <h3>ALL Users</h3>
           <td><input type="submit" value="User" /></td>
     </tr>
 </div>
</body>
</html>