package com.alex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FORBIDDEN,reason="数据越界222222")
public class MyArrayIndexOutofBoundsException extends Exception {
	
}
