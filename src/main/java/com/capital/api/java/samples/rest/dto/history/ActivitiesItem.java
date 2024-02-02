package com.capital.api.java.samples.rest.dto.history;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivitiesItem {

    private String epic;
    private String dealId;
    private String activityHistoryId;
    private String date;
    private String time;
    private String activity;
    private String marketName;
    private String period;
    private String result;
    private String channel;
    private String currency;
    private String size;
    private String level;
    private String stop;
    private String stopType;
    private String limit;
    /*
    The action status of the activity item. The value of the action status can be any one of these.
     "ACCEPT"  - Manual accept or auto accept.
     "REJECT"  - Manual reject or declined.
     "MANUAL"  - Gone manual and in pending. Cannot determine if accepted or rejected.
     "NOT_SET" - Default value which should have been overwritten by more specific status. Possibly caused by an error in processing by order server.
    */
    private String actionStatus;

}
