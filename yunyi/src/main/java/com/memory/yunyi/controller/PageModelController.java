package com.memory.yunyi.controller;

import com.memory.yunyi.entity.PageModel;
import com.memory.yunyi.service.PageModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping
public class PageModelController {

    @Autowired
    private PageModelService pageModelService;

    /**
     * 添加模板，没有用到，准备移除
     *
     * @param pageModel
     * @return
     */
    @PostMapping("/add")
    public String addPageModel(PageModel pageModel) {
        if (pageModelService.pageModelExists(pageModel)) {
            return "modelAdd";
        } else {
            pageModelService.addPageModel(pageModel);
            return "redirect:/modelList";
        }
    }

    /**
     * 删除模板，无实际作用，准备移除
     *
     * @param pageModelID 模板id
     * @param model
     * @return
     */
    @GetMapping("/deleteModel/{pageModelID}")
    public String deletePageModel(@PathVariable("pageModelID") Integer pageModelID, Model model) {
        pageModelService.deletePageModel(pageModelID);
        List<PageModel> list = pageModelService.getAllPageModel();
        model.addAttribute("pageModels", list);
        return "redirect:/modelList";
    }

    /**
     * 获取模板信息展示
     *
     * @param model
     * @return
     */
    @GetMapping("modelList")
    public String getAllPageModel(Model model) {
        List<PageModel> list = pageModelService.getAllPageModel();
        model.addAttribute("pageModels", list);
        return "modelList";
    }

    /**
     * 进入添加模板页面，无用，准备移除
     *
     * @return
     */
    @GetMapping("modelAdd")
    public String modelAddPage() {
        return "modelAdd";
    }

}
