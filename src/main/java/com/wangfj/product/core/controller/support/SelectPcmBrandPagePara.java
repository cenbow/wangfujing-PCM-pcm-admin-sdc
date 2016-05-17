package com.wangfj.product.core.controller.support;

import com.wangfj.product.core.controller.support.base.para.BasePara;

/**
 * 分页查询品牌Controller参数
 * 
 * @Class Name SelectPcmBrandPagePara
 * @Author wangx
 * @Create In 2015-8-3
 */
public class SelectPcmBrandPagePara extends BasePara {

	/**
	 * @Field long serialVersionUID
	 */
	private static final long serialVersionUID = -5909136040021489993L;

	private Long sid;

	private String brandSid;// 品牌编码

	private String brandName;// 品牌名称

	private String spell;// 拼音

	private String parentSid;// 与集团品牌关联的sid

	private Integer shopType;// 门店类型(0 北京，1 外埠 ，2 电商erp)默认为0

	private Integer brandType;// 品牌类型 0 集团品牌 1 门店品牌 

	private Integer status = 0;// 有效标记：0有效，1无效（默认为0）

	private Integer currentPage = 1;// 当前页数

	private Integer pageSize = 10;// 每页大小

	/** 当前页的起始索引,从0开始 */
	private int start = 0;

	/** mysql 分页 */
	private int limit = 10;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getBrandSid() {
		return brandSid;
	}

	public void setBrandSid(String brandSid) {
		this.brandSid = brandSid == null ? null : brandSid.trim();
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName == null ? null : brandName.trim();
	}

	public String getSpell() {
		return spell;
	}

	public void setSpell(String spell) {
		this.spell = spell == null ? null : spell.trim();
	}

	public String getParentSid() {
		return parentSid;
	}

	public void setParentSid(String parentSid) {
		this.parentSid = parentSid == null ? null : parentSid.trim();
	}

	public Integer getShopType() {
		return shopType;
	}

	public void setShopType(Integer shopType) {
		this.shopType = shopType;
	}

	public Integer getBrandType() {
		return brandType;
	}

	public void setBrandType(Integer brandType) {
		this.brandType = brandType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
