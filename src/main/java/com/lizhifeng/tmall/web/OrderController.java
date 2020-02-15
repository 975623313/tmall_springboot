package com.lizhifeng.tmall.web;

import com.lizhifeng.tmall.pojo.OrderItem;
import com.lizhifeng.tmall.pojo.Product;
import com.lizhifeng.tmall.service.ProductService;
import com.lizhifeng.tmall.util.Page4Navigator;
import com.lizhifeng.tmall.util.Result;
import com.lizhifeng.tmall.pojo.Order;
import com.lizhifeng.tmall.service.OrderItemService;
import com.lizhifeng.tmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

/**
 * @description:
 * @author: 李志峰
 * @time: 2019/12/23 16:53
 */
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ProductService productService;
    @GetMapping("/orders")
    public Page4Navigator<Order> list(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        start = start<0?0:start;
        Page4Navigator<Order> page =orderService.list(start, size, 5);
        System.out.println("后台获取分页全部订单列表"+page.toString());
        orderItemService.fill(page.getContent());
        orderService.removeOrderFromOrderItem(page.getContent());
        return page;
    }
    @PutMapping("deliveryOrder/{oid}")
    public Object deliveryOrder(@PathVariable int oid) throws IOException {
        System.out.println("oid------"+oid);
        Order o = orderService.get(oid);
        OrderItem orderItem =  orderItemService.getOrderItemNumber(oid);

        Product product = orderItem.getProduct();

        System.out.println("product.getStock()---"+product.getStock());
        System.out.println("orderItem.getNumber()---"+orderItem.getNumber());
        product.setStock(product.getStock()-orderItem.getNumber());
        productService.update(product);
        o.setDeliveryDate(new Date());
        o.setStatus(OrderService.waitConfirm);
        orderService.update(o);
        return Result.success();
    }
}