package com.specialpriceshop.domain;

import com.specialpriceshop.common.entity.BaseTimeEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private double originalPrice;

    public Item(
        final String name,
        final String description,
        final Double originalPrice
    ) {
        this.name = name;
        this.description = description;
        this.originalPrice = originalPrice;
    }
}
