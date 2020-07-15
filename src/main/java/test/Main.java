package test;

import java.sql.JDBCType;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
/**
 * @author ScXin
 * @date 4/26/2020 9:52 PM
 */
public class Main {


    @Autowired
    private static JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        Date date=new Date();
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dt=sp.format(date);
        String id="127.0.0.1";
        String oper_type="INSERT";
        String user_Id="admin";
        String context="this is a insert operation";
        String sql="insert into operation_log('user_id','remote_ip','op_type','op_time','content')values(id,remote_id,oper_type,oper_date,content)";
        jdbcTemplate.execute(sql);
    }
}
