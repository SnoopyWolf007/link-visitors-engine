package org.link.visitors.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author link-kit
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {
    private Integer page;
    private Integer size;

    private Integer total;
    private Integer pages;
}
