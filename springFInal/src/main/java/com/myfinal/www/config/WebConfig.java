package com.myfinal.www.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class, SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {ServletConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encoding = new CharacterEncodingFilter();
		encoding.setEncoding("utf-8");
		encoding.setForceRequestEncoding(true);
		return new Filter[] {encoding};
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		String uploadLocation = "D:\\moon\\_myProject\\_java\\_fileUpload";
		int maxFileSize = 1024*1024*20;
		int maxReqSize = maxFileSize*2;
		int fileSizeThreshold = maxFileSize;
		
		MultipartConfigElement multipartConfig =
				new MultipartConfigElement(uploadLocation, maxFileSize, maxReqSize, fileSizeThreshold);
		
		registration.setMultipartConfig(multipartConfig);
		
	}
}
