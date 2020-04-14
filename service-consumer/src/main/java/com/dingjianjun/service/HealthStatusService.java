package com.dingjianjun.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

/**
 * @author : Jianjun.Ding
 * @description: 健康状态Service
 * @date 2020/4/13
 */
@Service
public class HealthStatusService implements HealthIndicator {
    private Boolean status = true;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public Health health() {
        if (status) {
            return Health.up().build();
        }

        return Health.down().build();
    }
}
