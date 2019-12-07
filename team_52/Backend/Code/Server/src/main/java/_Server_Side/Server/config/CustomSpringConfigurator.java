package _Server_Side.Server.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.websocket.server.ServerEndpointConfig;

public class CustomSpringConfigurator extends ServerEndpointConfig.Configurator implements ApplicationContextAware {

    
    private static volatile BeanFactory context;

    @Override
    public <T> T getEndpointInstance(Class<T> endpoint) throws InstantiationException {
        return context.getBean(endpoint);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CustomSpringConfigurator.context = applicationContext;
    }
}