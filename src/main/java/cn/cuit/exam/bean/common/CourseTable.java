package cn.cuit.exam.bean.common;

<<<<<<< HEAD
=======
import cn.cuit.exam.bean.Class;
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
import cn.cuit.exam.mapper.ClassroomMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseTable {

    @Autowired
    private ClassroomMapper classroomMapper;

    public static final List<CourseSection> table = new ArrayList<>();
    public static final List<ClassroomSection> classrooms = new ArrayList<>();

    public static final Map<String, Integer> classnameToId = new HashMap<>();

    /**
     * 从.csv文件中获取课程表数据
     */
    public static void importCourseTable(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String instr = "";

            while ((instr = reader.readLine()) != null) {
                String[] strs = instr.split(",");
                if (strs.length < 6) return;

                CourseSection cs = new CourseSection();
                ClassroomSection crs = new ClassroomSection(new int[140][170]);

                //获取课程数据
                cs.setClassAbbr(strs[0]);
                cs.setClassname(strs[1]);
                cs.setTname(strs[2]);
                cs.setStWeek(Integer.parseInt(strs[3]));
                cs.setEdWeek(Integer.parseInt(strs[4]));
                cs.setWeekday(Integer.parseInt(strs[5]));
                cs.setSite(strs[6]);

                //节次数
                List<Integer> occ = new ArrayList<>();
                for (int i = 7; i < strs.length; ++i) occ.add(Integer.parseInt(strs[i]));

                cs.setOccupy(occ);

                //根据课程表更新教室情况
                for (int i = cs.getStWeek(); i <= cs.getEdWeek(); ++i) {
                    for (int st : cs.getOccupy()) {
                        //更新教室使用情况
                        crs.update(i, cs.getWeekday(), st, 9);
                    }
                }

                //实现教室的按名存取
                int id; //id为教室在classrooms里的编号
                if (classnameToId.containsKey(cs.getClassname())) {
                    id = classnameToId.get(cs.getClassname());
                } else {
                    id = classrooms.size();
                    classrooms.add(crs);
                    classnameToId.put(cs.getClassname(), id);
                }

                table.add(cs);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在！");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(table);
    }

}
