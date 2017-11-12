package net.skhu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("salesitem")
public class SalesitemController {
	@Autowired SalesitemRepository salesitemRepository;
    @Autowired ItemtypeRepository itemtypeRepository;

    @RequestMapping("list")
    public String list(Pagination pagination, Model model) {
        List<Salesitem> list = salesitemRepository.findAll(pagination);
        model.addAttribute("list", list);
        return "salesitem/list";
    }

    @RequestMapping(value="edit", method=RequestMethod.GET)
    public String edit(@RequestParam("id") int id, Pagination pagination, Model model) {
        model.addAttribute("itemtypes", itemtypeRepository.findAll());
        model.addAttribute("salesitem", salesitemRepository.findOne(id));
        model.addAttribute("title", "수정");
        return "salesitem/edit";
    }

    @RequestMapping(value="edit", method=RequestMethod.POST)
    public String edit(Salesitem salesitem, Pagination pagination, Model model) {
        salesitemRepository.save(salesitem);
        model.addAttribute("itemtypes", itemtypeRepository.findAll());
        model.addAttribute("message", "저장했습니다.");
        return "salesitem/edit";
    }

    @RequestMapping(value="create", method=RequestMethod.GET)
    public String create(Pagination pagination, Model model) {
        model.addAttribute("itemtypes", itemtypeRepository.findAll());
        model.addAttribute("salesitem", new Salesitem());
        model.addAttribute("title", "등록");
        return "salesitem/edit";
    }

    @RequestMapping(value="create", method=RequestMethod.POST)
    public String create(Salesitem salesitem, Pagination pagination, Model model) {
        salesitemRepository.save(salesitem);
        long recordCount = salesitemRepository.count();
        long pageCount = (recordCount + pagination.getSz() - 1) / pagination.getSz();
        return "redirect:list?pg=" + pageCount;
    }

    @RequestMapping("delete")
    public String delete(@RequestParam("id") int id, Pagination pagination, Model model) {
        salesitemRepository.delete(id);
        return "redirect:list?pg=" + pagination.getPg();
    }
}

