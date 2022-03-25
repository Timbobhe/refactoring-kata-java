package com.sipios.refactoring.business.domain;

import com.sipios.refactoring.business.constants.CustomerConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * discount by customer type
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum DiscountCustomerType {

    STANDARD_CUSTOMER(CustomerConstants.STANDARD_CUSTOMER, 1, 200, "standard customer"),
    PREMIUM_CUSTOMER(CustomerConstants.PREMIUM_CUSTOMER, 0.9, 800, "premium customer"),
    PLATINUM_CUSTOMER(CustomerConstants.PLATINUM_CUSTOMER, 0.5, 2000, "platinum customer");
//  YOUNG_CUSTOMER(CustomerConstants.YOUNG_CUSTOMER, 0.1, 50, "young customer");

    private String customerType;
    private double discount;
    private double maxPrice;
    private String customerMessageExceptionType;

    public static DiscountCustomerType getDiscountCustomer(Body body) {
        for (DiscountCustomerType discountCustomer : values()) {
            if (discountCustomer.getCustomerType().equals(body.getType())) {
                return discountCustomer;
            }
        }
        return null;
    }
}
