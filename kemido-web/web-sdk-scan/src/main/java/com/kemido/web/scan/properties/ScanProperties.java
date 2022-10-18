package com.kemido.web.scan.properties;

import com.kemido.web.core.constants.WebConstants;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>Description: 接口扫描配置 </p>
 */
@ConfigurationProperties(prefix = WebConstants.PROPERTY_REST_SCAN)
public class ScanProperties {

    /**
     * 是否开启注解扫描
     */
    private Boolean enabled;
    /**
     * 指定扫描的命名空间。未指定的命名空间中，即使包含RequestMapping，也不会被添加进来。
     */
    private List<String> scanGroupIds;
    /**
     * Spring 中会包含 Controller和 RestController，
     * 如果该配置设置为True，那么就只扫描RestController
     * 如果该配置设置为False，那么Controller和 RestController斗扫描。
     */
    private boolean justScanRestController = false;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setScanGroupIds(List<String> scanGroupIds) {
        this.scanGroupIds = scanGroupIds;
    }

    public List<String> getScanGroupIds() {
        List<String> defaultGroupIds = Stream.of("com.kemido").collect(Collectors.toList());

        if (CollectionUtils.isEmpty(this.scanGroupIds)) {
            this.scanGroupIds = new ArrayList<>();
        }

        this.scanGroupIds.addAll(defaultGroupIds);
        return scanGroupIds;
    }

    public boolean isJustScanRestController() {
        return justScanRestController;
    }

    public void setJustScanRestController(boolean justScanRestController) {
        this.justScanRestController = justScanRestController;
    }
}
