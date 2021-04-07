package com.auth.dbauth.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import com.auth.dbauth.core.service.BootstrapService;



/**
 * task 211, 276
 * @author Shiv Pratap Singh
 * Bootstrap class to populate the initial data and to create form fields.
 */
@Service
@DependsOn("encoder")
public class Bootstrap implements InitializingBean {

	@Autowired
	private BootstrapService bootStrapService;

	@Override
	public void afterPropertiesSet() throws Exception {
		bootStrapService.execute();
	}
}
