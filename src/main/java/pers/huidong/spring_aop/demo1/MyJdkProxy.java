package pers.huidong.spring_aop.demo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: spring_ioc
 * @auther: HuiDong
 * @date: 2020/4/13 22:48
 * @description: Jdk动态代理
 */
public class MyJdkProxy implements InvocationHandler {
    private UserDao userDao;

    public MyJdkProxy(UserDao userDao){
        this.userDao=userDao;
    }
    public Object createProxy(){
        Object proxy = Proxy.newProxyInstance(userDao.getClass().getClassLoader(),userDao.getClass().getInterfaces(),this);
        return proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("save".equals(method.getName())){
            System.out.println("权限校验..."+proxy.getClass().getName());
            return method.invoke(userDao,args);
        }
        return method.invoke(userDao,args);
    }
}
