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
	
	
	//With Request Parameter
	
	@GetMapping(path="/person", params="version=1")
	public PersonV1 getFirstVersonOfPersonRequestParameter() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path="/person", params="version=2")
	public PersonV2 getSecondVersonOfPersonRequestParameter() {
		return new PersonV2(new Name("Bob","Charlie"));
	}
	
	//With custom header
	
	@GetMapping(path="/person/header", headers="X-API-VERSION=1")
	public PersonV1 getFirstVersonOfPersonCustomHeader() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping(path="/person/header", headers="X-API-VERSION=2")
	public PersonV2 getSecondVersonOfPersonCustomHeader() {
		return new PersonV2(new Name("Bob","Charlie"));
	}
	
	//With Media type versioning 
	
		@GetMapping(path="/person/accept", produces="application/vnd.company.app-v1+json")
		public PersonV1 getFirstVersonOfPersonMedia() {
			return new PersonV1("Bob Charlie");
		}
		
		@GetMapping(path="/person/accept", produces="application/vnd.company.app-v2+json")
		public PersonV2 getSecondVersonOfPersonMedia() {
			return new PersonV2(new Name("Bob","Charlie"));
		}
	
	
}
