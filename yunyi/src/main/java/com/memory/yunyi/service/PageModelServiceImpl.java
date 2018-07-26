package com.memory.yunyi.service;

import com.memory.yunyi.entity.PageModel;
import com.memory.yunyi.repository.PageModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//by张一帆
@Service
public class PageModelServiceImpl implements PageModelService {

    @Autowired
    private PageModelRepository pageModelRepository;

    @Override
    public List<PageModel> getAllPageModel() {
        return pageModelRepository.findAll();
    }

   public boolean pageModelExists(PageModel pageModel){
        if(pageModelRepository.existsById(pageModel.getPageModelID())){
            return true;
        }else{
            return false;
        }
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
    public void deletePageModel(Integer pageModelID) {pageModelRepository.deleteById(pageModelID);}


    @Override
    public PageModel inc(Integer id) {
        PageModel tmp = pageModelRepository.findById(id).get();
        tmp.setUsageAmount(tmp.getUsageAmount()+1);
        return pageModelRepository.save(tmp);
    }

    @Override
    public PageModel dec(Integer id) {
        PageModel tmp = pageModelRepository.findById(id).get();
        tmp.setUsageAmount(tmp.getUsageAmount()-1);
        return pageModelRepository.save(tmp);
    }
}
