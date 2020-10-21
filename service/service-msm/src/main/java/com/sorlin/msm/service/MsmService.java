package com.sorlin.msm.service;

import java.util.Map;

/**
 * <p>
 * MSM 服务类
 * </p>
 *
 * @author sorlin
 * @since 2020-08-18
 */
public interface MsmService {
    boolean send(Map<String, Object> param, String phone);
}
