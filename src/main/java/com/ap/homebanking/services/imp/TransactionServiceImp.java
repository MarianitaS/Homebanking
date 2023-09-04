package com.ap.homebanking.services.imp;

import com.ap.homebanking.models.Transaction;
import com.ap.homebanking.repositories.TransactionRepository;
import com.ap.homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImp implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;



    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
