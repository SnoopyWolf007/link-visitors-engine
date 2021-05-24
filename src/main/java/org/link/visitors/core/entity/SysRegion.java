package org.link.visitors.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author link-kit
 */
@Data
@TableName("sys_region")
@AllArgsConstructor
@NoArgsConstructor
public class SysRegion {

    @TableField("country")
    private String country;

    @TableField("province")
    private String province;

    @TableField("city")
    private String city;

}
