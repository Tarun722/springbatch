package com.example.demo;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class consolewriter implements ItemWriter<Employee>
{

	@Override
	public void write(List<? extends Employee> items) throws Exception {

System.out.println("......In Writer.....");
System.out.println("items.."+items);
		
	}

}
