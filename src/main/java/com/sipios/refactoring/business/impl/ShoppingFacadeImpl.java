package com.sipios.refactoring.business.impl;

import com.sipios.refactoring.business.IShoppingFacade;
import com.sipios.refactoring.business.domain.Body;
import com.sipios.refactoring.business.domain.DiscountCustomerType;
import com.sipios.refactoring.business.domain.Item;
import com.sipios.refactoring.business.domain.ItemType;
import com.sipios.refactoring.exception.ShoppingException;
import com.sipios.refactoring.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ShoppingFacadeImpl implements IShoppingFacade {

    @Override
    public String getPrice(Body body) throws ShoppingException {
        double price = 0;
        Date today = new Date();

        List<Item> itemsCustomer = Arrays.asList(body.getItems());
//       Can be retrieved from database or CSV or excel soon
        DiscountCustomerType discountCustomerType = DiscountCustomerType.getDiscountCustomer(body);

        if (discountCustomerType == null) {
//          can be customized to be more explicit for the customer
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (CollectionUtils.isEmpty(itemsCustomer)) {
            return "0";
        }
        // Compute total amount depending on the types and quantity of product
        // if we are or not in winter or summer discounts periods
        price = itemsCustomer.stream()
            .map(item -> getAmountItem(item, discountCustomerType,
                DateUtils.isSummerDiscountsPeriod(today)
                    || DateUtils.isWinterDiscountsPeriod(today)))
            .mapToDouble(value -> value).sum();

        if (price > discountCustomerType.getMaxPrice()) {
            throw new ShoppingException("Price (" + price + ") is too high for " + discountCustomerType.getCustomerMessageExceptionType());
        }

        return String.valueOf(price);
    }

    // amount by item
    private double getAmountItem(Item item, DiscountCustomerType discountCustomerType, boolean isWinterOrSummer) {
//       Can be retrieved from database or CSV or excel soon
        ItemType itemType = ItemType.getItemType(item, isWinterOrSummer);

        if (itemType != null && itemType.getCoefficient() != 0 && item.getNb() != 0 && itemType.getDiscount() != 0 && discountCustomerType != null) {
            return itemType.getCoefficient() * item.getNb() * itemType.getDiscount() * discountCustomerType.getDiscount();
        }
        return 0;
    }
}
