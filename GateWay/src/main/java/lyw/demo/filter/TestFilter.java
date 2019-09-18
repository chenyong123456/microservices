package lyw.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class TestFilter extends ZuulFilter {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("zuul-port",httpServletRequest.getLocalPort());
        System.out.println(httpServletRequest.getLocalPort());


        List<String> services =  discoveryClient.getServices();
        services.forEach(str->{
            List<ServiceInstance> instances = discoveryClient.getInstances(str);
            instances.forEach(serviceInstance -> {
                System.out.println(serviceInstance.getServiceId() + serviceInstance.getPort());
            });
        });

        return null;
    }
}
