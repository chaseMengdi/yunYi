package com.memory.yunyi.service;

import com.memory.yunyi.entity.PageModel;

import java.util.List;
import java.util.Optional;

//by张一帆
public interface PageModelService {

    List<PageModel> getAllPageModel();

    boolean pageModelExists(PageModel pageModel);

    PageModel addPageModel(PageModel pageModel);

    void deletePageModel(Integer pageModelID);

    Optional<PageModel> getPageModelByID(Integer pageModelID);

    PageModel inc(Integer id);
    PageModel dec(Integer id);
}
