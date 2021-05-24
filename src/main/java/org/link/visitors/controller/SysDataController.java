package org.link.visitors.controller;

import lombok.AllArgsConstructor;
import org.link.visitors.core.dto.IResponse;
import org.link.visitors.core.service.SysDataService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author link-kit
 */
@RestController
@RequestMapping("/data")
@AllArgsConstructor
public class SysDataController {

    private final SysDataService sysDataService;

    @RequestMapping("/list")
    public IResponse list() {

        return IResponse.getInstance().success();
    }

}
