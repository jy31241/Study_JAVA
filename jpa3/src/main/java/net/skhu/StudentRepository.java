package net.skhu;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer>  {

    public default List<Student> findAll(Pagination pagination) {
        PageRequest pageRequest = new PageRequest(
                pagination.getPg() - 1, pagination.getSz(), Sort.Direction.ASC, "id");
        Page<Student> page = this.findAll(pageRequest);
        pagination.setRecordCount((int)page.getTotalElements());
        return page.getContent();
    }
}