package br.com.condominium.auth.config.infra;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String TENANT_EXCHANGE = "tenant.exchange";
    public static final String TENANT_PROVISIONED_QUEUE = "tenant.provisioned";
    public static final String TENANT_PROVISIONED_DLQ = "tenant.provisioned.dlq";

    @Bean
    public TopicExchange tenantExchange() {
        return new TopicExchange(TENANT_EXCHANGE, true, false);
    }

    @Bean
    public Queue tenantProvisionedQueue() {
        return QueueBuilder
                .durable(TENANT_PROVISIONED_QUEUE)
                .deadLetterExchange(TENANT_EXCHANGE)
                .deadLetterRoutingKey(TENANT_PROVISIONED_DLQ)
                .build();
    }

    @Bean
    public Queue tenantProvisionedDLQ() {
        return QueueBuilder
                .durable(TENANT_PROVISIONED_DLQ)
                .build();
    }

    @Bean
    public Binding provisionedBinding() {
        return BindingBuilder
                .bind(tenantProvisionedQueue())
                .to(tenantExchange())
                .with(TENANT_PROVISIONED_QUEUE);
    }

    @Bean
    public Binding dlqBinding() {
        return BindingBuilder
                .bind(tenantProvisionedDLQ())
                .to(tenantExchange())
                .with(TENANT_PROVISIONED_DLQ);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(
            ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter converter
    ) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter);
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter converter
    ) {
        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(converter);
        factory.setDefaultRequeueRejected(false);

        factory.setAdviceChain(
                RetryInterceptorBuilder
                        .stateless()
                        .maxAttempts(3)
                        .backOffOptions(1000, 2.0, 10000)
                        .recoverer(new RejectAndDontRequeueRecoverer())
                        .build()
        );

        return factory;
    }
}
