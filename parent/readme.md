# Alibaba Cloud 学习

## parent    
管理依赖及版本

## common   
### model   
全局依赖model

### frame
全局框架类

## gateway   
网关   
端口：18080   
使用框架：spring-cloud-gateway + webflux + nacos + sentinel

## servers   
业务微服务父项目   
使用框架：nacos

### server-producer   
业务微服务-生产者   
端口：18081   
使用框架：spring-cloud-webflux + nacos + sentinel + feign + dubbo

### server-consumer   
业务微服务-消费者   
端口：18082   
使用框架：spring-cloud-webflux + nacos + sentinel + feign + dubbo

