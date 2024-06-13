package com.example.PD_server.dto;

import lombok.Data;

@Data
public class StatisticsDto {
    private long adminCount;
    private long carrierCount;
    private long offerCount;
    private long regionCount;
    private long requestCount;
    private long roleCount;
    private long transactionCount;
}
