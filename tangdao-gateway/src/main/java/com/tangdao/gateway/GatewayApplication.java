/**
 *
 */
package com.tangdao.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * 	网关服务中心
 * @author Ryan Ru(ruyangit@gmail.com)
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication{
	
	public static void main(String[] args){
        SpringApplication.run(GatewayApplication.class, args);
	}
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
