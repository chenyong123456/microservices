package cn.knowimage.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class firstInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器已拦截!");
        //用户没有登录
//        if (1 == 1) {
//            response.sendRedirect("/index");
//            return false;
//        }
        //始终向下执行 相当于乱接器失效(拦截器确实对所有请求进行了拦截除了/index,/insert不拦截
        // 也就是不会进入preHandle()，其他的请求都会进入preHandle()方法中,只不过preHandle不做任何处理
        // 相当于没有做任何处理映射继续执行照样执行后面的方法)
            return true;
    }

}
