package org.link.visitors.core.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.link.visitors.core.entity.SysData;

/**
 * @author link-kit
 */
@Data
public class DataSearchRequestDTO {
    private Page<SysData> pageInfo;
    private SysData searchInfo;
}
