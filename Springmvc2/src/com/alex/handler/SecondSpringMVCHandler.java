package com.alex.handler;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.alex.exception.MyArrayIndexOutofBoundsException;

@Controller
@RequestMapping("Second")
//1231235+5555
//helloword
public class SecondSpringMVCHandler  {
	
	@RequestMapping("testExceptionHandler")
	public String testExceptionHandler() {
		 System.out.println(1/0);
		 
		 return "error";
		 
	}
		
	@RequestMapping("testExceptionHandler2")
	public String testExceptionHandler2() {
		int [] arr = new int[2]; 
		
		System.out.println(arr[2]);
		 
		 return "error";
		 
	}
	
	@ExceptionHandler({ArithmeticException.class})
	public ModelAndView handlerArithmeticException(Exception e) {
		System.out.println(e);
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("er", e);
		return mv;
	

		
		
	}
	
//	@ExceptionHandler({ArrayIndexOutOfBoundsException.class})
//	public ModelAndView handlerArrayIndexOutOfBoundsException(Exception e) {
//		System.out.println(e);
//		ModelAndView mv = new ModelAndView("error");
//		mv.addObject("er", e);
//		return mv;
//	}
	
	
	/**
	 * 如果不写具体异常的类文件的话，可以直接写Exception.class 
	 * 。但如果你把具体异常的实现类写出来了又把Exception.class写了的话，他会先去找具体的实现类的异常，
	 * 找不到的再找Exception.class的实现类
	 * 异常处理路径：最短优先
	 * 则优先级：最短路径优先
	 * 这些处理的异常只能处理本类的异常
	 * @param e 
	 * @return
	 */
//	@ExceptionHandler({Exception.class})
//	public ModelAndView handlerException(Exception e) {
//		System.out.println(e);
//		ModelAndView mv = new ModelAndView("error");
//		mv.addObject("er", e);
//		return mv;
//	}
	
	/*
	 * 处理自定义异常
	 */

	@RequestMapping("testMyException")
	public String testMyException(@RequestParam("i") Integer i) throws MyArrayIndexOutofBoundsException {
		if(i==3) {
			throw new MyArrayIndexOutofBoundsException();
		}
		 
		 return "error";
		 
	}
	

	@RequestMapping("testMyException2")
	public String testMyException2(@RequestParam("i") Integer i) {
		if(i==3) {
			return "redirect:/Second/testResponseStatus";
		}
		 
		 return "error";
		 
	}
	@ResponseStatus(value=HttpStatus.BAD_GATEWAY,reason="123123")
	@RequestMapping("testResponseStatus")
	public String testResponseStatus() throws MyArrayIndexOutofBoundsException {
		
		 
		 return "error";
	}	 
	
}
