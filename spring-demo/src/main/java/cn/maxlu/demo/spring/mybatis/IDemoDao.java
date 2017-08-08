package cn.maxlu.demo.spring.mybatis;

import org.springframework.stereotype.Repository;

@Repository
public interface IDemoDao {
	DemoInfo selectDemo(int id);

	DemoInfo selectByName(String name);

	int insertDemo(DemoInfo demoInfo);
}
