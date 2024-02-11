package georgetenev.usereventproducer.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {

    private String bootstrapServers;

    private HashMap<String, TopicProperties> topics;

    public String getBootstrapServers() {
        return this.bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }


    public HashMap<String, TopicProperties> getTopics() {
        return this.topics;
    }

    public void setTopics(HashMap<String, TopicProperties> topics) {
        this.topics = topics;
    }

    public static class TopicProperties {
        private int partitions;
        private short replicationFactor;

        public int getPartitions() {
            return this.partitions;
        }

        public void setPartitions(int partitions) {
            this.partitions = partitions;
        }

        public short getReplicationFactor() {
            return this.replicationFactor;
        }

        public void setReplicationFactor(short replicationFactor) {
            this.replicationFactor = replicationFactor;
        }
    }

}