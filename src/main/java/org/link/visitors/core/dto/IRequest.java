package org.link.visitors.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ginger
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IRequest<T> {

    private Header header;
    private T body;

    @Data
    public static class Header {
        private String accessToken;
        private Long userId;
    }
}
