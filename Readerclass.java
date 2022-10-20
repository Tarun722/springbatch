package com.example.demo;

import java.io.File;
import java.util.Arrays;

import javax.batch.api.chunk.AbstractItemReader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.core.io.FileSystemResource;

public class Readerclass extends FlatFileItemReader<Employee>
{

	
	{
	FlatFileItemReader fileReader = new FlatFileItemReader<Employee>();
	
	System.out.println("inside reader/...");
	try
	{
	fileReader.setResource(new FileSystemResource(new File("/input/test.csv")));
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	
	fileReader.setLineMapper(new DefaultLineMapper(){
		
		{
		
		
			setLineTokenizer(new DelimitedLineTokenizer(){
					{
				
			            setNames(new String[] {"EmployeeId","EmployeeName","EmployeeSalary","EmployeeStatus"});
					}
				});
			
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Employee>(){
						
						{
					        setTargetType(Employee.class);
					
						}
						
						
						
						
				});	
					
		}	
		
	});
		
		
		
		
	
			
			
			
			
			
			
			
			
	}

}
