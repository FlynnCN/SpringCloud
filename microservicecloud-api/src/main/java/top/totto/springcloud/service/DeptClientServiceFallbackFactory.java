package top.totto.springcloud.service;

import java.util.List;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;
import top.totto.springcloud.entities.Dept;

@Component // 不要忘记添加，不要忘记添加
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService>{
	@Override
	public DeptClientService create(Throwable throwable){
		return new DeptClientService() {
			@Override
			public Dept get(long id){
				Dept d = new Dept();
				d.setDeptno(id);
				d.setDname("该ID：" + id + "没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭");
				d.setDb_source("no this database in MySQL");
				return d;
			}

			@Override
			public List<Dept> list(){
				return null;
			}

			@Override
			public boolean add(Dept dept){
				return false;
			}
		};
	}
}
