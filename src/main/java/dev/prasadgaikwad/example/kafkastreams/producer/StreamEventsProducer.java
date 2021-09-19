package dev.prasadgaikwad.example.kafkastreams.producer;

import dev.prasadgaikwad.example.kafkastreams.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.function.Supplier;

/**
 * This class provides supplier which produces simple events onto kafka topic as input. Refer application.yaml for topic configuration.
 */
@Slf4j
@Component
public class StreamEventsProducer {
    private static final Random random = new Random();

    @Bean
    public Supplier<Event> eventsProducer() {
        return () -> {
            int randomInt = random.nextInt(100000);
            Event event;
            if (randomInt % 2 == 0) {
                event = new Event(Integer.toString(randomInt), "skipped-event", "This event should be SKIPPED!");
            } else {
                event = new Event(Integer.toString(randomInt), "consumed-event", "This event should be CONSUMED by final consumer!");
            }
            log.info("Producing event: {}", event);
            return event;
        };
    }
}
