package com.example.demo;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.item.file.FlatFileParseException;

public class ErrorListener
{
	
	
	@OnSkipInRead
	public void m1(Throwable t)
	{
		
		System.out.println("in inside listener");
		FlatFileParseException t1 = (FlatFileParseException)t;
		
		skip(t1);
	}
	
	
	public void skip(FlatFileParseException t)
	{
		try
		{
			System.out.println("Inside skip");
			File  a = new File("errorfile.txt");
			a.createNewFile();
		FileOutputStream fs = new FileOutputStream(a, true);
		fs.write(t.getInput().getBytes());
		fs.write("\r\n".toString().getBytes());
		fs.close();
		
		
		
		
		
		}
		catch(Exception e)
		{
			System.out.println("caught exception"+e);
		}
		
	}
	
	

}
