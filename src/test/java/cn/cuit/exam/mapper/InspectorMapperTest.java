package cn.cuit.exam.mapper;

import cn.cuit.exam.bean.Inspector;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InspectorMapperTest {

    @Autowired
    private InspectorMapper inspectorMapper;

    @Test
    void test1() {
        //System.out.println(inspectorMapper.insertInspector(new Inspector("ajx", "wx", 0, "H2101")));
    }


}
