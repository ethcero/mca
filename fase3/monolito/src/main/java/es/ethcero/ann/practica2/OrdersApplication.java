package es.ethcero.ann.practica2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.ethcero.ann.practica2.service.notification.NotificationService;
import es.ethcero.ann.practica2.service.notification.NotificationServiceLocal;
import es.ethcero.ann.practica2.service.notification.proxy.NotificationServiceProxy;

@SpringBootApplication
public class OrdersApplication {

	@Value("#{systemEnvironment['EXTERNAL_NOTIFICATOR'] ?: false}")
	private boolean externalNotificator;

	public static void main(String[] args) {
		SpringApplication.run(OrdersApplication.class, args);
	}

	@Bean
	public NotificationService getNotificationService() {
		return externalNotificator ?  new NotificationServiceProxy() : new NotificationServiceLocal();
	}
}
