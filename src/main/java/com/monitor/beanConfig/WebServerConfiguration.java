package com.monitor.beanConfig;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * @author : ys
 * @date : 2019/10/21 14:55 星期一
 * 当Spring容器内没有TomcatEmbeddedServletContainerFactory这个bean时，会把此bean加载进spring容器中
 **/
@Component
public class WebServerConfiguration implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
	@Override
	public void customize(ConfigurableWebServerFactory factory) {
		// 使用对应工厂类提供给我们的接口定制化我们的tomcat connector
		((TomcatServletWebServerFactory) factory).addConnectorCustomizers(new TomcatConnectorCustomizer() {
			@Override
			public void customize(Connector connector) {// 修改长链接keepAlive相关配置，保证路由策略的性能高效。
				Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
				// 定制化keepAliveTimeout，设置30秒内没有请求则服务端自动断开keepalive链接
				protocol.setKeepAliveTimeout(30000);
				// 当客户端发送超过10000个请求则自动断开keepalive链接
				protocol.setMaxKeepAliveRequests(10000);
			}
		});
	}
}
