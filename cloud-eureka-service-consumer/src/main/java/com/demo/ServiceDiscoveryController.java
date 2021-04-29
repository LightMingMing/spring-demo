package com.demo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 消费方: 服务发现
 */
@RestController
@RequestMapping
public class ServiceDiscoveryController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/services")
    public List<String> getServices() {
        return discoveryClient.getServices();
    }

    // 根据应用名 (application), 获取应用所有实例信息 (instance)
    @GetMapping("/services/{serviceId}")
    public List<ServiceInstanceVo> getInstances(@PathVariable String serviceId) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        return instances.stream().map(ServiceInstanceVo::convert).collect(Collectors.toList());
    }

    @Getter
    @Setter
    static class ServiceInstanceVo {

        private String instanceId;

        private String host;

        private int port;

        public static ServiceInstanceVo convert(ServiceInstance instance) {
            ServiceInstanceVo result = new ServiceInstanceVo();
            result.setInstanceId(instance.getInstanceId());
            result.setHost(instance.getHost());
            result.setPort(instance.getPort());
            return result;
        }
    }
}