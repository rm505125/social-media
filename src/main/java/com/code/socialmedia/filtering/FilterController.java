package com.code.socialmedia.filtering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilterController {

	// static filtering
	@GetMapping("/filtering")
	public SomeBean filtering() {
		return new SomeBean("value1", "value2", "value3");
	}

	@GetMapping("/filtering-list")
	public List<SomeBean> filteringList() {
		return Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value4", "value5", "value6"),
				new SomeBean("value4", "value5", "value6"));
	}

	// dynamic filtering
	// for the same bean different filtering
	@GetMapping("/filtering-dynamic")
	public MappingJacksonValue filteringDynamic() {
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		MappingJacksonValue jacksonValueFilter = filter(someBean);
		return jacksonValueFilter;
	}

	@GetMapping("/filtering-list-dynamic")
	public MappingJacksonValue filteringListDynamic() {

		List<SomeBean> list = new ArrayList<>();
		list.add(new SomeBean("value1", "value2", "value3"));
		list.add(new SomeBean("value4", "value5", "value6"));
		list.add(new SomeBean("value4", "value5", "value6"));

		MappingJacksonValue jacksonValueFilter = filter(list);
		return jacksonValueFilter;
	}

	private MappingJacksonValue filter(List<SomeBean> list) {
		// TODO Auto-generated method stub
		MappingJacksonValue jacksonValue = new MappingJacksonValue(list);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		jacksonValue.setFilters(filters);
		return jacksonValue;

	}

	public MappingJacksonValue filter(SomeBean someBean) {
		MappingJacksonValue jacksonValue = new MappingJacksonValue(someBean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		jacksonValue.setFilters(filters);
		return jacksonValue;
	}
}
