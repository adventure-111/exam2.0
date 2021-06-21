package cn.cuit.exam.mapper;

<<<<<<< HEAD
<<<<<<< HEAD
import cn.cuit.exam.bean.Klass;
import org.apache.ibatis.annotations.Mapper;
=======
import cn.cuit.exam.bean.Class;
import org.mapstruct.Mapper;
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
=======
import cn.cuit.exam.bean.Class;
import org.mapstruct.Mapper;
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c

@Mapper
public interface ClassMapper {

<<<<<<< HEAD
<<<<<<< HEAD
    Klass queryByCid(int cid);

    Klass queryClassWithCidAndNum(Klass c);
=======
    Class queryByCid(int cid);

    Class queryClassWithCidAndNum(Class c);
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
=======
    Class queryByCid(int cid);

    Class queryClassWithCidAndNum(Class c);
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c

}
