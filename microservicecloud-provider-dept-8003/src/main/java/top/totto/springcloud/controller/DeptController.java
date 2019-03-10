package top.totto.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import top.totto.springcloud.entities.Dept;
import top.totto.springcloud.service.DeptService;

@RestController
public class DeptController {
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private DiscoveryClient client;//Eureka的服务发现
	
	@RequestMapping(value="/dept/add",method=RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return deptService.add(dept);
	}
	
	@RequestMapping(value="/dept/get/{id}",method=RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id) {
		return deptService.get(id);
	}
	
	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list(){
		return deptService.list();
	}
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(){
		return "hello";
	}
	
//	@Autowired
//	private DiscoveryClient client;//Eureka的服务发现
	@RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
	public Object discovery(){
		List<String> services = client.getServices();
		System.out.println("......"+services);
		
		List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
		for (ServiceInstance element : srvList) {
			System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
					+ element.getUri());
		}
		return this.client;
	}
}
