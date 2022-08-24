package org.globallogic.cloudgateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackControler {
	
	@RequestMapping("/scheduleFallBack")
	public Mono<String> orderServiceFallBack(){
		return Mono.just("schedule service is taking too long to respond or is down" 
							+ "Please try again later");
	}
	@RequestMapping("/airportFallback")
	public Mono<String> paymentServiceFallBack(){
		return Mono.just("airport service is taking too long to respond or is down"
				+ "Please try again later");
	}
}