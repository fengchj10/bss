package com.angevin.interceptor;

import com.angevin.entity.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义拦截器，实现简单的登录拦截
 *
 * @author Angevin
 * @date 2019年11月1日
 */
@Component
@Aspect
public class AuthTokenInterceptor {

    private static Map<String, List<FilterMethod>> filterMethodMap = new HashMap<>();

    private static Map<Integer,List<String>> permMap = new HashMap<>();

    static {
        XStream xStream = new XStream(new PureJavaReflectionProvider());
        //需要先指定classLoader 否则无法识别bean
        xStream.setClassLoader(AuthTokenInterceptor.class.getClassLoader());
        xStream.processAnnotations(Filters.class);
        xStream.processAnnotations(Filter.class);
        xStream.processAnnotations(FilterMethod.class);

        InputStream inputStream = xStream.getClassLoader().getResourceAsStream("config/filterMethod.xml");
//            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config/filterMethod.xml")

        Filters   filters = (Filters) xStream.fromXML(inputStream);

        List<Filter> filterList = filters.getFilterList();
        for (Filter filter : filterList) {
            filterMethodMap.put(filter.getName(), filter.getFilterMethodList());
        }

        List<String> queryList = Arrays.asList("get");
        permMap.put(0, queryList);
        List<String> editList = Arrays.asList("get","post","put","delete");
        permMap.put(1,editList);
    }

    @Pointcut("within (com.angevin.controller..*) && !within(com.angevin.controller.admin.LoginController)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object trackInfo(ProceedingJoinPoint joinPoint) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String servletPath = request.getServletPath();
        String methodType = request.getMethod();

//        request.getSession().setAttribute("user", new User()); //测试，手动添加用户登录的session
        User user = (User) request.getSession().getAttribute("user");

        if (!ignoreValidateAuthorization("global", servletPath, methodType) && user == null) {
            //手动转发到/login映射路径
            attributes.getResponse().sendRedirect("/bss/login");
        }

        //一定要指定Object返回值，若AOP拦截的Controller return了一个视图地址，那么本来Controller应该跳转到这个视图地址的，但是被AOP拦截了，那么原来Controller仍会执行return，但是视图地址却找不到404了
        //切记一定要调用proceed()方法
        //proceed()：执行被通知的方法，如不调用将会阻止被通知的方法的调用，也就导致Controller中的return会404
        return joinPoint.proceed();
    }

    private boolean ignoreValidateAuthorization(String authName, String servletPath, String methodType) {
        List<FilterMethod> filterMethodList = filterMethodMap.get(authName);
        String url, type, rule;
        for (FilterMethod filterMethod : filterMethodList) {
            url = filterMethod.getUrl();
            type = filterMethod.getType();
            rule = filterMethod.getRule();
            switch (rule){
                case "equals":
                    if ("ignore".equals(type)) {
                        if (servletPath.equals(url)) {
                            return true;
                        }
                    } else {
                        if (servletPath.equals(url) && type.equalsIgnoreCase(methodType)) {
                            return true;
                        }
                    }
                    break;
                case "startWith":
                    if ("ignore".equals(type)) {
                        if (servletPath.startsWith(url)) {
                            return true;
                        }
                    } else {
                        if (servletPath.startsWith(url) && type.equalsIgnoreCase(methodType)) {
                            return true;
                        }
                    }
                    break;
                case "matches":
                    if ("ignore".equals(type)) {
                        if (servletPath.matches(url)) {
                            return true;
                        }
                    } else {
                        if (servletPath.matches(url) && type.equalsIgnoreCase(methodType)) {
                            return true;
                        }
                    }
                    break;
                default:
                    return false;
            }

        }
        return false;
    }


    @XStreamAlias("filters")
    public static class Filters {
        @XStreamImplicit(itemFieldName="filter")
        private List<Filter> filterList = new ArrayList<>();

        public List<Filter> getFilterList() {
            return filterList;
        }

        public void setFilterList(List<Filter> filterList) {
            this.filterList = filterList;
        }

        public void add(Filter filter) {
            this.filterList.add(filter);
        }
    }

    public static class Filter {
        @XStreamAsAttribute
        private String name;
        @XStreamImplicit(itemFieldName="method")
        private List<FilterMethod> filterMethodList = new ArrayList<FilterMethod>();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<FilterMethod> getFilterMethodList() {
            return filterMethodList;
        }

        public void setFilterMethodList(List<FilterMethod> filterMethodList) {
            this.filterMethodList = filterMethodList;
        }

        public void add(FilterMethod method) {
            this.filterMethodList.add(method);
        }
    }

    public static class FilterMethod {
        @XStreamAsAttribute
        private String url;
        @XStreamAsAttribute
        private String type = "ignore";
        @XStreamAsAttribute
        private String rule = "equals";

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRule() {
            return rule;
        }

        public void setRule(String rule) {
            this.rule = rule;
        }
    }
}
