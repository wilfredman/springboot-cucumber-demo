package com.tsukhu.demo.resource;

import com.github.javafaker.Faker;
import com.tsukhu.demo.domain.Address;
import com.tsukhu.demo.domain.Order;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderResource {
    @RequestMapping(method = {RequestMethod.GET}, value = {"/order/{sku}"})
    public Order getOrderBySKU(@PathVariable("sku")  String orderSKU) {
        String billToState = Faker.instance().address().stateAbbr();
        String shipToState = Faker.instance().address().stateAbbr();
        Address billTo = Address.builder()
                .name(Faker.instance().name().name())
                .address(Faker.instance().address().streetAddress())
                .state(billToState)
                .zip(Faker.instance().address().zipCode()).build();
        Address shipTo = Address.builder()
                .name(Faker.instance().name().name())
                .address(Faker.instance().address().streetAddress())
                .state(shipToState)
                .zip(Faker.instance().address().zipCode()).build();
        return Order.builder()
                .billTo(billTo)
                .shipTo(shipTo)
                .name(Faker.instance().name().name())
                .sku(orderSKU).build();
    }
}
