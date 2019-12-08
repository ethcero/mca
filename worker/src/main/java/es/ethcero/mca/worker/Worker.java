package es.ethcero.mca.worker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.ethcero.mca.worker.models.Task;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Worker {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    private AmqpAdmin amqpAdmin;

    ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void createQueues() {
        amqpAdmin.declareQueue(new Queue("tasks", true));
        amqpAdmin.declareQueue(new Queue("progress", true));
    }

    @RabbitListener(queues = "newTasks", ackMode = "AUTO")
    public void received(String message) throws JsonProcessingException{
        System.out.println("Message from queue: "+message);


        Task task = objectMapper.readValue(message, Task.class);
        //TODO send external service

        for(int i = 0; i <= 10; i++) {
            try {
                Thread.sleep(1000);
                task.setProgress(i*10);
                publish(task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        task.setResult("TODO EN MASYUSCULAS");
        publish(task);
    }

    private void publish( Object value) throws JsonProcessingException {
        System.out.println("publishToQueue: '" + value + "'");
        rabbitTemplate.convertAndSend("tasksProgress", objectMapper.writeValueAsString(value));
    }
}
