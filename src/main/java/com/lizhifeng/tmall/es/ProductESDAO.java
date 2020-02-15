package com.lizhifeng.tmall.es;

import com.lizhifeng.tmall.pojo.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @description:
 * @author: 李志峰
 * @time: 2019/12/23 16:15
 */
public interface ProductESDAO extends ElasticsearchRepository<Product,Integer> {

}
