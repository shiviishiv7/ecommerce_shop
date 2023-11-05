package com.halim.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentTrackActivities {


    private String date;
    private String status;
    private String activity;
    private String location;
    private String srstatus;

}
