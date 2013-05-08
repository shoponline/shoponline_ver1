package cn.shop.dao.impl;

import javax.annotation.Resource;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.shop.bean.PageBean;
import cn.shop.dao.BaseDao;
import cn.shop.dao.DbutilsTemplate;

@Transactional
@Repository("baseDaoImpl")
public class BaseDaoImpl4Mysql<T> implements BaseDao<T> {

	private static final long serialVersionUID = -8782445358749275659L;

	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Resource(name = "dbutilsTemplate")
	private DbutilsTemplate dbutilsTemplate;

	@Override
	public int update(String sql, Object... args) {
		return jdbcTemplate.update(sql, args);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public int stat(String sql, Object... args) {
		return jdbcTemplate.queryForObject(sql, Integer.class, args);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public T find(String sql, Class<T> clazz, Object... args) {
		return jdbcTemplate.queryForObject(sql,
				BeanPropertyRowMapper.newInstance(clazz), args);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public PageBean<T> findBeanList(String sql, Class<T> clazz,
			PageBean<T> pageBean) {
		if (pageBean != null) {
			sql += " limit " + pageBean.getCurrentRow() + ","
					+ pageBean.getPageSize();
		}
		pageBean.setLists(dbutilsTemplate.query(sql, new BeanListHandler<T>(
				clazz)));
		return pageBean;
	}

}
