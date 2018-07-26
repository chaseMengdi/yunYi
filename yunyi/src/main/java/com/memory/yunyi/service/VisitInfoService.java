package com.memory.yunyi.service;

import com.memory.yunyi.entity.VisitInfo;

import java.awt.*;
import java.util.List;

/*
 * by陈曦
 */

public interface VisitInfoService {

    List<VisitInfo> DescByLike();
    List<VisitInfo> DescByReport();
    List<VisitInfo> ListByHometown(String hometown);
    VisitInfo findById(Integer id);
    VisitInfo incLike(Integer id);
    VisitInfo decLike(Integer id);
    VisitInfo incReport(Integer id);
    VisitInfo incComment(Integer id);
    VisitInfo add(VisitInfo v);
}
