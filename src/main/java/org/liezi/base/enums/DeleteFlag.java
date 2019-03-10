package org.liezi.base.enums;

public enum DeleteFlag {
	/**
	 * 删除标识
	 */
	DELETED(1),
	/**
	 * 未删除标识
	 */
	UNDELETE(0);
	
	private Integer flag;
	private DeleteFlag(Integer flag){
		this.flag = flag;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	

}
