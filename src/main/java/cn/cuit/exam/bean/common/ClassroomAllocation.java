package cn.cuit.exam.bean.common;

import cn.cuit.exam.bean.Classroom;
import lombok.Data;

import java.util.Calendar;
import java.util.List;

@Data
public class ClassroomAllocation {

    Calendar start;
    Calendar end;

    List<Classroom> classrooms;

    public ClassroomAllocation(Calendar start, Calendar end, List<Classroom> classrooms) {
        this.start = start;
        this.end = end;
        this.classrooms = classrooms;
    }

    public String toString() {
        return start.get(Calendar.HOUR) + " " + start.get(Calendar.MINUTE) + " "
                + classrooms.toString();
    }

    public ClassroomAllocation() { }
}
