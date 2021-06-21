package cn.cuit.exam.service;

import cn.cuit.exam.bean.Absence;

public interface AbsenceService {

    int insertAbsence(Absence absence);

    int handleAbsence(Absence absence);

}
