/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshophanghieu.Pojo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name="order_detail")
public class OrderDetail implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @ManyToOne
    @JoinColumn(name="order_id")
    private Orders order;
    
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
    @Column(name = "unit_price")
    private long unitPrice;
    @Column(name = "amount_detail")
    private int amountDetail;
    @Column(name="total_detail")
    private long totalDetail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getAmountDetail() {
        return amountDetail;
    }

    public void setAmountDetail(int amountDetail) {
        this.amountDetail = amountDetail;
    }

    public long getTotalDetail() {
        return totalDetail;
    }

    public void setTotalDetail(long totalDetail) {
        this.totalDetail = totalDetail;
    }
    
}
