package com.mamalimomen.services.impl;

import com.mamalimomen.base.services.impl.BaseServiceImpl;
import com.mamalimomen.domains.CreditCard;
import com.mamalimomen.repositories.CreditCardRepository;
import com.mamalimomen.services.CreditCardService;

import java.util.List;
import java.util.Optional;

public class CreditCardServiceImpl extends BaseServiceImpl<CreditCard, Long, CreditCardRepository> implements CreditCardService {
    public CreditCardServiceImpl(CreditCardRepository creditCardRepository) {
        super(creditCardRepository);
    }

    @Override
    public Optional<CreditCard> findOneCreditCard(Long ownerID) {
        return baseRepository.findOneByNamedQuery("CreditCard.findByOwner", ownerID, CreditCard.class);
    }

    @Override
    public List<CreditCard> findAllCreditCards() {
        return baseRepository.findAllByNamedQuery("CreditCard.findAll", CreditCard.class);
    }
}
