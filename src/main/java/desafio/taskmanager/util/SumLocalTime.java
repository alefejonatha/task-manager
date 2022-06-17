package desafio.taskmanager.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class SumLocalTime {

    public LocalTime sum(LocalTime x, LocalTime y) {
        return x.plusHours(y.getHour()).plusMinutes(y.getMinute());
    }
}
