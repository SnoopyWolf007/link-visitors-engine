package org.link.visitors.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author link-kit
 */
@Data
@TableName("sys_data")
public class SysData {

    @TableId("mobile")
    private String mobile;

    @TableField("qq")
    private String qq;

    @TableField("isp_type")
    private String ispType;

    @TableField("post_code")
    private String postCode;

    @TableField("country")
    private String country;

    @TableField("province")
    private String province;

    @TableField("city")
    private String city;

    @TableField("name")
    private String name;
}
