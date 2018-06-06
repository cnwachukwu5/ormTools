package com.appslabtest.ormtools.service;

import com.appslabtest.ormtools.model.Month;

public interface MonthService {
	Month findMonthByName(String name);
}
