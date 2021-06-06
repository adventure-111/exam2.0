package cn.cuit.exam.config;

import cn.cuit.exam.bean.Admin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class AuthorityInterceptor implements HandlerInterceptor {

    // 不拦截的URI
    private static final Set<String> NOT_INTERCEPTOR_URI = new HashSet<>();

    static {
        NOT_INTERCEPTOR_URI.add("/admin/login.html");
        NOT_INTERCEPTOR_URI.add("/admin/login");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * 在请求处理之前调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (NOT_INTERCEPTOR_URI.contains(uri)) {
            return true;
        }
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) throw new RuntimeException("用户未登录");
        return true;
    }

}
