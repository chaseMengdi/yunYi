package com.memory.yunyi.service;

import java.util.List;
import com.memory.yunyi.entity.VisitInfo;
import com.memory.yunyi.repository.VisitInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

/*
 * by陈曦
 */

@Service
public class VisitInfoServiceImpl implements VisitInfoService{

    @Autowired
    private VisitInfoRepository visitInfoRepository;


    @Override
    public List<VisitInfo> DescByLike() {
        Sort sort = new Sort(Sort.Direction.DESC, "likeNum");
        return  visitInfoRepository.findAll(sort);
//        return  visitInfoRepository.findAll();
    }


    @Override
    public List<VisitInfo> DescByReport() {
        Sort sort = new Sort(Sort.Direction.DESC, "reportNum");
        return visitInfoRepository.findAll(sort);
    }

    @Override
    public List<VisitInfo> ListByHometown(String hometown) {
        return visitInfoRepository.listByHometown(hometown);
    }

    @Override
    public VisitInfo findById(Integer id) {
        return visitInfoRepository.findById(id).get();
    }

    @Override
    public VisitInfo incLike(Integer id) {
        VisitInfo tmp = visitInfoRepository.findById(id).get();
        tmp.setLikeNum(tmp.getLikeNum()+1);
        return visitInfoRepository.save(tmp);
    }

    @Override
    public VisitInfo decLike(Integer id) {
        VisitInfo tmp = visitInfoRepository.findById(id).get();
        tmp.setLikeNum(tmp.getLikeNum()-1);
        return visitInfoRepository.save(tmp);
    }

    @Override
    public VisitInfo incReport(Integer id) {
        VisitInfo tmp = visitInfoRepository.findById(id).get();
        tmp.setReportNum(tmp.getReportNum()+1);
        return visitInfoRepository.save(tmp);
    }

    @Override
    public VisitInfo incComment(Integer id) {
        VisitInfo tmp = visitInfoRepository.findById(id).get();
        tmp.setCommentNum(tmp.getCommentNum()+1);
        return visitInfoRepository.save(tmp);
    }

    @Override
    public VisitInfo add(VisitInfo v) {
        return visitInfoRepository.save(v);
    }


}
