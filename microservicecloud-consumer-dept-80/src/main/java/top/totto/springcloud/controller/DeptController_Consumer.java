package top.totto.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import top.totto.springcloud.entities.Dept;

@RestController
public class DeptController_Consumer {
	@Autowired
	private RestTemplate restTemplate;
	
//	private static final String REST_URL_PREFIX = "http://localhost:8001";//这是未加入ribbon前直接访问
	private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";//这是添加ribbon负载均衡后访问
	
	@PostMapping("/consumer/dept/add")
	public boolean add(Dept dept) {
		return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add", dept, Boolean.class);
	}
	
	@GetMapping("/consumer/dept/get/{id}")
	public Dept get(@PathVariable("id") long id) {
		return restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id, Dept.class);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/consumer/dept/list")
	public List<Dept> list(){
		return restTemplate.getForObject(REST_URL_PREFIX+"/dept/list", List.class);
	}
	
	@GetMapping("/consumer/dept/discovery")
	public Object discovery(){
		return restTemplate.getForObject(REST_URL_PREFIX+"/dept/discovery", Object.class);
	}
}
