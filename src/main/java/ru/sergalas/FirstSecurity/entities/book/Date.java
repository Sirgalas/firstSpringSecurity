package ru.sergalas.FirstSecurity.entities.book;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor()
@ToString
@Embeddable
public class Date {

    private LocalDate date_receiving;

    @Transient
    private final Integer expire_day = 10;

    @Transient
    private boolean expired;

    public Date(LocalDate date_receiving)
    {
        this.date_receiving = date_receiving;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(date_receiving.plusDays(this.expire_day));
    }

    public void setExpired(boolean expired) {
        this.expired =expired;
    }
}
