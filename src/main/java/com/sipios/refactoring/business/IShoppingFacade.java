package com.sipios.refactoring.business;

import com.sipios.refactoring.business.domain.Body;
import com.sipios.refactoring.exception.ShoppingException;

public interface IShoppingFacade {
    /**
     * retrieve the price of all the items of a customer
     *
     * @param body
     * @return
     * @throws ShoppingException
     */
    String getPrice(Body body) throws ShoppingException;
}
