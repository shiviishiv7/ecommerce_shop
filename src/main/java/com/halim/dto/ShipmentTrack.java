package com.halim.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentTrack {

    private Integer id;
    private String awb_code;
    private Integer courier_company_id;
    private Integer shipment_id;
    private Integer order_id;
    private String pickup_date;
    private String delivered_date;
    private String weight;
    private Integer packages;
    private String current_status;
    private String delivered_to;
    private String destination;
    private String consignee_name;
    private String origin;
    private String courier_agent_details;
    private String edd;
}
