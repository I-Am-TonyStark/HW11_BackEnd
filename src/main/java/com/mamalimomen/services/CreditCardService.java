package com.mamalimomen.services;

import com.mamalimomen.base.services.BaseService;
import com.mamalimomen.domains.CreditCard;

import java.util.List;
import java.util.Optional;

public interface CreditCardService extends BaseService<CreditCard, Long> {
    Optional<CreditCard> findOneCreditCard(Long ownerID);

    List<CreditCard> findAllCreditCards();
}
