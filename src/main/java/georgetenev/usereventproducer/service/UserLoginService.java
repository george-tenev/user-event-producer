package georgetenev.usereventproducer.service;

import georgetenev.usereventproducer.dto.UserLogin;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import georgetenev.usereventproducer.config.KafkaProducerConfig;

import org.springframework.stereotype.Service;

@Service
public class UserLoginService {
    private final String TOPIC = "logins";

    private final KafkaTemplate<String, UserLogin> kafkaTemplate;
    private final KafkaProducerConfig<String, UserLogin> kafkaConfig;

    @Autowired
    public UserLoginService(KafkaProducerConfig<String, UserLogin> kafkaConfig) {
        this.kafkaConfig = kafkaConfig;
        this.kafkaTemplate = kafkaConfig.kafkaTemplate();
    }

    public void sendMessage(UserLogin userLoginMessage) {
        // Assuming UserLogin class has a getId() method to retrieve the id
        String key = userLoginMessage.getId(); // Directly use the getId() method to get the key

        if (key != null) {
            // Use the 'id' as the key and the UserLogin object as the value
            // HashMap<String>
            kafkaTemplate.send(TOPIC, key, userLoginMessage);
        }
    }
}

