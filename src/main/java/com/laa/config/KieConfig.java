package com.laa.config;

import java.io.IOException;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.spring.KModuleBeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KieConfig {



	@Bean
	@ConditionalOnMissingBean(KieContainer.class)
	public KieContainer kieContainer() throws IOException {

		return getKieServices()
				.newKieContainer(getKieServices().newReleaseId("com.laaccms", "rules-file", "0.0.1-SNAPSHOT"));
	}

	private KieServices getKieServices() {
		return KieServices.Factory.get();
	}

	@Bean
	@ConditionalOnMissingBean(KieSession.class)
	public KieSession kieSession() throws IOException {
		return kieContainer().newKieSession();
	}

	@Bean
	@ConditionalOnMissingBean(KModuleBeanFactoryPostProcessor.class)
	public KModuleBeanFactoryPostProcessor kiePostProcessor() {
		return new KModuleBeanFactoryPostProcessor();
	}

}
