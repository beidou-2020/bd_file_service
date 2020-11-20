<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>北斗重生</title>
  <c:set var="beidou" value="${pageContext.request.contextPath}" />
</head>
<body>

<h2>文件回显测试</h2>
<form action='${beidou}/file/fileEcho' method='post' enctype='multipart/form-data'>
  <input type='file' name='uploadFile'>
  <button type='submit'>上传</button>
</form>

<h2>多文件上传</h2>
<form action='${beidou}/file/uploadFiles' method='post' enctype='multipart/form-data'>
  <input type='file' name='uploadFiles' multiple="multiple">
  <button type='submit'>上传</button>
</form>

<h2>单文件上传</h2>
<form action='${beidou}/file/uploadFile' method='post' enctype='multipart/form-data'>
  <input type='file' name='uploadFile'>
  <button type='submit'>上传</button>
</form>

</body>
</html>