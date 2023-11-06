package domain;

public class Student {
    private String studentId;//ѧ��
    private String studentName;//�Ա�
    private String sex;//�Ա�
    private String tel;//�绰
    private String enrolldate;//ע��ʱ��
    private String classid;//�༶��
    private String college;//��Ժ

    public Student() {//�޲ι���
    }

    public Student(String studentId, String studentName, String sex, String tel, String enrolldate, String classid, String college) {//�вι���
        super();
        this.studentId = studentId;
        this.studentName = studentName;
        this.sex = sex;
        this.tel = tel;
        this.enrolldate = enrolldate;
        this.classid = classid;
        this.college = college;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEnrolldate() {
        return enrolldate;
    }

    public void setEnrolldate(String enrolldate) {
        this.enrolldate = enrolldate;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

}
