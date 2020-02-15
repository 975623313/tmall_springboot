/**
* 模仿天猫整站 springboot 教程 为 how2j.cn 版权所有
* 本教程仅用于学习使用，切勿用于非法用途，由此引起一切后果与本站无关
* 供购买者学习，请勿私自传播，否则自行承担相关法律责任
*/	

package com.lizhifeng.tmall.service;

import com.lizhifeng.tmall.dao.OrderItemDAO;
import com.lizhifeng.tmall.pojo.Order;
import com.lizhifeng.tmall.pojo.OrderItem;
import com.lizhifeng.tmall.pojo.Product;
import com.lizhifeng.tmall.pojo.User;
import com.lizhifeng.tmall.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @description:
 * @author: 李志峰
 * @time: 2019/12/25 23:04
 */
@Service
@CacheConfig(cacheNames="orderItems")
public class OrderItemService {
	@Autowired OrderItemDAO orderItemDAO;
	@Autowired ProductImageService productImageService;

	public void fill(List<Order> orders) {
		for (Order order : orders) 
			fill(order);
	}
	@CacheEvict(allEntries=true)
	public void update(OrderItem orderItem) {
		orderItemDAO.save(orderItem);
	}

	
	public void fill(Order order) {
		OrderItemService orderItemService = SpringContextUtil.getBean(OrderItemService.class);
		List<OrderItem> orderItems = orderItemService.listByOrder(order);
		float total = 0;
		int totalNumber = 0;			
		for (OrderItem oi :orderItems) {
			total+=oi.getNumber()*oi.getProduct().getPromotePrice();
			totalNumber+=oi.getNumber();
			productImageService.setFirstProdutImage(oi.getProduct());
		}
		order.setTotal(total);
		order.setOrderItems(orderItems);
		order.setTotalNumber(totalNumber);		
		order.setOrderItems(orderItems);
	}
	
	@CacheEvict(allEntries=true)
    public void add(OrderItem orderItem) {
    	orderItemDAO.save(orderItem);
    }
	@Cacheable(key="'orderItems-one-'+ #p0")
    public OrderItem get(int id) {
    	return orderItemDAO.findOne(id);
    }
	@Cacheable(key="'orderItems-one-'+ #p0")
	public OrderItem getOrderItemNumber(int id) {
		return orderItemDAO.getOrderItemNum(id);
	}

	@CacheEvict(allEntries=true)
    public void delete(int id) {
        orderItemDAO.delete(id);
    }

	
	
	
    public int getSaleCount(Product product) {
		OrderItemService orderItemService = SpringContextUtil.getBean(OrderItemService.class);
        List<OrderItem> ois =orderItemService.listByProduct(product);
        int result =0;
        for (OrderItem oi : ois) {
        	if(null!=oi.getOrder())
        	if(null!= oi.getOrder() && null!=oi.getOrder().getPayDate())
        		result+=oi.getNumber();
        }
        return result;
    }

    @Cacheable(key="'orderItems-uid-'+ #p0.id")
    public List<OrderItem> listByUser(User user) {
    	return orderItemDAO.findByUserAndOrderIsNull(user);
    }
    
    @Cacheable(key="'orderItems-pid-'+ #p0.id")
    public List<OrderItem> listByProduct(Product product) {
    	return orderItemDAO.findByProduct(product);
    }
    @Cacheable(key="'orderItems-oid-'+ #p0.id")
    public List<OrderItem> listByOrder(Order order) {
    	return orderItemDAO.findByOrderOrderByIdDesc(order);
    }
	
	
}


