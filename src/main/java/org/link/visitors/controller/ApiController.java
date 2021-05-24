package org.link.visitors.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.link.visitors.core.dto.DataSearchRequestDTO;
import org.link.visitors.core.dto.IResponse;
import org.link.visitors.core.entity.SysData;
import org.link.visitors.core.entity.SysRegion;
import org.link.visitors.core.service.SysDataService;
import org.link.visitors.core.service.SysRegionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author link-kit
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ApiController {

    private final SysDataService sysDataService;
    private final SysRegionService sysRegionService;

    @RequestMapping("/search")
    public IResponse search(@RequestBody DataSearchRequestDTO searchRequestDTO) {

        Page<SysData> pageInfo = searchRequestDTO.getPageInfo();
        if (pageInfo == null) {
            pageInfo = new Page<>(1, 20, 0, true);
        }

        SysData searchInfo = searchRequestDTO.getSearchInfo();
        if (searchInfo == null) {
            searchInfo = new SysData();
        }

        if (searchInfo.getCountry() == null) {
            searchInfo.setCountry("中国");
        }

        QueryWrapper<SysData> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(searchInfo);


        Page<SysData> pageData = sysDataService.page(pageInfo, queryWrapper);
        return IResponse.getInstance().success().data(pageData);
    }

    @RequestMapping("/region")
    public IResponse country(@RequestBody SysRegion sysRegion) {
        QueryWrapper<SysRegion> queryWrapper = new QueryWrapper<>();
        if (sysRegion == null) {
            sysRegion = new SysRegion("中国", null, null);

        }
        if (sysRegion.getCountry() == null) {
            sysRegion.setCountry("中国");
        }

        queryWrapper.setEntity(sysRegion);
        List<SysRegion> list = sysRegionService.list(queryWrapper);
        return IResponse.getInstance().success().data(list);
    }

}
