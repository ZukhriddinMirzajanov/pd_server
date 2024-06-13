package com.example.PD_server.service;

import com.example.PD_server.dto.StatisticsDto;
import com.example.PD_server.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarrierRepository carrierRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public StatisticsDto getStatistics() {
        StatisticsDto statisticsDto = new StatisticsDto();
        statisticsDto.setAdminCount(userRepository.count());
        statisticsDto.setCarrierCount(carrierRepository.count());
        statisticsDto.setOfferCount(offerRepository.count());
        statisticsDto.setRegionCount(regionRepository.count());
        statisticsDto.setRequestCount(requestRepository.count());
        statisticsDto.setTransactionCount(transactionRepository.count());
        return statisticsDto;
    }
}
