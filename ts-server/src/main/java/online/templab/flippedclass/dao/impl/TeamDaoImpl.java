package online.templab.flippedclass.dao.impl;

import online.templab.flippedclass.dao.TeamDao;
import online.templab.flippedclass.entity.*;
import online.templab.flippedclass.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author fj
 */
@Component
public class TeamDaoImpl implements TeamDao {


    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private KlassStudentMapper klassStudentMapper;

    @Autowired
    private KlassMapper klassMapper;

    @Autowired
    MemberLimitStrategyMapper memberLimitStrategyMapper;

    @Autowired
    ConflictCourseStrategyMapper conflictCourseStrategyMapper;

    @Autowired
    CourseMemberLimitStrategyMapper courseMemberLimitStrategyMapper;

    @Autowired
    TeamAndStrategyMapper teamAndStrategyMapper;

    @Autowired
    TeamOrStrategyMapper teamOrStrategyMapper;

    @Autowired
    TeamStrategyMapper teamStrategyMapper;

    @Autowired
    TeamStudentMapper teamStudentMapper;

    @Autowired
    KlassTeamMapper klassTeamMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    TeamValidApplicationMapper teamValidApplicationMapper;

    void deleteSubStrategy(CourseStrategy courseStrategy) {

        List<CourseStrategy> courseStrategyList = courseStrategy.getCourseStrategyList();

        if (courseStrategy instanceof TeamStrategy || courseStrategy instanceof TeamOrStrategy || courseStrategy instanceof TeamAndStrategy) {
            if ("ConflictCourseStrategy".equals(courseStrategy.getMyStrategyName())) {
                for (int i = 0; i < courseStrategyList.size(); ++i) {
                    CourseStrategy courseStrategy1 = courseStrategyList.get(i);
                    conflictCourseStrategyMapper.delete(new ConflictCourseStrategy()
                            .setId(courseStrategy1.getMyId())
                            .setCourseId(courseStrategy1.getMyCourseId())
                    );
                }
            } else if ("TeamAndStrategy".equals(courseStrategy.getMyStrategyName())) {
                for (int i = 0; i < courseStrategyList.size(); ++i) {
                    CourseStrategy courseStrategy1 = courseStrategyList.get(i);
                    teamAndStrategyMapper.delete(new TeamAndStrategy()
                            .setId(courseStrategy1.getMyId())
                            .setStrategyId(courseStrategy1.getMyStrategyId())
                            .setStrategyName(courseStrategy1.getMyStrategyName()));
                }
            } else if ("TeamOrStrategy".equals(courseStrategy.getMyStrategyName())) {
                for (int i = 0; i < courseStrategyList.size(); ++i) {
                    CourseStrategy courseStrategy1 = courseStrategyList.get(i);
                    teamOrStrategyMapper.delete(new TeamOrStrategy()
                            .setId(courseStrategy1.getMyId())
                            .setStrategyId(courseStrategy1.getMyStrategyId())
                            .setStrategyName(courseStrategy1.getMyStrategyName()));
                }
            } else if ("CourseMemberLimitStrategy".equals(courseStrategy.getMyStrategyName())) {
                for (int i = 0; i < courseStrategyList.size(); ++i) {
                    CourseStrategy courseStrategy1 = courseStrategyList.get(i);
                    courseMemberLimitStrategyMapper.delete(new CourseMemberLimitStrategy()
                            .setId(courseStrategy1.getMyId())
                            .setMin(courseStrategy1.getMyMin())
                            .setMax(courseStrategy1.getMyMax())
                            .setCourseId(courseStrategy1.getMyCourseId()));
                }
            } else if ("MemberLimitStrategy".equals(courseStrategy.getMyStrategyName())) {
                for (int i = 0; i < courseStrategyList.size(); ++i) {
                    CourseStrategy courseStrategy1 = courseStrategyList.get(i);
                    memberLimitStrategyMapper.delete(new MemberLimitStrategy()
                            .setId(courseStrategy1.getMyId())
                            .setMin(courseStrategy1.getMyMin())
                            .setMax(courseStrategy1.getMyMax()));
                }
            }

            for (int j = 0; j < courseStrategyList.size(); ++j) {
                deleteSubStrategy(courseStrategyList.get(j));
            }

        }
        return;

    }

    void insertSubStrategy(CourseStrategy courseStrategy) {

        List<CourseStrategy> courseStrategyList = courseStrategy.getCourseStrategyList();

        if (courseStrategy instanceof TeamStrategy || courseStrategy instanceof TeamOrStrategy || courseStrategy instanceof TeamAndStrategy) {
            if ("ConflictCourseStrategy".equals(courseStrategy.getMyStrategyName()) && courseStrategyList.size() != 0) {
                ConflictCourseStrategy conflictCourseStrategy = new ConflictCourseStrategy().setCourseId(courseStrategyList.get(0).getMyCourseId());
                conflictCourseStrategyMapper.insert(conflictCourseStrategy);
                System.out.println(conflictCourseStrategy);
                courseStrategyList.get(0).setMyId(conflictCourseStrategy.getId());
                courseStrategy.setMyStrategyId(conflictCourseStrategy.getId());
                System.out.println(courseStrategy);
                for (int i = 1; i < courseStrategyList.size(); ++i) {
                    conflictCourseStrategyMapper.insert(new ConflictCourseStrategy()
                            .setId(courseStrategy.getMyStrategyId())
                            .setCourseId(courseStrategyList.get(i).getMyCourseId()));
                }
            } else if ("TeamAndStrategy".equals(courseStrategy.getMyStrategyName()) && courseStrategyList.size() != 0) {
                for (int j = 0; j < courseStrategyList.size(); ++j) {
                    insertSubStrategy(courseStrategyList.get(j));
                }

                TeamAndStrategy teamAndStrategy = new TeamAndStrategy().
                        setStrategyId(courseStrategyList.get(0).getCourseStrategyList().get(0).getMyId())
                        .setStrategyName(courseStrategyList.get(0).getMyStrategyName());
                teamAndStrategyMapper.insert(teamAndStrategy);
                System.out.println("1111"+teamAndStrategy);
                courseStrategyList.get(0).setMyId(teamAndStrategy.getId());
                courseStrategy.setMyStrategyId(teamAndStrategy.getId());
                for (int i = 1; i < courseStrategyList.size(); ++i) {
                    teamAndStrategyMapper.insert(new TeamAndStrategy()
                            .setId(courseStrategy.getMyStrategyId())
                            .setStrategyId(courseStrategyList.get(i).getCourseStrategyList().get(0).getMyId())
                            .setStrategyName(courseStrategyList.get(i).getMyStrategyName()));
                }
            } else if ("TeamOrStrategy".equals(courseStrategy.getMyStrategyName()) && courseStrategyList.size() != 0) {
                for (int j = 0; j < courseStrategyList.size(); ++j) {
                    insertSubStrategy(courseStrategyList.get(j));
                }

                TeamOrStrategy teamOrStrategy = new TeamOrStrategy().setStrategyId(courseStrategyList.get(0).getCourseStrategyList().get(0).getMyId())
                        .setStrategyName(courseStrategyList.get(0).getMyStrategyName());
                teamOrStrategyMapper.insert(teamOrStrategy);
                System.out.println("1111"+teamOrStrategy);
                courseStrategyList.get(0).setMyId(teamOrStrategy.getId());
                courseStrategy.setMyStrategyId(teamOrStrategy.getId());
                for (int i = 1; i < courseStrategyList.size(); ++i) {
                    teamOrStrategyMapper.insert(new TeamOrStrategy()
                            .setId(courseStrategy.getMyStrategyId())
                            .setStrategyId(courseStrategyList.get(i).getCourseStrategyList().get(0).getMyId())
                            .setStrategyName(courseStrategyList.get(i).getMyStrategyName()));
                }
            } else if ("CourseMemberLimitStrategy".equals(courseStrategy.getMyStrategyName()) && courseStrategyList.size() != 0) {
                CourseMemberLimitStrategy courseMemberLimitStrategy = new CourseMemberLimitStrategy()
                        .setMin(courseStrategyList.get(0).getMyMin())
                        .setMax(courseStrategyList.get(0).getMyMax())
                        .setCourseId(courseStrategyList.get(0).getMyCourseId());
                courseMemberLimitStrategyMapper.insert(courseMemberLimitStrategy);
                System.out.println("dao:"+courseMemberLimitStrategy);
                courseStrategyList.get(0).setMyId(courseMemberLimitStrategy.getId());
                courseStrategy.setMyStrategyId(courseMemberLimitStrategy.getId());
                System.out.println(courseStrategy);
                for (int i = 1; i < courseStrategyList.size(); ++i) {
                    courseMemberLimitStrategyMapper.insert(new CourseMemberLimitStrategy()
                            .setId(courseStrategy.getMyStrategyId())
                            .setMin(courseStrategyList.get(i).getMyMin())
                            .setMax(courseStrategyList.get(i).getMyMax())
                            .setCourseId(courseStrategyList.get(i).getMyCourseId()));
                }
            } else if ("MemberLimitStrategy".equals(courseStrategy.getMyStrategyName()) && courseStrategyList.size() != 0) {
                MemberLimitStrategy memberLimitStrategy = new MemberLimitStrategy()
                        .setMin(courseStrategyList.get(0).getMyMin())
                        .setMax(courseStrategyList.get(0).getMyMax());
                memberLimitStrategyMapper.insert(memberLimitStrategy);
                courseStrategyList.get(0).setMyId(memberLimitStrategy.getId());
                courseStrategy.setMyStrategyId(memberLimitStrategy.getId());
                for (int i = 1; i < courseStrategyList.size(); ++i) {
                    memberLimitStrategyMapper.insert(new MemberLimitStrategy()
                            .setId(courseStrategy.getMyStrategyId())
                            .setMin(courseStrategyList.get(i).getMyMin())
                            .setMax(courseStrategyList.get(i).getMyMax()));
                }

            }
        }
        return;

    }

    void getSubStrategy(CourseStrategy courseStrategy) {

        if (courseStrategy instanceof TeamStrategy || courseStrategy instanceof TeamOrStrategy || courseStrategy instanceof TeamAndStrategy) {
            String strategyName = courseStrategy.getMyStrategyName();
            CourseStrategy subCourseStrategy = null;
            List<CourseStrategy> courseStrategyList = new LinkedList<>();

            if ("TeamAndStrategy".equals(strategyName)) {
//               subCourseStrategy = new TeamAndStrategy()
//                                .setStrategyId(courseStrategy.getMyStrategyId());
//                courseStrategyList = teamAndStrategyMapper.select(subCourseStrategy );
                List<TeamAndStrategy> teamAndStrategyList = teamAndStrategyMapper.select(new TeamAndStrategy().setId(courseStrategy.getMyStrategyId()));
                for (int i = 0; i < teamAndStrategyList.size(); ++i) {
                    courseStrategyList.add(teamAndStrategyList.get(i));
                }
            } else if ("TeamOrStrategy".equals(strategyName)) {
//                subCourseStrategy = new TeamOrStrategy()
//                        .setStrategyId(courseStrategy.getMyStrategyId());
//                courseStrategyList = teamOrStrategyMapper.select(subCourseStrategy );

                List<TeamOrStrategy> teamOrStrategyList = teamOrStrategyMapper.select(new TeamOrStrategy().setId(courseStrategy.getMyStrategyId()));
                for (int i = 0; i < teamOrStrategyList.size(); ++i) {
                    courseStrategyList.add(teamOrStrategyList.get(i));
                }
            } else if ("MemberLimitStrategy".equals(strategyName)) {
                List<MemberLimitStrategy> memberLimitStrategyList = memberLimitStrategyMapper.select(new MemberLimitStrategy().setId(courseStrategy.getMyStrategyId()));
                for (int i = 0; i < memberLimitStrategyList.size(); ++i) {
                    courseStrategyList.add(memberLimitStrategyList.get(i));
                }
            } else if ("CourseMemberLimitStrategy".equals(strategyName)) {
                List<CourseMemberLimitStrategy> courseMemberLimitStrategyList = courseMemberLimitStrategyMapper.select(new CourseMemberLimitStrategy().setId(courseStrategy.getMyStrategyId()));
                for (int i = 0; i < courseMemberLimitStrategyList.size(); ++i) {
                    courseStrategyList.add(courseMemberLimitStrategyList.get(i));
                }
            } else if ("ConflictCourseStrategy".equals(strategyName)) {
                List<ConflictCourseStrategy> conflictCourseStrategyList = conflictCourseStrategyMapper.select(new ConflictCourseStrategy().setId(courseStrategy.getMyStrategyId()));
                for (int i = 0; i < conflictCourseStrategyList.size(); ++i) {
                    courseStrategyList.add(conflictCourseStrategyList.get(i));
                }
            }
//                courseStrategyMapper = (CourseStrategyMapper) Class.forName(CourseStrategyMapper.class.getPackage().getName() +"." +strategyMapperName ).newInstance();
//                subCourseStrategy = (CourseStrategy) Class.forName(CourseStrategy.class.getPackage().getName() +"." +courseStrategy.getMyStrategyName() ).newInstance();
//                subCourseStrategy.setMyStrategyName(courseStrategy.getMyStrategyName());
//                subCourseStrategy.setMyStrategyId(courseStrategy.getMyStrategyId());

            for (int j = 0; j < courseStrategyList.size(); ++j) {
                getSubStrategy(courseStrategyList.get(j));
            }

            courseStrategy.setCourseStrategyList(courseStrategyList);
        }
        return;
    }

    @Override
    public Team selectTeamValid(Long teamId) {

        Team team = teamMapper.selectByTeamId(teamId);
        team.setAllStudents(studentMapper.selectTeamMerberCourseIdByTeamId(teamId));
        List<TeamStrategy> teamStrategyList = team.getTeamStrategyList();
        for (int i = 0; i < teamStrategyList.size(); ++i) {
            TeamStrategy teamStrategy = teamStrategyList.get(i);
            getSubStrategy(teamStrategy);
        }
        return team;
    }

    @Override
    public List<TeamStrategy> selectTeamValidByCourseId(Long courseId) {
        Course course = courseMapper.selectTeamStrategyListByCourseId(courseId);
        List<TeamStrategy> teamStrategyList = course.getTeamStrategyList();
        for (int i = 0; i < teamStrategyList.size(); ++i) {
            TeamStrategy teamStrategy = teamStrategyList.get(i);
            getSubStrategy(teamStrategy);
        }
        return course.getTeamStrategyList();
    }

    @Override
    public Long deleteTeamStratgyList(Long courseId) {
        List<TeamStrategy> teamStrategyListPast = selectTeamValidByCourseId(courseId);

        for (int i = 0; i < teamStrategyListPast.size(); ++i) {
            TeamStrategy teamStrategy = teamStrategyListPast.get(i);
            deleteSubStrategy(teamStrategy);

            teamStrategyMapper.delete(teamStrategy);
        }

        return 1L;
    }

    @Override
    public Long insertTeamStratgyList(List<TeamStrategy> teamStrategyList) {
        if (teamStrategyList != null) {
            //先删就分组规则
            deleteTeamStratgyList(teamStrategyList.get(0).getCourseId());

            //新增分组规则
            for (int i = 0; i < teamStrategyList.size(); ++i) {
                TeamStrategy teamStrategy = teamStrategyList.get(i);
                Integer maxStrategySerial = teamStrategyMapper.getMaxStrategySerial();
                teamStrategy.setStrategySerial(maxStrategySerial + 1);

                insertSubStrategy(teamStrategy);
                if(teamStrategy.getCourseStrategyList().size()!=0){
                    teamStrategy.setStrategyId(teamStrategy.getCourseStrategyList().get(0).getMyId());

                    System.out.println("teamStrategy"+teamStrategy);
                    teamStrategyMapper.insert(teamStrategy);
                }



            }
        }

        return 1L;
    }

    @Override
    public List<Team> selectByCourseId(Long courseId) {
        //找到所有klass
        List<Long> klassIds = klassMapper.selectIdByCourseId(courseId);
        List<Team> resultTeam = new LinkedList<>();
        for (Long klassId : klassIds) {
            Klass klass = klassMapper.selectByPrimaryKey(klassId);
            List<KlassTeam> klassTeams = klassTeamMapper.select(new KlassTeam().setKlassId(klassId));
            for (KlassTeam klassTeam : klassTeams) {
                List<Student> students = studentMapper.selectStudentsByKlassIdTeamId(klassId, klassTeam.getTeamId());
                Team team = teamMapper.selectOne(new Team().setId(klassTeam.getTeamId()));
                team.setKlass(klass);
                List<Student> memberStudent = new LinkedList<>();
                Student leader = null;
                if (team.getLeaderId() != null) {
                    leader = studentMapper.selectByPrimaryKey(team.getLeaderId());
                }
                for (Student student : students) {
                    if (!student.getId().equals(team.getLeaderId())) {
                        memberStudent.add(student);
                    }
                }
                //如果组长不空直接加入
                if (leader != null) {
                    team.setLeader(leader);
                } else {
                    team.setLeader(new Student().setStudentName("无").setStudentNum("无"));
                }
                team.setStudents(memberStudent);
                resultTeam.add(team);
            }
        }
        return resultTeam;
    }

    @Override
    public List<Student> selectUnTeamedStudentByCourseId(Long courseId) {
        return studentMapper.selectUnTeamedStudentByCourseId(courseId);
    }

    @Override
    public Team selectTeam(Long courseId, Long studentId) {
        // 获取队伍id
        KlassTeam klassTeam = klassTeamMapper.selectByCourseIdAndStudentId(courseId, studentId);
        if (klassTeam == null) {
            return null;
        }
        // 获取队伍成员
        List<Student> member = studentMapper.selectTeamMerberCourseIdByTeamId(klassTeam.getTeamId());
        // 通过 teamId 获取 team
        Team team = teamMapper.selectByPrimaryKey(klassTeam.getTeamId());
        // 队长
        Student leader = new Student();
        for (int i = 0; i < member.size(); i++) {
            if (member.get(i).getId().equals(team.getLeaderId())) {
                leader = member.get(i);
                member.remove(i);
                break;
            }
        }
        Klass klass = klassMapper.selectByPrimaryKey(team.getKlassId());
        // 组装 team
        team.setLeader(leader)
                .setStudents(member)
                .setKlass(klass)
                .setKlassSerial((byte) 1);
        System.out.println(team);
        return team;
    }

    @Override
    public Boolean deleteMemberById(Long teamId, Long studentId) {
        Team team = teamMapper.selectByPrimaryKey(teamId);
        if (team == null) {
            return true;
        }
        // 如果这个人是队长 ， 那么需要删除整个组
        if (studentId.equals(team.getLeaderId())) {
            int deleteTeam = teamMapper.deleteByPrimaryKey(team.getId());
            int deleteRelation = teamStudentMapper.delete(new TeamStudent().setTeamId(teamId));
            Long klassId = klassStudentMapper.selectOne(new KlassStudent().setStudentId(studentId).setCourseId(team.getCourseId())).getKlassId();
            int deleteKlassTeam = klassTeamMapper.delete(new KlassTeam().setTeamId(teamId).setKlassId(klassId));
            return (deleteRelation + deleteTeam + deleteKlassTeam) > 0;
        } else {
            int deleteRelation = teamStudentMapper.delete(new TeamStudent().setStudentId(studentId).setTeamId(teamId));
            return deleteRelation == 1;
        }
    }

    @Override
    public Long insert(Team team) {
        // 插入队伍
        int lineTeam = teamMapper.insert(team
                .setKlassSerial((byte) klassMapper.selectByPrimaryKey(team.getKlassId()).getSerial().intValue())
                .setSerial(teamMapper.getMaxTeamSerial(team.getCourseId(), team.getKlassId()) + 1)
                .setStatus(0));
        List<Long> studentPrimaryKeyList = new ArrayList<>();
        studentPrimaryKeyList.add(team.getLeaderId());
        // 插入 klass_student 表关系
        int lineKlassStudent = teamStudentMapper.insertList(team.getId(), studentPrimaryKeyList);
        // 插入 klass_team 表关系
        int lineKlassTeam = klassTeamMapper.insert(new KlassTeam().setTeamId(team.getId()).setKlassId(team.getKlassId()));
        return team.getId();
    }

    @Override
    public Boolean deleteByStudentNum(Long teamId, String studentNum) {
        Student student = studentMapper.selectOne(new Student().setStudentNum(studentNum));
        int line = teamStudentMapper.delete(new TeamStudent().setTeamId(teamId).setStudentId(student.getId()));
        return line == 1;
    }

    @Override
    public Boolean updateByStudentNum(Long teamId, List<String> studentNum) {
        // 找学生所有id
        List<Long> studentIdList = new ArrayList<>();
        for (String studentAccount : studentNum) {
            Student student = studentMapper.selectOne(new Student().setStudentNum(studentAccount));
            studentIdList.add(student.getId());
        }
        // 获取 courseId 和 klassId
        int line = teamStudentMapper.insertList(teamId, studentIdList);
        return line == studentIdList.size();
    }

    @Override
    public Boolean update(Team team) {
        return teamMapper.updateByPrimaryKeySelective(team) > 0;
    }

    @Override
    public Boolean delete(Long teamId, Long studentId) {
        int deleteTeam = teamMapper.deleteByPrimaryKey(teamId);
        int deleteRelation = teamStudentMapper.delete(new TeamStudent().setTeamId(teamId));
        int deleteKlassTeam = klassTeamMapper.delete(new KlassTeam().setTeamId(teamId));
        return (deleteTeam + deleteRelation + deleteKlassTeam) > 0;
    }

    @Override
    public Long selectByKlassIdAndStudentId(Long klassId, Long studentId) {
        return klassTeamMapper.selectByKlassIdAndStudentId(klassId, studentId).getTeamId();
    }

    @Override
    public Team selectByPrimaryKey(Long id) {
        return teamMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TeamStudent> selectTeamStudentByTeamId(Long teamId) {
        return teamStudentMapper.select(new TeamStudent().setTeamId(teamId));
    }

    @Override
    public Boolean insertTeamValidApplication(TeamValidApplication teamValidApplication) {
        //设置队伍状态为审核中
        Team team = teamMapper.selectByPrimaryKey(teamValidApplication.getTeamId());
        team = selectTeam(team.getCourseId(), team.getLeaderId());
        if (team.getStudents().size() > 5) {
            teamMapper.updateByPrimaryKeySelective(new Team().setId(teamValidApplication.getTeamId()).setStatus(0));
            return true;
        }
        teamMapper.updateByPrimaryKeySelective(new Team().setId(teamValidApplication.getTeamId()).setStatus(2));
        return teamValidApplicationMapper.insert(teamValidApplication) == 1;
    }

    @Override
    public List<TeamValidApplication> selectTeamApplicationByTeacherId(Long teacherId) {
        List<TeamValidApplication> result = new LinkedList<>();
        List<TeamValidApplication> teamValidApplications = teamValidApplicationMapper.selectTeamApplicationByTeacherId(teacherId);
        for (TeamValidApplication teamValidApplication : teamValidApplications) {
            teamValidApplication.setTeam(teamValidApplication.getTeam()
                    .setStudents(studentMapper.selectTeamMerberCourseIdByTeamId(teamValidApplication.getTeamId())));
            result.add(teamValidApplication);
        }
        return result;
    }

    @Override
    public Boolean updateTeamValidApplication(Long teamValidApplicationId, Long teamId, Boolean accept) {
        int line = 0;
        //更新队伍信息
        if (accept) {
            teamMapper.updateByPrimaryKeySelective(new Team().setId(teamId).setStatus(1));
            line += teamValidApplicationMapper.updateByPrimaryKeySelective(new TeamValidApplication().setId(teamValidApplicationId).setStatus(1));
        } else {
            teamMapper.updateByPrimaryKeySelective(new Team().setId(teamId).setStatus(0));
            line += teamValidApplicationMapper.updateByPrimaryKeySelective(new TeamValidApplication().setId(teamValidApplicationId).setStatus(0));
        }
        return line == 1;
    }
}
