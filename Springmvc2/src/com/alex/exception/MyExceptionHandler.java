package com.alex.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//@ControllerAdvice

//加上了ControllerAdvice之后能够处理所有类发生的异常，

public class MyExceptionHandler {
	@ExceptionHandler({Exception.class})
	public ModelAndView handlerException(Exception e) {
		System.out.println(e);
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("er", e);
		return mv;
	}
	
}
