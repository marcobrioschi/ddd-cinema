package cinema.domain;

import lombok.Value;

import java.util.Date;

@Value
public class SchedulingTime {
    Date date;

    public SchedulingTime(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
