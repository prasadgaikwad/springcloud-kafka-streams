package dev.prasadgaikwad.example.kafkastreams.consumer;

import dev.prasadgaikwad.example.kafkastreams.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * This class provides events consumer for filtered events which are processed by processor and sent to final topic.
 */
@Slf4j
@Component
public class StreamEventsConsumer {

    @Bean
    public Consumer<KStream<String, Event>> eventsConsumer() {
        return kStream -> kStream.foreach((key, event) -> log.info("Consumer received event: {}", event));
    }
}
