package net.skhu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired StudentRepository studentRepository;
    @Autowired DepartmentRepository departmentRepository;

    @RequestMapping("list")
    public String list(Pagination pagination, Model model) {
        List<Student> list = studentRepository.findAll(pagination);
        model.addAttribute("list", list);
        return "student/list";
    }

    @RequestMapping(value="edit", method=RequestMethod.GET)
    public String edit(@RequestParam("id") int id, Pagination pagination, Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("student", studentRepository.findOne(id));
        model.addAttribute("title", "수정");
        return "student/edit";
    }

    @RequestMapping(value="edit", method=RequestMethod.POST)
    public String edit(Student student, Pagination pagination, Model model) {
        studentRepository.save(student);
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("message", "저장했습니다.");
        return "student/edit";
    }

    @RequestMapping(value="create", method=RequestMethod.GET)
    public String create(Pagination pagination, Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        model.addAttribute("student", new Student());
        model.addAttribute("title", "등록");
        return "student/edit";
    }

    @RequestMapping(value="create", method=RequestMethod.POST)
    public String create(Student student, Pagination pagination, Model model) {
        studentRepository.save(student);
        long recordCount = studentRepository.count();
        long pageCount = (recordCount + pagination.getSz() - 1) / pagination.getSz();
        return "redirect:list?pg=" + pageCount;
    }

    @RequestMapping("delete")
    public String delete(@RequestParam("id") int id, Pagination pagination, Model model) {
        studentRepository.delete(id);
        return "redirect:list?pg=" + pagination.getPg();
    }
}