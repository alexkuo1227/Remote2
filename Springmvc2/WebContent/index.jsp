<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
 <script type="text/javascript" src="js/jquery-1.8.3.js"></script>
	<script type="text/javascript">
	
		$(document).ready(function(){
			$("#testJson").click(function(){
					//通过ajax请求springmvc
					$.post(
						"SpringMVC/testJson",//服务器地址
						//{"name":"zs","age":23}
						function(result){//服务端处理完毕后的回调函数 List<Student> students， 加上@ResponseBody后， students实质是一个json数组的格式
							for(var i=0;i<result.length ;i++){
								alert(result[i].id +"-"+result[i].name +"-"+result[i].age);
							}
						}
					);
				
				
			});
			
			
		});
	
	
	</script>
	    

</head>
<body>
<input type="button" value="testJson" id="testJson" />


	<!--  
<a href="/user/welcome.action">first springmvc</a>
<a href="/xxx/welcome.action">first springmvc</a>-->
<!-- ant形式 使用通配符*，**，和？其中一个问号代表一个占位符 -->
	<a href="SpringMVC/welcome/test/test/test">first springmvc</a>
	<br>
	<a href="SpringMVC/welcome2/abc/test">first springmvc</a>
	<br>
	<a href="SpringMVC/welcome3/axc/test">first springmvc</a>
	<br>
<!-- 动态获取参数 //zs为参数 -->
	<a href="SpringMVC/welcome4/zs">first springmvc</a>
	<br>
	<!-- rest形式  虽然testRest相同但是可以通过method方法约束匹配-->
	<form action="SpringMVC/testRest/123" method="POST">
		<input type="submit" value="增">

	</form>
	<!-- 通过filter和filter-Mapping 拦截器将POST方法转换为DELETE和PUT方法 -->
	<form action="SpringMVC/testRest/123" method="POST">
		<input type="hidden" name="_method" value="DELETE">
		<input type="submit" value="删">

	</form>
	<form action="SpringMVC/testRest/123" method="POST">
		<input type="hidden" name="_method" value="PUT">
		<input type="submit" value="改">
	</form>
	<form action="SpringMVC/testRest/123" method="GET">
		<input type="submit" value="查">

	</form>
	
	<!-- 通过注解的方式获取参数信息，相当于javaweb中的request.getParameter(),也可以用,隔开再注解获取第二个参数，若传递过来的数据不是必填的数据，
	可以增加枚举项required=false，也可一设置defaultValue参数 -->
	-----------testParam---------------<br/>
	<form action="SpringMVC/testParam" method="POST">
		<input name="uname" >
		<!--  <input name="uage">  -->
		<input type="submit" value="param">

	</form>
	<br/>
	<!-- 获取请求头信息 -->
		<a href="SpringMVC/testRequestHeader" >testRequestHeader</a>
	<!-- 获取cookie信息 -->
	<a href="SpringMVC/testCookieValue" >testCookieValue</a>
	<!-- 使用实体类接收参数，需要按照实体类的属性名定义表单参数（name="id"），而且在一个实体类中包括另外一个实体类的话可以使用级联的方式 address.schoolAddress
	连接且对应的属性名
	
	-->
	
	
	<form action="SpringMVC/testObjectProperties" method="POST">
		<input name="id" type="text">
		<input name="name" type="text">
		<input name="address.homeAddress" type="text">
		<input name="address.schoolAddress" type="text">
		
		
		<input type="submit" value="查">

	
	</form>
	<!-- 在mvc中直接使用原生态的servlet-api -->
	<a href="SpringMVC/testServletAPI" >testServletAPI</a>
	<br/>
	
	
	<!-- 通过ModelAndView同时传输视图view和实体类 -->
	<a href="SpringMVC/testModelAndView" >testModelAndView</a><br/>
	
	
	<!-- 通过ModelMap,Model,Maps实现ModelAndView 同时传输视图view和实体类 -->
	
	<a href="SpringMVC/testModelMap" >testModelMap</a><br/>6
	<a href="SpringMVC/testModel" >testModel</a><br/>
	<a href="SpringMVC/testMap" >testMap</a><br/>

	<!-- testModelAttribute -->
	 
	<form action="SpringMVC/testModelAttribute" method="POST">
		<input name="id" type="hidden" value="6">
		<input name="name" type="text">
		
		<input type="submit" value="查">
		
		</form>
		
	<!-- 国际化操作 -->
	<a href="SpringMVC/testi18N" >testi18N</a><br/>
	
	<!-- 通过mvcviewcontroller进行跳转，不需要进入controller而直接跳转页面 -->
	<a href="SpringMVC/testmvcviewcontroller" >testmvcviewcontroller</a><br/>
	 
	<!-- 使用请求转发和重定向的方式跳转， -->
	<a href="SpringMVC/testRedirect" >testRedirect</a><br/>
	
	<!-- 直接访问静态资源 -->
	<a href="123.png">teststaticresource</a><br/>
	<!-- 测试Converter,自定义转换器 -->
		-------testConverter----------<br />
		<form action="SpringMVC/testConverter" method="POST">
			<input name="StudentInfo" type="text"> 
			<input type="submit"value="post">
			
			</form><br/>
	<!-- 测试数据格式化 -->	<br/>
	-------testDateTimeFormat----------<br />
		<form action="SpringMVC/testDateTimeFormat" method="POST">
			id:<input name="id" type="text"> 
			名字：<input name="name" type="text"> 
			生日：<input name="birthday" type="text"> 
			邮箱：<input name="email" type="text">
			<input type="submit"value="post">
			
			</form><br/>	
	 		
	<!-- 处理文件上传 -->
	
	-------testUpload----------<br />
	
	<form action="SpringMVC/testUpload" method="post"  enctype="multipart/form-data">
		<input type="file" name="file" />
		描述:<input name="desc" type="text" />
		
		<input type="submit" value="上传">
	</form>

			
			
			
			<br/>	
	  -------testInterceptor----<br/>
	 
	
	<a href="SpringMVC/testInterceptor">testInterceptor</a>
	
	</body>
	
	
	<!-- 测试捕获异常 -->
		  -------testExceptionHandler----<br/>
	 
	<a href="Second/testExceptionHandler">testExceptionHandler</a>
	<a href="Second/testExceptionHandler2">testExceptionHandler2</a>
	  -------testMyException----<br/>
	  
	  <a href="Second/testMyException?i=3">testMyException</a>
	
</html>