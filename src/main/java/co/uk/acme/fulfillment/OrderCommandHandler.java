package co.uk.acme.fulfillment;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author psamatt
 */
final public class OrderCommandHandler {

    private Repository<Order> repository;

    @Autowired
    public OrderCommandHandler(Repository<Order> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(PlaceOrder command) {
        Order order = Order.place(command.getOrderId(), command.getCustomerId(), command.getCustomerName(), command.getPhoneNumber(), command.getProducts());
        repository.add(order);
    }

    @CommandHandler
    public void handle(MarkItemAsPicked command) {
        Order order = repository.load(command.getOrderId());
        order.pickItem(command.getSku(), command.getStoreAssistantId(), command.getStoreAssistantName());
    }

    @CommandHandler
    public void handle(RecordDeliveryDate command) {
        Order order = repository.load(command.getOrderId());
        order.recordDeliveryDate(command.getDeliveryDate());
    }

    @CommandHandler
    public void handle(MarkAsDispatched command) {
        Order order = repository.load(command.getOrderId());
        order.markAsDispatched();
    }
}
