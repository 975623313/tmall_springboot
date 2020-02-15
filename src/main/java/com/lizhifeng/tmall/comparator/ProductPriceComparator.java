package com.lizhifeng.tmall.comparator;

import com.lizhifeng.tmall.pojo.Product;

import java.util.Comparator;

/**
 * @description:
 * @author: 李志峰
 * @time: 2019/12/23 15:26
 */
public class ProductPriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o1.getPromotePrice()-o2.getPromotePrice());
    }
}
