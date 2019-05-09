package it.my.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestableService {
	
	@Autowired
	private MockableFeignClient cli;
	
	public String getFoo() {
		return cli.getFoo();
	}
	
	public MockablePojo getPojo() {
		return cli.getPojo();
	}

}
