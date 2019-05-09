package it.my.test;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "http://localhost:6996", name="mockable")
public interface MockableFeignClient {
	
	@GetMapping("/api/foo}")
	public String getFoo();
	
	@GetMapping("/api/pojo")
	public MockablePojo getPojo();

}
