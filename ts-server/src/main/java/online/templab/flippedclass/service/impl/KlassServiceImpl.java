package online.templab.flippedclass.service.impl;

import online.templab.flippedclass.dao.KlassDao;
import online.templab.flippedclass.dao.KlassStudentDao;
import online.templab.flippedclass.dao.StudentDao;
import online.templab.flippedclass.dao.TeamDao;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.mapper.KlassMapper;
import online.templab.flippedclass.mapper.KlassStudentMapper;
import online.templab.flippedclass.service.KlassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Klass 业务 实现类
 *
 * @author jh
 */
@Service
public class KlassServiceImpl implements KlassService {

    @Autowired
    private KlassDao klassDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private KlassStudentDao klassStudentDao;

    @Autowired
    private TeamDao teamDao;

    @Override
    public Boolean insert(Klass klass) {
        return klassDao.insert(klass);
    }

    @Override
    public Boolean delete(Long id) {
        return klassDao.delete(id);
    }

    @Override
    public Boolean update(Klass klass) {
        return klassDao.update(klass);
    }

    @Override
    public Klass get(Long id) {
        return klassDao.select(id);
    }

    @Override
    public List<Klass> listByCourseId(Long courseId) {
        return klassDao.selectByCourseId(courseId);
    }

    @Override
    public Boolean resetStudentList(Long id, List<Student> students) {
        int count = 0;
        Long courseId = klassDao.select(id).getCourseId();
        List<KlassStudent> klassStudents = klassStudentDao.select(new KlassStudent().setKlassId(id));
        //取出存在新名单中的旧学生和新学生
        List<KlassStudent> oldStudents = new LinkedList<>();
        List<Student> newStudents = new LinkedList<>();
        for (Student student : students) {
            KlassStudent temp = klassStudentDao.selectOneByStudentNum(id, student.getStudentNum());
            if (temp != null) {
                oldStudents.add(temp);
            } else {
                newStudents.add(student);
            }
        }
        //删除该班级的旧表
        klassStudentDao.delete(new KlassStudent().setKlassId(id));
        //插入名单中的新学生，并判断如果没有该学生则创建
        for (Student student : newStudents) {
            //未有账号学生
            if (studentDao.selectByStudentNum(student.getStudentNum()) == null) {
                studentDao.insert(student);
                count += klassStudentDao.insert(new KlassStudent()
                        .setStudentId(student.getId())
                        .setKlassId(id)
                        .setCourseId(courseId));
            }
            //有账号学生
            else {
                count += klassStudentDao.insert(new KlassStudent()
                        .setStudentId(studentDao.selectByStudentNum(student.getStudentNum()).getId())
                        .setKlassId(id)
                        .setCourseId(courseId));
            }
        }
        //插入名单中的旧学生
        for (KlassStudent klassstudent : oldStudents) {
            count+=klassStudentDao.insert(klassstudent);
        }
        for(KlassStudent klassStudent:klassStudents){
            //如果不在新表中删除队伍信息
            if(!oldStudents.contains(klassStudent)){
                if(klassStudent.getTeamId()!=null) {
                    teamDao.deleteMemberById(teamDao.selectByKlassIdAndStudentId(id,klassStudent.getStudentId()),klassStudent.getStudentId());
                }
            }
        }
        return count == students.size();
    }

    @Override
    public List<Klass> listByStudentId(Long studentId) {
        return klassDao.selectByStudentId(studentId);
    }

    @Override
    public Boolean setStudentList(Long id, List<Student> students) {
        int count = 0;
        Long courseId = klassDao.select(id).getCourseId();
        //删除该班级的旧表
        klassStudentDao.delete(new KlassStudent().setKlassId(id));
        //插入名单中的新学生，并判断如果没有该学生则创建
        for (Student student : students) {
            //未有账号学生
            if (studentDao.selectByStudentNum(student.getStudentNum()) == null) {
                studentDao.insert(student);
                count += klassStudentDao.insert(new KlassStudent()
                        .setStudentId(student.getId())
                        .setKlassId(id)
                        .setCourseId(courseId));
            }
            //有账号学生
            else {
                count += klassStudentDao.insert(new KlassStudent()
                        .setStudentId(studentDao.selectByStudentNum(student.getStudentNum()).getId())
                        .setKlassId(id)
                        .setCourseId(courseId));
            }
        }
        return count == students.size();
    }
}
