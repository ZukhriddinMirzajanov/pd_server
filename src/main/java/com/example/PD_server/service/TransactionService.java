package com.example.PD_server.service;

import com.example.PD_server.dto.TransactionDto;
import com.example.PD_server.exception.ResourceNotFoundException;
import com.example.PD_server.model.Offer;
import com.example.PD_server.model.Request;
import com.example.PD_server.model.Transaction;
import com.example.PD_server.repository.OfferRepository;
import com.example.PD_server.repository.RequestRepository;
import com.example.PD_server.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private OfferRepository offerRepository;

    public Transaction createTransaction(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDto.getAmount());
        Request request = requestRepository.findById(transactionDto.getRequestId()).orElseThrow(() -> new ResourceNotFoundException("Request not found"));
        transaction.setRequest(request);
        Offer offer = offerRepository.findById(transactionDto.getOfferId()).orElseThrow(() -> new ResourceNotFoundException("Offer not found"));
        transaction.setOffer(offer);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
    }

    public Transaction updateTransaction(Long id, TransactionDto transactionDto) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
        transaction.setAmount(transactionDto.getAmount());
        Request request = requestRepository.findById(transactionDto.getRequestId()).orElseThrow(() -> new ResourceNotFoundException("Request not found"));
        transaction.setRequest(request);
        Offer offer = offerRepository.findById(transactionDto.getOfferId()).orElseThrow(() -> new ResourceNotFoundException("Offer not found"));
        transaction.setOffer(offer);
        return transactionRepository.save(transaction);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }

    public TransactionDto toDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setRequestId(transaction.getRequest().getId());
        transactionDto.setOfferId(transaction.getOffer().getId());
        return transactionDto;
    }
}
