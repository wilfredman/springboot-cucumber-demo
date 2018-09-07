package com.tsukhu.demo.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private String name;
    private String address;
    private String state;
    private String zip;
}
