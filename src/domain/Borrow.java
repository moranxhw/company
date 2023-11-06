package domain;


public class Borrow {
    private int mainId;//����
    private int studentId;//ѧ��ѧ��
    private String studentName;//ѧ������
    private int id;//��id
    private String bookName;//����
    private String createtime;//����ʱ��
    private String returntime;//�黹ʱ��
    private int isBorrow;//0��ʾ�黹��1��ʾ����

    public Borrow() {

    }

    public Borrow(int studentId, String studentName, int id, String bookName, String createtime, String returntime, int isBorrow, int mainId) {
        super();
        this.mainId = mainId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.id = id;
        this.bookName = bookName;
        this.createtime = createtime;
        this.returntime = returntime;
        this.isBorrow = isBorrow;
    }

    public int getMainId() {
        return mainId;
    }

    public void setMainId(int mainId) {
        this.mainId = mainId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getReturntime() {
        return returntime;
    }

    public void setReturntime(String returntime) {
        this.returntime = returntime;
    }

    public int getIsBorrow() {
        return isBorrow;
    }

    public void setIsBorrow(int isBorrow) {
        this.isBorrow = isBorrow;
    }

}
