/**
* 模仿天猫整站 springboot 教程 为 how2j.cn 版权所有
* 本教程仅用于学习使用，切勿用于非法用途，由此引起一切后果与本站无关
* 供购买者学习，请勿私自传播，否则自行承担相关法律责任
*/	

package com.lizhifeng.tmall.service;

import com.lizhifeng.tmall.dao.PropertyValueDAO;
import com.lizhifeng.tmall.pojo.Product;
import com.lizhifeng.tmall.pojo.Property;
import com.lizhifeng.tmall.pojo.PropertyValue;
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
@CacheConfig(cacheNames="propertyValues")
public class PropertyValueService  {

	@Autowired PropertyValueDAO propertyValueDAO;
	@Autowired PropertyService propertyService;
	
	@CacheEvict(allEntries=true)
	public void update(PropertyValue bean) {
		propertyValueDAO.save(bean);
	}

	public void init(Product product) {
		PropertyValueService propertyValueService = SpringContextUtil.getBean(PropertyValueService.class);

		List<Property> propertys= propertyService.listByCategory(product.getCategory());
		for (Property property: propertys) {
			PropertyValue propertyValue = propertyValueService.getByPropertyAndProduct(product, property);
			if(null==propertyValue){
				propertyValue = new PropertyValue();
				propertyValue.setProduct(product);
				propertyValue.setProperty(property);
				propertyValueDAO.save(propertyValue);
			}
		}
	}

	@Cacheable(key="'propertyValues-one-pid-'+#p0.id+ '-ptid-' + #p1.id")
	public PropertyValue getByPropertyAndProduct(Product product, Property property) {
		return propertyValueDAO.getByPropertyAndProduct(property,product);
	}

	@Cacheable(key="'propertyValues-pid-'+ #p0.id")
	public List<PropertyValue> list(Product product) {
		return propertyValueDAO.findByProductOrderByIdDesc(product);
	}
	
	
	
}

