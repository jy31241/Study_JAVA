package net.skhu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("book")
public class BookController {

    @Autowired BookRepository bookRepository;
    @Autowired CategoryRepository categoryRepository;

    @RequestMapping("list")
    public String list(Pagination pagination, Model model) {
        List<Book> list = bookRepository.findAll(pagination);
        model.addAttribute("list", list);
        return "book/list";
    }

    @RequestMapping(value="edit", method=RequestMethod.GET)
    public String edit(@RequestParam("id") int id, Pagination pagination, Model model) {
        model.addAttribute("categorys", categoryRepository.findAll());
        model.addAttribute("book", bookRepository.findOne(id));
        model.addAttribute("title", "수정");
        return "book/edit";
    }

    @RequestMapping(value="edit", method=RequestMethod.POST)
    public String edit(Book book, Pagination pagination, Model model) {
        bookRepository.save(book);
        model.addAttribute("categorys", categoryRepository.findAll());
        model.addAttribute("message", "저장했습니다.");
        return "book/edit";
    }

    @RequestMapping(value="create", method=RequestMethod.GET)
    public String create(Pagination pagination, Model model) {
        model.addAttribute("categorys", categoryRepository.findAll());
        model.addAttribute("book", new Book());
        model.addAttribute("title", "등록");
        return "book/edit";
    }

    @RequestMapping(value="create", method=RequestMethod.POST)
    public String create(Book book, Pagination pagination, Model model) {
        bookRepository.save(book);
        long recordCount = bookRepository.count();
        long pageCount = (recordCount + pagination.getSz() - 1) / pagination.getSz();
        return "redirect:list?pg=" + pageCount;
    }

    @RequestMapping("delete")
    public String delete(@RequestParam("id") int id, Pagination pagination, Model model) {
        bookRepository.delete(id);
        return "redirect:list?pg=" + pagination.getPg();
    }
}
