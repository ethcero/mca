package es.ethcero.mca.worker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.ethcero.mca.grpc.ToUpperCaseRequest;
import es.ethcero.mca.grpc.ToUpperCaseResponse;
import es.ethcero.mca.grpc.ToUpperCaseServiceGrpc;
import es.ethcero.mca.worker.models.Task;
import net.devh.boot.grpc.client.inject.GrpcClient;
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

    @GrpcClient("toUpperCaseServer")
    private ToUpperCaseServiceGrpc.ToUpperCaseServiceBlockingStub grpcClient;

    ObjectMapper objectMapper = new ObjectMapper();


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

        task.setResult(toUpperCase(task.getText()));
        publish(task);
    }

    private String toUpperCase(String text) {
        ToUpperCaseRequest request = ToUpperCaseRequest.newBuilder()
                .setText(text)
                .build();
        ToUpperCaseResponse response = grpcClient.toUpperCase(request);
        return response.getResult();
    }

    private void publish( Object value) throws JsonProcessingException {
        System.out.println("publishToQueue: '" + value + "'");
        rabbitTemplate.convertAndSend("tasksProgress", objectMapper.writeValueAsString(value));
    }
}