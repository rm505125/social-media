package com.code.socialmedia.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

	
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersonOfPerson() {
		return new PersonV1("Bob Charlie");
	}
	

	@GetMapping("/v2/person")
	public PersonV2 getSecondVersonOfPerson() {
		return new PersonV2(new Name("Bob","Charlie"));
	}
}
