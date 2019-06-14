package com.alex.handler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alex.entity.Student;
import com.sun.org.apache.xalan.internal.xsltc.trax.OutputSettings;
//@SessionAttributes(value="Student4")//如果要在request中存放student4对象，且同时将改对象放在session中的话则需要将对象名放入注解SessionAttributes。
//@SessionAttributes(types= {Student.class,Address.class})//也可以通过type属性将所有的类都放入到session域中。必须需要将student类放入request时才可以同时放入session
@Controller
@RequestMapping("SpringMVC")
public class SpringMVCHandler {
	@RequestMapping(value="welcome/**/test")//welcome和test之间可以加任何的目录多个子目录都可以
	
	public String welcome() { 
		return "success";
	}
	@RequestMapping(value="welcome2/*/test")////welcome和test之间可以加任何的字符
	public String welcome2() {
		return "success";
	}
	@RequestMapping(value="welcome3/a?c/test")// 与b之间可以添加任意一个字符，一个问号一个占位符
	public String welcome3() {
		return "success";
	}
	@RequestMapping(value="welcome4/{name}")//动态获取参数使用PathVariable
	public String welcome4(@PathVariable("name") String name) {
		System.out.println(name);
		return "success";
	}
	
	
	//通过filter和filter-mapping将post请求方式修改成delete和put方式，
		//并且当注解的名称相同时，通过请求的方式匹配，从而检验请求方式是否为put或者delete
	@RequestMapping(value="testRest/{id}",method=RequestMethod.POST)
	
	public String testPost(@PathVariable("id") Integer id) {
		System.out.println("post:增"+id);
		return "success";
	}
	@RequestMapping(value="testRest/{id}",method=RequestMethod.DELETE)
	public String welcome6(@PathVariable("id") Integer id) {
		System.out.println("post:删"+id);
		return "success";
	}
	@RequestMapping(value="testRest/{id}",method=RequestMethod.PUT)
	public String welcome7(@PathVariable("id") Integer id) {
		System.out.println("post:改"+id);
		return "success";
	}
	@RequestMapping(value="testRest/{id}",method=RequestMethod.GET)
	public String welcome8(@PathVariable("id") Integer id) {
		System.out.println("Get:查"+id);
		return "success";
	}
	
	
	//通过RequestParam注解传递参数， 通过注解的方式获取参数信息，相当于javaweb中的request.getParameter(),(获取表单数据)
	//可也可以用,隔开再注解获取第二个参数，若传递过来的数据不是必填的数据，以增加枚举项required=false，也可一设置defaultValue参数 设定默认值
	@RequestMapping(value="testParam")
	public String welcome8(@RequestParam("uname") String name,@RequestParam(value="uage",required=false,defaultValue="23") Integer age) {
		System.out.println(name);
		System.out.println(age);
		return "success";
	}
	
	//获取请求头信息
	@RequestMapping(value="testRequestHeader")
	public String  testRequestHeader(@RequestHeader("Accept-Language")  String AL) {
		System.out.println(AL);
		return "success";
	}
	
	//获取cookie信息
	@RequestMapping(value="testCookieValue")
	public String  testCookieValue(@CookieValue("JSESSIONID")  String jsessionid) {
		System.out.println(jsessionid);
		return "success";
	}
	
	//
	@RequestMapping(value="testObjectProperties")
	public String  testObjectProperties(Student student) {
		System.out.println(student);
		return "success";
	}
	//将HttpServletRequest和HttpServletResponse放在参数里面可以直接是servlet-api里面的方法
	@RequestMapping(value="testServletAPI")
	public String  testServletAPI(HttpServletRequest request,HttpServletResponse response) {
		System.out.println(request );
		return "success";
	}
	
	//通过ModelAndView同时传输model和view实现页面跳转还有传输实体类
	@RequestMapping(value="testModelAndView")
	public ModelAndView  testModelAndView() {
		ModelAndView mv = new ModelAndView(); 
		Student stu = new Student();
		stu.setName("ls");
		stu.setId(1);
		mv.setViewName("success");
		mv.addObject("Student1", stu);
		
		return mv;
	}
	
	//调试Model, Map, ModelMap.三种方法来实现ModelAndView的方式的效果,而且这些数据都放在request作用域里面使用的是请求转发的方式跳转
	@RequestMapping(value="testModelMap")
	public String  testModelMap(ModelMap mm) {
		Student stu = new Student();
		stu.setName("ls");
		stu.setId(2);
		mm.put("Student2", stu);
		return "success";
	}
	@RequestMapping(value="testModel")
	public String  testModel(Model m) {
		Student stu = new Student();
		stu.setName("ls");
		stu.setId(3);
		m.addAttribute("Student3", stu);
		return "success";
	}
	@RequestMapping(value="testMap")
	public String  testMap(Map<String,Object> map) {
		Student stu = new Student();
		stu.setName("ls");
		stu.setId(4);
		map.put("Student4", stu);
		System.out.println(1/0);
		return "success";
	}
	
	
//	 @ModelAttribute//在任何一次请求前，都会先执行@ModelAttribute的方法,并将map中值传给将student同名的参数作为形参的方法
	 
	
	//在该类的的各个方法被执行前都会回执行的设计师是基于一个思想：一个控制器Controller只做一个功能的思想
	public void queryStudentById(Map<String,Object> map) {
		Student student = new Student();
		student.setId(6);
		student.setName("zs");
		student.setAge(25);
		//map.put("student", student);//约定：map的key就是方法参数类型的首字母小写匹配
		map.put("stu", student);//如果首字母大小写不写相同的话，也可以在testModelAttribute()中加入@ModelAttribute注解将key值放入。也行
	} 
	
	//这里的Student student 是index.jsp传来的实体类参数，不是ModelAttribute中传递过来的Student参数，而ModelAttribute中的Student作为已经存在的
	//Student的实体类就是说相当于在testModelAttribute中开始先执行@ModelAttribute中的方法，实例化了一个student类，然后再用接收类中的值去修改student中的属性值。
	@RequestMapping(value="testModelAttribute")
	public String  testModelAttribute(@ModelAttribute("stu")Student student) {
		student.setName(student.getName());
		System.out.println(student);
		return "success";
	}
	
	@RequestMapping(value="testi18N")
	public String  testi18N() {
		
		return "success";
	}
	//如果想要通过重定向的方式跳转页面，则需要在return 字符串中加入redirect:/view/success.jsp，使用了redirect前缀之后会自动忽略视图解析器的前缀和后缀，所以
	//需要自己再加上前缀和后缀
	@RequestMapping(value="testRedirect")
	public String  testRedirect() {
		
		return "redirect:/view/success.jsp";
	}
	
	
	//测试自定义转换器converter
	@RequestMapping(value="testConverter")
	public String  testConverter(@RequestParam("StudentInfo") Student student) {
		System.out.println(student);
		return "success";
	}
	
	//测试DateTimeFormat
	//测试数据校验，@Pass，@Email导入jar，配springmvc.xml,在实体类属性前加上@注解，再返回到controller中，在在传入controller对应的参数上加入@Valid
	@RequestMapping(value="testDateTimeFormat")
	public String  testDateTimeFormat(@Valid Student student,BindingResult result,Map<String,Object> map) {
		/**
		 * 1.错误消息：public String testDateTimeFormat(Student student, BindingResult result ,Map<String,Object> map) {
			需要验证的数据是 Student中的birthday, SPringMVC要求 如果校验失败  则将错误信息 自动放入 该对象之后紧挨着的	BindingResult中。
			即Student student, BindingResult result之间 不能有其他参数。
		 * @param student
		 * @param result
		 * @param map
		 * @return
		 */
		
//		System.out.println(student+","+student.getEmail());
		if(result.getErrorCount()>0) {
			for(ObjectError error : result.getAllErrors()) {
				System.out.println(error);
				
				map.put("errors", result.getFieldError());
			}
			
		}
		return "success";
	}
	
	
//	//测试Json回传
		
		@RequestMapping(value="testJson")
		public List<Student>  testJson() {
			Student stu1 = new Student(1,"zs",23);
			Student stu2 = new Student(2,"ls",24);
			Student stu3 = new Student(3,"ww",25);
			List<Student> students = new ArrayList<Student>(); 
			students.add(stu1);
			students.add(stu2);
			students.add(stu3);
			
			return students;
		}
		
		

		//测试文件上传
	
	
		@RequestMapping(value="testUpload")
		
		public String  testUpload(@RequestParam("file") MultipartFile file,@RequestParam("desc" )String desc ) throws IOException {
			System.out.println("文件描述信息："+desc);
			InputStream input = file.getInputStream();
			String filename = file.getOriginalFilename();
			
		    OutputStream output = new FileOutputStream("d:\\"+filename);
		    
		    int len =-1;
		    byte[] bs = new byte[1024];
		    while((len = input.read(bs)) != -1) {
		    	output.write(bs, 0, len);
		    	
		    }
		    output.close();
		    input.close();
		    
		    
			
			return "success";
		}
	
	//拦截器测试
		@RequestMapping(value="testInterceptor")
		public String  testInterceptor() {
			System.out.println("处理拦截方法");
			return "success";
		}
		
}
