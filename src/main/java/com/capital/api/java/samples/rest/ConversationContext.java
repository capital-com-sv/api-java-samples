package com.capital.api.java.samples.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ConversationContext {
    private String apiKey;
    private String clientSecurityToken;
    private String accountSecurityToken;
    private String streamingURL;
}
