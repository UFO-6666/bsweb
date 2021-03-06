package com.lj.app.core.common.flows.task.interceptor;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lj.app.core.common.flows.FlowInterceptor;
import com.lj.app.core.common.flows.core.Execution;
import com.lj.app.core.common.flows.entity.FlowTask;

public class LocalTaskInterceptor implements FlowInterceptor {
	private static final Log log = LogFactory.getLog(LocalTaskInterceptor.class);
	
	public void intercept(Execution execution) {
		if(log.isInfoEnabled()) {
			log.info("LocalTaskInterceptor start...");
			for(FlowTask task : execution.getTasks()) {
				StringBuffer buffer = new StringBuffer(100);
				buffer.append("创建任务[标识=").append(task.getId());
				buffer.append(",名称=").append(task.getDisplayName());
				buffer.append(",创建时间=").append(task.getCreateTime());
				buffer.append(",参与者={");
				if(task.getActorIds() != null) {
					for(String actor : task.getActorIds()) {
						buffer.append(actor).append(";");
					}
				}
				buffer.append("}]");
				log.info(buffer.toString());
			}
			log.info("LocalTaskInterceptor finish...");
		}
	}

}


