package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.skhu.dto.Student;
//메소드 실행 할 때 디비 칼럼 제목과 자바객체 set 메소드 이름이 일치해야한다

@Mapper
public interface StudentMapper {
    Student findOne(int id);
    Student findByStudentNumber(String studentNumber);
    List<Student> findAll();
    void insert(Student student);
    void update(Student student);
    void delete(int id);
}

