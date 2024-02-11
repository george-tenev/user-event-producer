package georgetenev.usereventproducer;

import georgetenev.usereventproducer.config.KafkaProperties;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class UserEventProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserEventProducerApplication.class, args);
	}
	@Autowired
    KafkaProperties kafkaProperties;
	@Bean
	public ApplicationRunner runner(KafkaAdmin kafkaAdmin) {
		return args -> {
			HashMap<String, KafkaProperties.TopicProperties> topics = kafkaProperties.getTopics();
			List<NewTopic> newTopics = new ArrayList<>();

			topics.forEach((topicName, topicConfig) -> {
				NewTopic newTopic = new NewTopic(topicName, topicConfig.getPartitions(), topicConfig.getReplicationFactor());
				newTopics.add(newTopic);
			});			// build list
			// create topics
			NewTopic[] topicsArray = newTopics.toArray(new NewTopic[0]);

			kafkaAdmin.createOrModifyTopics(topicsArray);
		};
	}

}
