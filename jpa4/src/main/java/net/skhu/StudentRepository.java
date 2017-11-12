package net.skhu;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer>  {

    Option[] searchBy = { new Option(0, "검색조건"), new Option(1, "학번"),
                          new Option(2, "이름"), new Option(3, "학과명") };

    Option[] orderBy = { new Option(0, "정렬순서"), new Option(1, "학번"),
                         new Option(2, "이름"), new Option(3, "학과명") };

    Sort[] sort = { new Sort(Sort.Direction.ASC, "id"),
                    new Sort(Sort.Direction.ASC, "studentNumber"),
                    new Sort(Sort.Direction.ASC, "name"),
                    new Sort(Sort.Direction.ASC, "department_departmentName")};

    public default List<Student> findAll(Pagination pagination) {
        Pageable pageable =
                new PageRequest(pagination.getPg() - 1, pagination.getSz(), sort[pagination.getOb()]);

        Page<Student> page;
        String searchText = pagination.getSt();
        switch (pagination.getSb()) {
        case 1: page = this.findByStudentNumber(searchText, pageable); break;
        case 2: page = this.findByNameStartingWith(searchText, pageable); break;
        case 3: page = this.findByDepartment_departmentNameStartingWith(searchText, pageable); break;
        default: page = this.findAll(pageable); break;
        }
        pagination.setRecordCount((int)page.getTotalElements());
        return page.getContent();
    }

    public Page<Student> findByStudentNumber(String s, Pageable pageable);
    public Page<Student> findByNameStartingWith(String s, Pageable pageable);
    public Page<Student> findByDepartment_departmentNameStartingWith(String s, Pageable pageable);
}