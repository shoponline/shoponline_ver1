package cn.shop.dao;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcAccessor;

public class DbutilsTemplate extends JdbcAccessor {
	private QueryRunner queryRunner = null;

	public DbutilsTemplate() {
	}

	public DbutilsTemplate(DataSource dataSource) {
		setDataSource(dataSource);
		afterPropertiesSet();
	}

	public <T> T query(String sql, ResultSetHandler<T> rsh) {
		Connection con = null;
		try {
			con = DataSourceUtils.getConnection(getDataSource());
			queryRunner = new QueryRunner();
			return queryRunner.query(con, sql, rsh);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataSourceUtils.releaseConnection(con, getDataSource());
		}
		return null;
	}
}