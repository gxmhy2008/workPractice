package com.gxm.workPractice.cms.util.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DatabaseContextHolder.getCustomerType();
	}
}