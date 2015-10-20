package org.people.service.client;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;

public class RestClient {

	private String serviceUrl;

	@Autowired
	private RestTemplate restTemplate;

	public RestClient() {
		super();
	}

	@PostConstruct
	public void initRestTemplate() {
		if (restTemplate == null) {
			RestTemplate restTemplate = new RestTemplate();
			List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
			messageConverters.add(new MappingJackson2HttpMessageConverter());
			restTemplate.setMessageConverters(messageConverters);
			this.setRestTemplate(restTemplate);
		}
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	protected <T> HttpEntity<T> createHttpEntity(T object) {
		return new HttpEntity<T>(object, createHttpHeaders());
	}

	protected HttpHeaders createHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

}
