package com.szmirren.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * SqlAssist的实体类
 * 
 * @author <a href="http://szmirren.com">Mirren</a>
 *
 */
public class SqlAssistContent {
	/** SqlAssist类的包名 */
	private String classPackage;
	/** SqlAssist类的类型 */
	private String className;
	/** SqlAssist类的配置文件 */
	private Map<String, Object> item = new HashMap<>();

	/**
	 * 初始化
	 */
	public SqlAssistContent() {
		super();
	}

	/**
	 * 通过包名与类名初始化
	 * 
	 * @param classPackage
	 * @param className
	 */
	public SqlAssistContent(String classPackage, String className) {
		super();
		this.classPackage = classPackage;
		this.className = className;
	}
	
	
	
	
	public String getClassPackage() {
		return classPackage;
	}

	public void setClassPackage(String classPackage) {
		this.classPackage = classPackage;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Map<String, Object> getItem() {
		return item;
	}

	public void setItem(Map<String, Object> item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return "SqlAssistContent [classPackage=" + classPackage + ", className=" + className + ", item=" + item + "]";
	}

}
