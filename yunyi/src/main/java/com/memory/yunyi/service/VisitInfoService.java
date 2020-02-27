package com.memory.yunyi.service;

import com.memory.yunyi.entity.VisitInfo;

import java.util.List;


public interface VisitInfoService {

    List<VisitInfo> DescByLike();

    List<VisitInfo> DescByReport();

    List<VisitInfo> infoListByHometown(String hometown);

    VisitInfo findByOpenId(String openId);

    VisitInfo incLike(String id);

    VisitInfo decLike(String id);

    VisitInfo incReport(String id);

    VisitInfo incComment(String openId);

    VisitInfo add(VisitInfo v);

    List<VisitInfo> getAllVisitInfos();
}
