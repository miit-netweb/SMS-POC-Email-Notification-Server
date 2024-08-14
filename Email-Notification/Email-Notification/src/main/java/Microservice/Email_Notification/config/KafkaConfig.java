package Microservice.Email_Notification.config;

import Microservice.Email_Notification.dto.EmailStatus;
import Microservice.Email_Notification.dto.MessageDto;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic jsonNewTopic(){
            return TopicBuilder.name("email-status").build();
    }

    @Bean
    public KafkaTemplate<String, EmailStatus> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ProducerFactory<String, EmailStatus> producerFactory(){
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configs.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);  // Optional: if you do not want type information in headers
//        configs.put(JsonSerializer.TYPE_MAPPINGS, "Microservice.dashboard_service.*");
        return new DefaultKafkaProducerFactory<>(configs);
    }

}
