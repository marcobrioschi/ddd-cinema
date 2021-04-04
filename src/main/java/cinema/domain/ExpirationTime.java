package cinema.domain;

import lombok.Value;

import java.util.Date;

@Value
public class ExpirationTime {
    Date date;

    public ExpirationTime(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
