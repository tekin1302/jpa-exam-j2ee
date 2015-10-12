package ro.tekin.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import java.util.List;

/**
 * Created by tekin.omer on 10/12/2015.
 */
@Entity
public class PrintQueue {
    @Id
    private Integer id;

    @OrderColumn(name = "PRINT_ORDER") // default is entityName_ORDER, in this case: PRINTJOB_ORDER
    @OneToMany(mappedBy = "printQueue")
    private List<PrintJob> printJobs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<PrintJob> getPrintJobs() {
        return printJobs;
    }

    public void setPrintJobs(List<PrintJob> printJobs) {
        this.printJobs = printJobs;
    }
}
