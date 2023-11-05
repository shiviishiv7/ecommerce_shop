package com.halim.web.response;

import com.halim.dto.ShipmentTrack;
import com.halim.dto.ShipmentTrackActivities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentTrackingResponse {

    private Integer track_status;
    private Integer shipment_status;
    private List<ShipmentTrack> shipment_track;
    private List<ShipmentTrackActivities> shipment_track_activities;
    private String track_url;
    private String etd;
}
