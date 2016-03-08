package co.uk.acme.deliveryday;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author psamatt
 */
final public class DeliveryDayCommandHandler {

    private Repository<DeliveryDay> repository;

    @Autowired
    public DeliveryDayCommandHandler(Repository<DeliveryDay> repository) {
        this.repository = repository;
    }

    @CommandHandler
    public void handle(ScheduleDeliveryDay command) {
        DeliveryDay deliveryDay = DeliveryDay.schedule(command.getDeliveryDayId(), command.getCapacity(), command.getDate());
        repository.add(deliveryDay);
    }

    @CommandHandler
    public void handle(ReserveDeliverySlot command) {
        DeliveryDay deliveryDay = repository.load(command.getDeliveryDayId());
        deliveryDay.reserveSlot(command.getOrderId());
    }

    @CommandHandler
    public void handle(RetractDeliverySlot command) {
        DeliveryDay deliveryDay = repository.load(command.getDeliveryDayId());
        deliveryDay.retractSlot(command.getOrderId());
    }

    @CommandHandler
    public void handle(ConfirmDeliverySlot command) {
        DeliveryDay deliveryDay = repository.load(command.getDeliveryDayId());
        deliveryDay.confirmSlot(command.getOrderId());
    }
}
