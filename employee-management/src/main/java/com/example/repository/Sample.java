//package com.example.repository;
//
//import com.example.domain.Employee;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.core.namedparam.SqlParameterSource;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class Sample {
//    @Autowired
//    private NamedParameterJdbcTemplate template;
//    /**
//     * Memberオブジェクトを生成するローマッパー.
//     */
//    private static final RowMapper<Employee> MEMBER_ROW_MAPPER = (rs, i) -> {
//        // ここに結果の処理を書く
//        return null;
//    };
//    /**
//     * メンバー一覧情報を年齢順で取得します.
//     *
//     * @return 全メンバー一覧 メンバーが存在しない場合はサイズ0件のメンバー一覧を返します
//     */
//    public List<Employee> findAll() {
//        String sql = "ここにSQL文を書く";
//        List<Employee> employeeList = null; // ←ここに実行の処理を書く
//        return employeeList;
//    }
//    /**
//     * 主キー検索を行います.
//     *
//     * @param id 検索したい主キーの値
//     * @return メンバー情報(検索されなかった場合は非検査例外が発生します)
//     */
//    public Employee findById(Integer id) {
//        String sql = "ここにSQL文を書く";
//        SqlParameterSource param = new MapSqlParameterSource().addValue(“id”, id);
//        Employee employee = null; // ←ここに実行処理を書く
//        return employee;
//    }
//    /**
//     * メンバー情報を登録or更新します.
//     *
//     * @param employee メンバー情報
//     * @return メンバー情報
//     */
//    public Employee save(Employee employee) {
//        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
//        if (employee == null) { // ←正しい条件に修正する
//            String insertSql = "ここにSQL文を書く";
//            // ここに実行処理を書く
//        } else {
//            String updateSql = "ここにSQL文を書く";
//            // ここに実行処理を書く
//        }
//        return employee;
//    }
//}