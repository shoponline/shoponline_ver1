package cn.shop.dao;

import java.io.Serializable;

import cn.shop.bean.PageBean;

public interface BaseDao<T> extends Serializable {

	public int update(String sql, Object... args);

	public int stat(String sql, Object... args);

	public T find(String sql, Class<T> clazz, Object... args);

	public PageBean<T> findBeanList(String sql, Class<T> clazz,
			PageBean<T> pageBean);
}
