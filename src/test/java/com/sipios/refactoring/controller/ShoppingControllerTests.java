package com.sipios.refactoring.controller;

import com.sipios.refactoring.UnitTest;
import com.sipios.refactoring.business.IShoppingFacade;
import com.sipios.refactoring.business.domain.Body;
import com.sipios.refactoring.business.domain.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class ShoppingControllerTests extends UnitTest {

    @InjectMocks
    private ShoppingController controller;

    @Mock
    private IShoppingFacade shoppingFacade;

    @Test
    void should_not_throw() {
        Assertions.assertDoesNotThrow(
            () -> controller.getPrice(new Body(new Item[]{}, "STANDARD_CUSTOMER"))
        );
    }
}
