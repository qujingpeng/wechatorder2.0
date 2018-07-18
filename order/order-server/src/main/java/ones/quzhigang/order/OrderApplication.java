package ones.quzhigang.order;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "ones.quzhigang.product.client")
@ComponentScan("ones.quzhigang")
@EnableHystrixDashboard
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean getServlet(){

		HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();

		ServletRegistrationBean regBean = new ServletRegistrationBean(streamServlet);

		regBean.setLoadOnStartup(1);

		List mappingList = new ArrayList();

		mappingList.add("/hystrix.stream");

		regBean.setUrlMappings(mappingList);

		regBean.setName("hystrixServlet");

		return regBean;

	}
}
