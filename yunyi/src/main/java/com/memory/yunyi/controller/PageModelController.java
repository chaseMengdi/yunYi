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


//by张一帆
@Controller
@RequestMapping
public class PageModelController {

    @Autowired
    private PageModelService pageModelService;

    @PostMapping("/add")
    public String addPageModel(PageModel pageModel) {
        if(pageModelService.pageModelExists(pageModel)){
            return "modelAdd";
        }else {
            pageModelService.addPageModel(pageModel);
            return "redirect:/modelList";
        }
    }

    @GetMapping("/deleteModel/{pageModelID}")
    public String deletePageModel(@PathVariable("pageModelID") Integer pageModelID, Model model){
        pageModelService.deletePageModel(pageModelID);
        List<PageModel> list = pageModelService.getAllPageModel();
        model.addAttribute("pageModels",list);
        return "redirect:/modelList";
    }

    @GetMapping("modelList")
    public String getAllPageModel(Model model){
        List<PageModel> list = pageModelService.getAllPageModel();
        model.addAttribute("pageModels",list);
        return "modelList";
    }

    @GetMapping("modelAdd")
    public String modelAddPage(){
        return  "modelAdd";
    }

}
