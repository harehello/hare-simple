package org.hare.common.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wang cheng
 */
@Getter
@Setter
public class OptionResponse {
    private String key;
    private String value;

    public OptionResponse() {
    }
    public OptionResponse(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
