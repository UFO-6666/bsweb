package com.lj.app.bsweb.upm.flows.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lj.app.bsweb.upm.AbstractBaseUpmAction;
import com.lj.app.core.common.base.service.BaseService;
import com.lj.app.core.common.flows.entity.FlowTaskHist;
import com.lj.app.core.common.flows.service.FlowEngineFacetsService;
import com.lj.app.core.common.flows.service.FlowTaskHistService;
import com.lj.app.core.common.pagination.PageTool;
import com.lj.app.core.common.util.StringUtil;
import com.lj.app.core.common.web.AbstractBaseAction;
import com.lj.app.core.common.web.Struts2Utils;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * @title :流程任务表
 * @description :FlowTaskHistAction
 * @author: liujie
 * @date: 2016-11-07 17:40:48
 */
@SuppressWarnings("serial")
@Controller
@Namespace("/jsp/flows")
@Results({
	    @Result(name = AbstractBaseAction.RELOAD, location = "flowTaskHistAction", type = AbstractBaseAction.REDIRECT),
		@Result(name = AbstractBaseAction.INPUT, location = "/jsp/flows/flowTaskHist-input.jsp"),
		@Result(name = AbstractBaseAction.SAVE, location = "flowTaskHistAction!edit.action",type=AbstractBaseAction.REDIRECT),
		@Result(name = AbstractBaseAction.LIST, location = "/jsp/flows/flowTaskHistList.jsp", type=AbstractBaseAction.REDIRECT),
		@Result(name = AbstractBaseAction.LIST, location = "/jsp/flows/flowTaskHistViewList.jsp", type=AbstractBaseAction.REDIRECT)
})

@Action("flowTaskHistAction")
public class FlowTaskHistAction extends AbstractBaseUpmAction<FlowTaskHist> {
	
	 protected Logger logger = LoggerFactory.getLogger(FlowTaskHistAction.class);

	 @Autowired
	private FlowEngineFacetsService flowEngineFacetsService;
	 
	@Autowired
	private FlowTaskHistService flowTaskHistService;
	
	private FlowTaskHist flowTaskHist;
	
	private java.lang.Integer id;
	
	public   BaseService getBaseService(){
		return flowTaskHistService;
	}
	
	public FlowTaskHist getModel() {
		return flowTaskHist;
	}
	
	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			flowTaskHist = (FlowTaskHist)flowTaskHistService.getInfoByKey(id);
		} else {
			flowTaskHist = new FlowTaskHist();
		}
	}
	
	/**
	 * 公共jgGrid查询方法
	 * @return
	 * @throws Exception
	 */
	public String jqGridList2() throws Exception {
		try {
			Map<String,Object> condition = new HashMap<String,Object>();
			page.setFilters(getModel());
			
			if (StringUtil.isNotBlank(this.getSidx())) {
				String orderBy = PageTool.convert(this.getSidx()) + " "+ this.getSord();
				page.setSortColumns(orderBy);
			}
			
			page = getBaseService().findPageList(page, condition);
			Struts2Utils.renderText(PageTool.pageToJsonJQGrid(this.page),new String[0]);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void setFlowTaskHist(FlowTaskHist flowTaskHist){
		this.flowTaskHist = flowTaskHist;
	}
	
	public void setId(java.lang.Integer id){
		this.id =  id;
	}
	
	public FlowTaskHist getFlowTaskHist(){
		return flowTaskHist;
	}
	
	public java.lang.Integer  getId(){
		return id;
	}

	public FlowEngineFacetsService getFlowEngineFacetsService() {
		return flowEngineFacetsService;
	}

	public void setFlowEngineFacetsService(FlowEngineFacetsService flowEngineFacetsService) {
		this.flowEngineFacetsService = flowEngineFacetsService;
	}

	public FlowTaskHistService getFlowTaskHistService() {
		return flowTaskHistService;
	}

	public void setFlowTaskHistService(FlowTaskHistService flowTaskHistService) {
		this.flowTaskHistService = flowTaskHistService;
	}
	
	
}

