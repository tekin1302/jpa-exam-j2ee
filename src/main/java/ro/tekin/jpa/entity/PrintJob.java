package ro.tekin.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by tekin.omer on 10/12/2015.
 */
@Entity
public class PrintJob {
    @Id
    private Integer id;

    @ManyToOne
    private PrintQueue printQueue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PrintQueue getPrintQueue() {
        return printQueue;
    }

    public void setPrintQueue(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }
}
