package com.appslabtest.ormtools.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appslabtest.ormtools.springdatajpa.dao.repositories.MonthRepository;
import com.appslabtest.ormtools.model.Month;

@Service
public class MonthServiceImpl implements MonthService{
	
	@Resource
	MonthRepository monthRespository;
	
	@Override
	@Transactional
	public Month findMonthByName(String name) {
		Month month = monthRespository.findMonthByName(name);
		return month;
	}
}
