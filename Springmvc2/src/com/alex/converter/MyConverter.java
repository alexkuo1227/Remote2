package com.alex.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import com.alex.entity.Student;

public class MyConverter implements Converter<String, Student> {

	@Override
	public Student convert(String source) {
		String[] StudentArr = source.split("-");
		Student student = new Student();
		student.setId(Integer.parseInt(StudentArr[0]));
		student.setName(StudentArr[1]);
		student.setAge(Integer.parseInt(StudentArr[2]));
		return student;
	}

}
