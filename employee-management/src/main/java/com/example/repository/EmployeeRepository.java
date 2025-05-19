package com.example.repository;

import com.example.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;
    /**
     * Memberオブジェクトを生成するローマッパー.
     */
    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setAge(rs.getInt("age"));
        employee.setDepartmentId(rs.getInt("department_id"));
        employee.setGender(rs.getString("gender"));
        return employee;
    };
    /**
     * メンバー一覧情報を年齢順で取得します.
     *
     * @return 全メンバー一覧 メンバーが存在しない場合はサイズ0件のメンバー一覧を返します
     */
    public List<Employee> findAll() {
        String sql = "SELECT " +
                "id,name,age,gender,department_id " +
                "FROM " +
                "employees " +
                ";";
        List<Employee> employeeList = template.query(sql,EMPLOYEE_ROW_MAPPER); // ←ここに実行の処理を書く
        return employeeList;
    }
    /**
     * 主キー検索を行います.
     *
     * @param id 検索したい主キーの値
     * @return メンバー情報(検索されなかった場合は非検査例外が発生します)
     */
    public Employee findById(Integer id) {
        String sql = "SELECT " +
                "id,name,age,gender,department_id " +
                "FROM " +
                "employees " +
                "WHERE " +
                "id = :id";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Employee employee = template.queryForObject(sql,param,EMPLOYEE_ROW_MAPPER); // ←ここに実行処理を書く
        return employee;
    }
    /**
     * メンバー情報を登録or更新します.
     *
     * @param employee メンバー情報
     * @return メンバー情報
     */
    public Employee save(Employee employee) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
        if (employee.getId() == null) { // ←正しい条件に修正する
            String insertSql = "INSERT INTO employees(name,age,gender,department_id) " +
                    "VALUES(:name,:age,:gender,:department_id)";
            // ここに実行処理を書く
            template.update(insertSql,param);
        } else {
            String updateSql = "UPDATE employees " +
                    "SET name = :name, age = :age, gender = :gender, department_id = :departmentId " +
                    "where id = :id;";
            // ここに実行処理を書く
            template.update(updateSql,param);
        }
        return employee;
    }
}