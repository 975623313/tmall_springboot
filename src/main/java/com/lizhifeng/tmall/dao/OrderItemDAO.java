/**
* 模仿天猫整站 springboot 教程 为 how2j.cn 版权所有
* 本教程仅用于学习使用，切勿用于非法用途，由此引起一切后果与本站无关
* 供购买者学习，请勿私自传播，否则自行承担相关法律责任
*/	

package com.lizhifeng.tmall.dao;

import com.lizhifeng.tmall.pojo.Order;
import com.lizhifeng.tmall.pojo.OrderItem;
import com.lizhifeng.tmall.pojo.Product;
import com.lizhifeng.tmall.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemDAO extends JpaRepository<OrderItem,Integer>{
	List<OrderItem> findByOrderOrderByIdDesc(Order order);
	List<OrderItem> findByProduct(Product product);
	List<OrderItem> findByUserAndOrderIsNull(User user);
	@Query(value = "select  * from orderitem where oid=?",nativeQuery = true)
	OrderItem getOrderItemNum(int id);

}


