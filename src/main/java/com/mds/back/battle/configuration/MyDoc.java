package com.mds.back.battle.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mds.back.battle.configuration.settings.SettingsDoc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class MyDoc {

	@Autowired
	private SettingsDoc settings;

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				// .groupName("public-api")
				.apiInfo(info()).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
	}

	private ApiInfo info() {
		return new ApiInfoBuilder().title(settings.getTitle()).description(settings.getDescription())
				.termsOfServiceUrl(settings.getTerms()).licenseUrl(settings.getLicenceurl())
				.license(settings.getLicence()).version(settings.getVersion()).build();
	}

}
