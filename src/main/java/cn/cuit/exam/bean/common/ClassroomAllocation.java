package cn.cuit.exam.bean.common;

import cn.cuit.exam.bean.Classroom;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Data
public class ClassroomAllocation implements Serializable {

    private Calendar start;
    private Calendar end;

    private String st;
    private String ed;

    private List<Classroom> classrooms;

    public ClassroomAllocation(Calendar start, Calendar end, List<Classroom> classrooms) {
        this.start = start;
        this.end = end;
        this.classrooms = classrooms;
        SimpleDateFormat sdf = new SimpleDateFormat("HH-mm-ss");
        st = sdf.format(start.getTime());
        ed = sdf.format(end.getTime());
    }

    public String toString() {
        return start.get(Calendar.HOUR) + " " + start.get(Calendar.MINUTE) + " "
                + classrooms.toString();
    }

    public ClassroomAllocation() { }
}
