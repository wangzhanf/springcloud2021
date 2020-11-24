package vip.epss.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @创建人 epss[wangzhanf]
 * @创建时间 2020/11/24 0024
 * @描述
 */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Accessors(chain = true)    //支持链式编程写法
public class Department implements Serializable {
    private Integer did;
    private String dname;
    private String dbSource;//数据所在的数据库

    public Department(String dname) {
        this.dname = dname;
    }

    public Department() {
    }

    public Department(Integer did, String dname, String dbSource) {
        this.did = did;
        this.dname = dname;
        this.dbSource = dbSource;
    }

    @Override
    public String toString() {
        return "Department{" +
                "did=" + did +
                ", dname='" + dname + '\'' +
                ", dbSource='" + dbSource + '\'' +
                '}';
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDbSource() {
        return dbSource;
    }

    public void setDbSource(String dbSource) {
        this.dbSource = dbSource;
    }
}
