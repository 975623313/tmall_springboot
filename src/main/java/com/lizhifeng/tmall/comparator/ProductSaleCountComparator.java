package com.lizhifeng.tmall.comparator;

import com.lizhifeng.tmall.pojo.Product;

import java.util.Comparator;

/**
 * @description:
 * @author: 李志峰
 * @time: 2019/12/23 15:28
 */
public class ProductSaleCountComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o2.getSaleCount()-o1.getSaleCount();
    }
}
