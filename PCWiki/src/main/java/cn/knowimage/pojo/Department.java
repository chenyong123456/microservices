package cn.knowimage.pojo;

/**
 * @author Mr.G
 * @date 2019/8/16
 */
public class Department {
    private Integer ID;
    private Integer department_code;
    private String department_name;

    public Department() {
    }

    public Department(Integer ID, Integer department_code, String department_name) {
        this.ID = ID;
        this.department_code = department_code;
        this.department_name = department_name;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getDepartment_code() {
        return department_code;
    }

    public void setDepartment_code(Integer department_code) {
        this.department_code = department_code;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }
}
