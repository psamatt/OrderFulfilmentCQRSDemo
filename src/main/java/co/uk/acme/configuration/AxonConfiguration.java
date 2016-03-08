package co.uk.acme.configuration;

import co.uk.acme.deliveryday.DeliveryDay;
import co.uk.acme.fulfillment.Order;
import co.uk.acme.hr.StoreAssistant;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerBeanPostProcessor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.CommandGatewayFactoryBean;
import org.axonframework.commandhandling.interceptors.BeanValidationInterceptor;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerBeanPostProcessor;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.Arrays;

/**
 * @author psamatt
 */
@Configuration
public class AxonConfiguration {

    @Bean
    public AnnotationEventListenerBeanPostProcessor annotationEventListenerBeanPostProcessor() {
        AnnotationEventListenerBeanPostProcessor processor = new AnnotationEventListenerBeanPostProcessor();
        processor.setEventBus(eventBus());
        return processor;
    }

    @Bean
    public AnnotationCommandHandlerBeanPostProcessor annotationCommandHandlerBeanPostProcessor() {
        AnnotationCommandHandlerBeanPostProcessor processor = new AnnotationCommandHandlerBeanPostProcessor();
        processor.setCommandBus(commandBus());
        return processor;
    }

    @Bean
    public CommandBus commandBus() {
        SimpleCommandBus commandBus = new SimpleCommandBus();
        commandBus.setHandlerInterceptors(Arrays.asList(new BeanValidationInterceptor()));
        return commandBus;
    }

    @Bean
    public EventBus eventBus() {
        return new SimpleEventBus();
    }

    @Bean
    public CommandGatewayFactoryBean<CommandGateway> commandGatewayFactoryBean() {
        CommandGatewayFactoryBean<CommandGateway> factory = new CommandGatewayFactoryBean<CommandGateway>();
        factory.setCommandBus(commandBus());
        return factory;
    }

    @Bean
    public EventSourcingRepository<Order> orderRepository() {
        EventSourcingRepository<Order> repository = new EventSourcingRepository<>(Order.class, getEventStore());
        repository.setEventBus(eventBus());
        return repository;
    }

    @Bean
    public EventSourcingRepository<DeliveryDay> dayOfChoiceRepository() {
        EventSourcingRepository<DeliveryDay> repository = new EventSourcingRepository<>(DeliveryDay.class, getEventStore());
        repository.setEventBus(eventBus());
        return repository;
    }

    @Bean
    public EventSourcingRepository<StoreAssistant> storeAssistantRepository() {
        EventSourcingRepository<StoreAssistant> repository = new EventSourcingRepository<>(StoreAssistant.class, getEventStore());
        repository.setEventBus(eventBus());
        return repository;
    }

    @Bean
    public AggregateAnnotationCommandHandler orderCommandHandler() {
        return AggregateAnnotationCommandHandler.subscribe(Order.class, orderRepository(), commandBus());
    }

    @Bean
    public AggregateAnnotationCommandHandler dayOfChoiceCommandHandler() {
        return AggregateAnnotationCommandHandler.subscribe(DeliveryDay.class, dayOfChoiceRepository(), commandBus());
    }

    @Bean
    public AggregateAnnotationCommandHandler storeAssistantCommandHandler() {
        return AggregateAnnotationCommandHandler.subscribe(StoreAssistant.class, storeAssistantRepository(), commandBus());
    }

    private EventStore getEventStore() {
        return new FileSystemEventStore(new SimpleEventFileResolver(new File("data/eventstore")));
    }
}
