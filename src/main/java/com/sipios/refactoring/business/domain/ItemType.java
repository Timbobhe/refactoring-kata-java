package com.sipios.refactoring.business.domain;

import com.sipios.refactoring.business.constants.CustomerConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * coefficient and discount by item type
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ItemType {

    TSHIRT_WINTER_SUMMER(CustomerConstants.TSHIRT, 30, 1, true),
    DRESS_WINTER_SUMMER(CustomerConstants.DRESS, 50, 0.8, true),
    JACKET_WINTER_SUMMER(CustomerConstants.JACKET, 100, 0.9, true),
//  WINTER_SUMMER(CustomerConstants.SWEATSHIRT, 80, 1, true);

    TSHIRT(CustomerConstants.TSHIRT, 30, 1, false),
    DRESS(CustomerConstants.DRESS, 50, 1, false),
    JACKET(CustomerConstants.JACKET, 100, 1, false);
//  SWEATSHIRT(CustomerConstants.SWEATSHIRT, 80, 1, false);


    private String itemName;
    private double coefficient;
    private double discount;
    private boolean winterOrSummer;

    public static ItemType getItemType(Item item, boolean isWinterOrSummer) {
        for (ItemType itemType : values()) {
            if (itemType.getItemName().equals(item.getType()) && (isWinterOrSummer == itemType.isWinterOrSummer())) {
                return itemType;
            }
        }
        return null;
    }
}
