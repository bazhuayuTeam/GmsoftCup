package util.configUtil;

import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import util.SpringUtil;


public class Job extends QuartzJobBean {  
		private int timeout;  
		private static int i = 0; 
		//调度工厂实例化后，经过timeout时间开始执行调度  
		public void setTimeout(int timeout) {  
			this.timeout = timeout;  
		}  
		public void dost() {
			System.out.println("aaaa");
		}
		@Override
		protected void executeInternal(JobExecutionContext arg0)
				throws JobExecutionException {
			//定时任务代码
			
		}
		
}  