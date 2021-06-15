package cn.cuit.exam.mapper;

import org.mapstruct.Mapper;

@Mapper
public interface MajorMapper {

    String getMshortByMno(String mno);

}
