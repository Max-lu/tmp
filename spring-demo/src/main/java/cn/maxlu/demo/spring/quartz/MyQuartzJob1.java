package cn.maxlu.demo.spring.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MyQuartzJob1 extends QuartzJobBean {
	
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}


	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("=========MyQuartzJob1:name=" + name + "==========");
	}

}
