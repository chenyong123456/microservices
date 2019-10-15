package cn.knowimage.pojo;

/**
 * 功能:分页插件的实体类
 * @author 啊勇
 */
public class Table {
    public Integer page;
    public  Integer rows;

    public Table() {
        super();
    }

    public Table(Integer page, Integer rows) {
        this.page = page;
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
