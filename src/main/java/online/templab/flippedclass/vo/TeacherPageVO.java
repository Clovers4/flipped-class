package online.templab.flippedclass.vo;

import online.templab.flippedclass.entity.Teacher;

import java.util.List;

/**
 * @author wk
 */
//TODO
public class TeacherPageVO {

    private boolean newFilter;

    private int fromIndex;

    private int sumPage;

    private int page;

    private List<Teacher> teachers;

    public boolean isNewFilter() {
        return newFilter;
    }

    public void setNewFilter(boolean newFilter) {
        this.newFilter = newFilter;
    }

    public int getFromIndex() {
        return fromIndex;
    }

    public void setFromIndex(int fromIndex) {
        this.fromIndex = fromIndex;
    }

    public int getSumPage() {
        return sumPage;
    }

    public void setSumPage(int sumPage) {
        this.sumPage = sumPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
