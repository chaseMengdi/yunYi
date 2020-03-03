package com.memory.yunyi.serviceImpl;

import com.memory.yunyi.entity.PageModel;
import com.memory.yunyi.repository.PageModelRepository;
import com.memory.yunyi.service.PageModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PageModelServiceImpl implements PageModelService {

    @Autowired
    private PageModelRepository pageModelRepository;

    @Override
    public List<PageModel> getAllPageModel() {
        return pageModelRepository.findAll();
    }

    public boolean pageModelExists(PageModel pageModel) {
        return pageModelRepository.existsById(pageModel.getPageModelID());
    }

    @Override
    public PageModel addPageModel(PageModel pageModel) {
        return pageModelRepository.save(pageModel);
    }

    @Override
    public Optional<PageModel> getPageModelByID(Integer pageModelID) {
        return pageModelRepository.findById(pageModelID);
    }

    @Override
    public void deletePageModel(Integer pageModelID) {
        pageModelRepository.deleteById(pageModelID);
    }


    @Override
    public PageModel inc(Integer id) {
        PageModel tmp = pageModelRepository.findById(id).get();
        tmp.setUsageAmount(tmp.getUsageAmount() + 1);
        return pageModelRepository.save(tmp);
    }

    @Override
    public PageModel dec(Integer id) {
        PageModel tmp = pageModelRepository.findById(id).get();
        tmp.setUsageAmount(tmp.getUsageAmount() - 1);
        return pageModelRepository.save(tmp);
    }
}
