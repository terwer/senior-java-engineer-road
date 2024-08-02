package space.terwer.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author terwer on 2024/6/13
 */
@Intercepts({
        @Signature(
                type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class, Integer.class}
        )
})
public class MyPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 增强逻辑
        System.out.println("这里是插件的增强方法....");
        // 执行原方法
        return invocation.proceed();
    }

    /**
     * 主要是为了把这个拦截器生成一个代理放到拦截器链中
     *
     * @param target 包装目标对象,为目标对象创建代理对象
     * @return target为要拦截的对象
     */
    @Override
    public Object plugin(Object target) {
        System.out.println("将要包装的目标对象:" + target);
        return Interceptor.super.plugin(target);
    }

    /**
     * 获取配置文件的属性，插件初始化的时候调用，也只调用一次，插件配置的属性从这里设置进来
     **/
    @Override
    public void setProperties(Properties properties) {
        System.out.println("插件配置的初始化参数:" + properties);
        Interceptor.super.setProperties(properties);
    }
}
