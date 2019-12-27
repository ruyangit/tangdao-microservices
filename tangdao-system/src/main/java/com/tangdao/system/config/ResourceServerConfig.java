///**
// *
// */
//package com.tangdao.system.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//
//import com.tangdao.openfeign.OAuth2FeignAutoConfiguration;
//
//import feign.RequestInterceptor;
//
///**
// * 
// * @author Ryan Ru(ruyangit@gmail.com)
// */
//@Configuration
//@EnableResourceServer
//@Import(OAuth2FeignAutoConfiguration.class)
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
////	@Autowired
////	private ResourceServerProperties sso;
//
////	@Bean
////	@ConfigurationProperties(prefix = "security.oauth2.client")
////	public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
////		return new ClientCredentialsResourceDetails();
////	}
////
////	@Bean
////	public RequestInterceptor oauth2FeignRequestInterceptor() {
////		return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), clientCredentialsResourceDetails());
////	}
////
////	@Bean
////	public OAuth2RestTemplate clientCredentialsRestTemplate() {
////		return new OAuth2RestTemplate(clientCredentialsResourceDetails());
////	}
//
////	@Bean
////	public ResourceServerTokenServices tokenServices() {
////		return new CustomUserInfoTokenServices(sso.getUserInfoUri(), sso.getClientId());
////	}
//
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/", "/demo").permitAll().anyRequest().authenticated();
//	}
//}
