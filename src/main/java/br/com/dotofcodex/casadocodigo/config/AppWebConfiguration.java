package br.com.dotofcodex.casadocodigo.config;

import java.time.format.DateTimeFormatter;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.dotofcodex.casadocodigo.controller.HomeController;
import br.com.dotofcodex.casadocodigo.dao.ProductDAO;

@EnableWebMvc
@ComponentScan(basePackageClasses = { HomeController.class, ProductDAO.class })
public class AppWebConfiguration {

	public AppWebConfiguration() {
		super();
	}

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean(name = "messageSource")
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("/WEB-INF/messages");
		bundle.setDefaultEncoding("UTF-8");
		bundle.setCacheSeconds(1);
		return bundle;
	}

	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService service = new DefaultFormattingConversionService(false);
		
		DateTimeFormatterRegistrar formatter = new DateTimeFormatterRegistrar();
		formatter.setDateFormatter(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		formatter.setDateTimeFormatter(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		formatter.registerFormatters(service);
		
		DateFormatterRegistrar formatter2 = new DateFormatterRegistrar();
		formatter2.setFormatter(new DateFormatter("dd/MM/yyyy"));
		formatter2.registerFormatters(service);
		return service;
	}

}
