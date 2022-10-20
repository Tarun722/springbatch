package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.skip.AlwaysSkipItemSkipPolicy;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.PathResource;

@EnableBatchProcessing
@Configuration
public class Batchconfig {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	
	@Bean
	Job startjob()
	{
		return jobBuilderFactory.get("testjobs3").incrementer(new RunIdIncrementer()).start(steprun()).build();
	}
	
	
	
	
	
	@Bean
	Step steprun()
	{
		return stepBuilderFactory.get("startstep").<Employee,Employee>chunk(2)
				.reader(reader())
				.writer(writer())
				.faultTolerant()
				.skipPolicy(new AlwaysSkipItemSkipPolicy())
				.listener(new ErrorListener())
				.build();
	}

	
	
	@Bean
	public FlatFileItemReader<Employee> reader()
	{
		FlatFileItemReader fileReader = new FlatFileItemReader<Employee>();
		
		System.out.println("in readerrrr");
		
		
		fileReader.setResource(new FileSystemResource("text.csv"));
		fileReader.setLinesToSkip(1);
		
		System.out.println("in after");
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
		return fileReader;
			
	}
	
	
	
	@Bean
	public FlatFileItemWriter<Employee> writer()
	{
		
		FlatFileItemWriter fw = new FlatFileItemWriter<Employee>();
		
		fw.setResource(new PathResource("input/output.csv"));
		
		fw.setLineAggregator(new DelimitedLineAggregator<Employee>(){
				
			{
				
				setDelimiter("||");
				setFieldExtractor(new BeanWrapperFieldExtractor<Employee>()
						{
						
					{
						setNames(new String[] {"EmployeeId","EmployeeName","EmployeeSalary","EmployeeStatus"});
					}
						
						});
			}
				
		});

		
		return fw;
	}
	
	
	
	
}
