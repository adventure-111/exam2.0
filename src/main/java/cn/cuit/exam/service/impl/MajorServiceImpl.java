package cn.cuit.exam.service.impl;

import cn.cuit.exam.mapper.MajorMapper;
import cn.cuit.exam.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorMapper Majormapper;

    @Override
    public String getMshortByMno(String mno) {
        return Majormapper.getMshortByMno(mno);
    }
}
