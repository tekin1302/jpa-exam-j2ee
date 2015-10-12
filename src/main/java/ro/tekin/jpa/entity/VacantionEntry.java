package ro.tekin.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

/**
 * Created by tekin.omer on 10/12/2015.
 */
@Embeddable
public class VacantionEntry {
    @Temporal(TemporalType.DATE)
    private Calendar startDate;

    @Column(name = "DAYS")
    private int daysTaken;

    public VacantionEntry(Calendar startDate, int daysTaken) {
        this.startDate = startDate;
        this.daysTaken = daysTaken;
    }

    public VacantionEntry() {
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public int getDaysTaken() {
        return daysTaken;
    }

    public void setDaysTaken(int daysTaken) {
        this.daysTaken = daysTaken;
    }
}
