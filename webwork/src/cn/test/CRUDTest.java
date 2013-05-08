package cn.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.shop.bean.PageBean;
import cn.shop.dao.BaseDao;
import cn.test.bean.Car;

public class CRUDTest {

	@Test
	public void test() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"bean.xml");
		BaseDao bd = (BaseDao) applicationContext.getBean("baseDaoImpl");
		PageBean<Car> cars = new PageBean<Car>();
		cars.setRowCounts(bd.stat("select count(*) from car", null));
		cars.setCurrentPage(2);
		bd.findBeanList("select * from car", Car.class, cars);
		for (Car car : cars.getLists())
			System.out.println(car.getName());
	}

}
