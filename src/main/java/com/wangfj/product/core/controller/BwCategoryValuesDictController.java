package com.wangfj.product.core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangfj.core.framework.base.controller.BaseController;
import com.wangfj.core.utils.HttpUtil;
import com.wangfj.core.utils.JsonUtil;
import com.wangfj.core.utils.PropertyUtil;
import com.wangfj.core.utils.ResultUtil;
import com.wangfj.product.category.domain.entity.PcmCategory;
import com.wangfj.product.category.domain.entity.PcmCategoryPropsDict;
import com.wangfj.product.category.domain.entity.PcmCategoryValuesDict;
import com.wangfj.product.category.domain.vo.PcmCategoryValuesDictDto;
import com.wangfj.product.category.service.intf.ICategoryPropsDictService;
import com.wangfj.product.category.service.intf.ICategoryService;
import com.wangfj.product.category.service.intf.ICategoryValuesDictService;
import com.wangfj.product.core.controller.support.CategoryValueDictPara;

@Controller
@RequestMapping("/valuesdictcontroller")
public class BwCategoryValuesDictController extends BaseController {

	@Autowired
	private ICategoryValuesDictService ssdCategoryValuesDictService;

	@Autowired
	private ICategoryPropsDictService ssdCategoryPropsDictService;

	@Autowired
	private ICategoryService categoryService;

	/**
	 * 属性值列表分页查询
	 * 
	 * @Methods Name list
	 * @Create In 2015年8月6日 By duanzhaole
	 * @param model
	 * @param request
	 * @param response
	 * @param valuePara
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/valuesdictList", method = { RequestMethod.POST, RequestMethod.GET })
	public Map<String, Object> list(Model model, HttpServletRequest request,
			HttpServletResponse response, CategoryValueDictPara valuePara) {

		// 获取参数
		String valuesName = valuePara.getValuesName();
		String page = valuePara.getPage();
		String rows = valuePara.getRows();
		PcmCategoryValuesDict scp = new PcmCategoryValuesDict();
		if (!(valuesName == null || "".equals(valuesName))) {
			scp.setValuesName(valuesName);
		}
		scp.setStart((Integer.valueOf(page) - 1) * Integer.valueOf(rows));
		scp.setPageSize(Integer.valueOf(rows));
		int total = this.ssdCategoryValuesDictService.selectPageTotal(scp);

		List<PcmCategoryValuesDictDto> lists = this.ssdCategoryValuesDictService.selectPage(scp);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("total", total);
		jsonMap.put("rows", lists);

		return ResultUtil.creComSucResult(jsonMap);
	}

	/**
	 * 属性值添加、修改
	 * 
	 * @Methods Name add
	 * @Create In 2015年8月6日 By duanzhaole
	 * @param m
	 * @param request
	 * @param valuePara
	 * @param response
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/valuesdictAdd", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String add(Model m, HttpServletRequest request, CategoryValueDictPara valuePara) {
		String url = PropertyUtil.getSystemUrl("pcm-admin")
				+ "valuesdictcontroller/bw/valuesdictAdd.htm";
		String response = HttpUtil.doPost(url, JsonUtil.getJSONString(valuePara));
		return response;
	}

	/**
	 * 通过sid查询属性值信息
	 * 
	 * @Methods Name edit
	 * @Create In 2015年8月6日 By duanzhaole
	 * @param m
	 * @param request
	 * @param response
	 * @param valuePara
	 * @return Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/valuesdictEdit", method = { RequestMethod.POST, RequestMethod.GET })
	public Map<String, Object> edit(Model m, HttpServletRequest request,
			HttpServletResponse response, CategoryValueDictPara valuePara) {
		String sid = valuePara.getSid();
		PcmCategoryValuesDict scpd = this.ssdCategoryValuesDictService.get(Long.valueOf(sid));
		JSONObject json = new JSONObject();
		json.put("valuesName", scpd.getValuesName());
		json.put("valuesDesc", scpd.getValuesDesc());
		json.put("valuesCode", scpd.getValuesCode());
		json.put("status", scpd.getStatus());
		json.put("channelSid", scpd.getChannelSid());

		return ResultUtil.creComSucResult(json);
	}

	/**
	 * 删除属性值信息
	 * 
	 * @Methods Name del
	 * @Create In 2015年8月6日 By duanzhaole
	 * @param m
	 * @param request
	 * @param response
	 * @param valuePara
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/valuesdictDel", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String del(Model m, HttpServletRequest request, CategoryValueDictPara valuePara) {
		String url = PropertyUtil.getSystemUrl("pcm-admin")
				+ "valuesdictcontroller/bw/valuesdictDel.htm";
		String response = HttpUtil.doPost(url, JsonUtil.getJSONString(valuePara));
		return response;
	}

	/**
	 * 根据品类sid查询属性值信息
	 * 
	 * @Methods Name comboxlist
	 * @Create In 2015年8月6日 By duanzhaole
	 * @param model
	 * @param request
	 * @param response
	 * @param valuePara
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/valuescomboxList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String comboxlist(Model model, HttpServletRequest request, HttpServletResponse response,
			CategoryValueDictPara valuePara) {
		String sid = valuePara.getSid();
		PcmCategory sc = this.categoryService.get(Long.valueOf(sid));
		PcmCategoryValuesDict scp = new PcmCategoryValuesDict();
		scp.setChannelSid(sc.getChannelSid());
		List<PcmCategoryValuesDict> lists = this.ssdCategoryValuesDictService.selectList(scp);
		JSONArray jsons = new JSONArray();
		for (PcmCategoryValuesDict scvd : lists) {
			JSONObject json = JSONObject.fromObject(scvd);
			jsons.add(json);
		}
		String result = jsons.toString();
		return result;
	}

	/**
	 * 根据属性查询属性值信息
	 * 
	 * @Methods Name comboxvlist
	 * @Create In 2015年8月6日 By duanzhaole
	 * @param model
	 * @param request
	 * @param response
	 * @param valuePara
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/valuesboxList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String comboxvlist(Model model, HttpServletRequest request,
			HttpServletResponse response, CategoryValueDictPara valuePara) {

		String sid = valuePara.getSid();
		PcmCategoryPropsDict scpd = this.ssdCategoryPropsDictService.get(Long.valueOf(sid));
		PcmCategoryValuesDict scp = new PcmCategoryValuesDict();
		scp.setChannelSid(scpd.getChannelSid());
		scp.setPropsSid(scpd.getPropsSid());
		List<PcmCategoryValuesDict> lists = this.ssdCategoryValuesDictService.selectList(scp);
		JSONArray jsons = new JSONArray();
		for (PcmCategoryValuesDict scvd : lists) {
			JSONObject json = JSONObject.fromObject(scvd);
			jsons.add(json);
		}
		String result = jsons.toString();
		return result;
	}

	/**
	 * 根据属性查询属性值信息
	 * 
	 * @Methods Name comboxvlist
	 * @Create In 2015年8月6日 By duanzhaole
	 * @param model
	 * @param request
	 * @param response
	 * @param valuePara
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value = "/bw/selectvalueDictByCateSid", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String selectvalueDictByCateSid(Model model, HttpServletRequest request,
			HttpServletResponse response, CategoryValueDictPara valuePara) {

		String sid = valuePara.getSid();
		PcmCategoryPropsDict scpd = this.ssdCategoryPropsDictService.get(Long.valueOf(sid));
		PcmCategoryValuesDictDto scp = new PcmCategoryValuesDictDto();
		scp.setChannelSid(scpd.getChannelSid());
		scp.setPropsSid(scpd.getPropsSid());
		scp.setCategorySid(Long.parseLong(valuePara.getCategorySid()));
		List<PcmCategoryValuesDict> lists = this.ssdCategoryValuesDictService
				.selectListByCategorySid(scp);
		JSONArray jsons = new JSONArray();
		for (PcmCategoryValuesDict scvd : lists) {
			JSONObject json = JSONObject.fromObject(scvd);
			jsons.add(json);
		}
		String result = jsons.toString();
		return result;
	}

}
