package dev.prasadgaikwad.example.kafkastreams.processor;

import dev.prasadgaikwad.example.kafkastreams.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * This class provides the processor which reads events from source topic, filters those and sends filtered events to destination topic.
 */
@Slf4j
@Component
public class StreamEventsProcessor {

    @Bean
    public Function<KStream<String, Event>, KStream<String, Event>> eventsProcessor() {
        return kStream -> kStream.filter((key, event) -> {
            log.info("Processor received event: {}", event);
            return !event.getType().equals("skipped-event");
        });
    }
}
