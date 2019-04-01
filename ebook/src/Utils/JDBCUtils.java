package Utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

public class JDBCUtils {
    private static ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
    //获取数据库连接池对像
    public static DataSource getDateSource(){
        return comboPooledDataSource;
    }
}
