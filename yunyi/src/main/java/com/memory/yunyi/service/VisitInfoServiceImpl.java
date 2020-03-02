package com.memory.yunyi.service;

import com.memory.yunyi.entity.VisitInfo;
import com.memory.yunyi.repository.VisitInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VisitInfoServiceImpl implements VisitInfoService {

    @Autowired
    private VisitInfoRepository visitInfoRepository;


    @Override
    public List<VisitInfo> DescByLike() {
        Sort sort = new Sort(Sort.Direction.DESC, "likeNum");
        return visitInfoRepository.findAll(sort);
//        return  visitInfoRepository.findAll();
    }


    @Override
    public List<VisitInfo> DescByReport() {
        Sort sort = new Sort(Sort.Direction.DESC, "reportNum");
        return visitInfoRepository.findAll(sort);
    }

    @Override
    public List<VisitInfo> infoListByHometown(String hometown) {
        return visitInfoRepository.pageListByHometown(hometown);
    }

    @Override
    public VisitInfo findByOpenId(String id) {
        return visitInfoRepository.findByOpenId(id);
    }

    @Override
    public VisitInfo incLike(String id) {
        VisitInfo tmp = visitInfoRepository.findByOpenId(id);
        tmp.setLikeNum(tmp.getLikeNum() + 1);
        return visitInfoRepository.save(tmp);
    }

    @Override
    public VisitInfo decLike(String id) {
        VisitInfo tmp = visitInfoRepository.findByOpenId(id);
        tmp.setLikeNum(tmp.getLikeNum() - 1);
        return visitInfoRepository.save(tmp);
    }

    @Override
    public VisitInfo incReport(String id) {
        VisitInfo tmp = visitInfoRepository.findByOpenId(id);
        tmp.setReportNum(tmp.getReportNum() + 1);
        return visitInfoRepository.save(tmp);
    }

    @Override
    public VisitInfo incComment(String openId) {
        VisitInfo tmp = visitInfoRepository.findByOpenId(openId);
        tmp.setCommentNum(tmp.getCommentNum() + 1);
        return visitInfoRepository.save(tmp);
    }

    @Override
    public VisitInfo add(VisitInfo v) {
        return visitInfoRepository.save(v);
    }

    @Override
    public List<VisitInfo> getAllVisitInfos() {
        return visitInfoRepository.findAll();
    }
}
