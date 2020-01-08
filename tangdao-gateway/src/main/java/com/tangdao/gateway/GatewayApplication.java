/**
 *
 */
package com.tangdao.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Gateway Service
 * 
 * @author Ryan Ru(ruyangit@gmail.com)
 */
@SpringBootApplication
@EnableDiscoveryClient
//@Import(AdditionalRoutes.class)
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	
//	@Bean
//	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route(r -> r.path("/uaa/**")
//					.uri("lb:tangdao-uaa-service")
//				).build();
//	}

//	@Bean
//	@LoadBalanced
//	public WebClient webClient(final ClientHttpConnector clientHttpConnector) {
//		return WebClient.builder().clientConnector(clientHttpConnector).build();
//	}
//
//	@Bean
//	public ClientHttpConnector clientHttpConnector() {
//		return new ReactorClientHttpConnector(HttpClient.from(TcpClient.newConnection()));
//	}

//	
//	@Bean
//	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
//		return new HiddenHttpMethodFilter() {
//			@Override
//			public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
//				return chain.filter(exchange);
//			}
//		};
//	}
}
