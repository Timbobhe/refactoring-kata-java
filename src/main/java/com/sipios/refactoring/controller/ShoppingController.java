package com.sipios.refactoring.controller;

import com.sipios.refactoring.business.IShoppingFacade;
import com.sipios.refactoring.business.domain.Body;
import com.sipios.refactoring.exception.ShoppingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/shopping")
public class ShoppingController {
    @Autowired
    private IShoppingFacade shoppingFacade;

    @PostMapping
    public String getPrice(@RequestBody Body b) throws ShoppingException {
        if (Objects.isNull(b)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return shoppingFacade.getPrice(b);
    }
}
