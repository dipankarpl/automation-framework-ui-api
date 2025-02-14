package com.qa.utils;

import com.github.javafaker.Faker;

public class FakerUtil {
	Faker faker=new Faker();
	
	public String randomUsername() {
		return faker.name().username();
	}
	public String randomEmail() {
		return faker.internet().emailAddress();
	}
	public String randomFirstName() {
		return faker.name().firstName();
	}
	public String randomLastName() {
		return faker.name().lastName();
	}
	public long randomMobile() {
		return faker.number().randomNumber(10, true);
	}

}
