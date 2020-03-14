
package es.ethcero.mca.worker;

import org.aopalliance.aop.Advice;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryInterceptorBuilder;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

/**
 * @author fran
 */
@EnableRabbit
@Configuration
public class RabbitConfiguration {


    @Bean
    public RetryOperationsInterceptor retryInterceptor() {
        return RetryInterceptorBuilder.stateless()
                .backOffOptions(1000, 3.0, 10000)
                .maxAttempts(20)
                //.recoverer(observableRecoverer())
                .build();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory retryContainerFactory(
            ConnectionFactory connectionFactory, RetryOperationsInterceptor retryInterceptor) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);

        Advice[] adviceChain = { retryInterceptor };
        factory.setAdviceChain(adviceChain);

        return factory;
    }
}
