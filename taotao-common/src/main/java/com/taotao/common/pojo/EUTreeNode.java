package com.taotao.common.pojo;

/**
 * EasyUI树形控件节点
 * @author Wait
 *
 */
public class EUTreeNode {

	private Long id;//节点id
	private String text;//节点名称
	private String state;//节点状态 close(父节点) or open(子节点)
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
