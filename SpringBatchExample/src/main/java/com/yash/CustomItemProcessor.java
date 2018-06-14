package com.yash;

import org.springframework.batch.item.ItemProcessor;
import com.yash.model.Report;

public class CustomItemProcessor implements ItemProcessor<Report, Report> {

	@Override
	public Report process(Report item) throws Exception {
		
		System.out.println("Processing... like make data UPPER CASE/LOWER CASE etc.." + item);
		return item;
	}

}