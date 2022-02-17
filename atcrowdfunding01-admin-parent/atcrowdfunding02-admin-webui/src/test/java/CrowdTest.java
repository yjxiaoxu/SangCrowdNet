

import com.yjxiaoxu.crowd.entity.Admin;
import com.yjxiaoxu.crowd.entity.Role;
import com.yjxiaoxu.crowd.mapper.AdminMapper;
import com.yjxiaoxu.crowd.mapper.RoleMapper;
import com.yjxiaoxu.crowd.service.api.AdminService;
import com.yjxiaoxu.crowd.util.CrowdUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * ClassName:CrowdTest
 * Package:PACKAGE_NAME
 * Description:定义一个测试类用来测试连接数据库是否成功
 *
 * @Date:2021/8/6 18:19
 * @Author:小许33058485@qq.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})
public class CrowdTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleMapper roleMapper;
    @Test
    public void testInsertAdmin() {
        //private String loginAcct;
        //
        //    private String userPswd;
        //
        //    private String userName;
        //
        //    private String email;
        //
        //    private String createTime;
        Admin admin = new Admin(null, "zs", CrowdUtil.md5("123"), "张三", "123@qq.com", "2021-08-19 ");
        int count = adminMapper.insert(admin);
        System.out.println("受影响的条数=" + count);
    }
    @Test
    public void testService() {
        for (int i = 0; i < 100; i++) {
            Admin admin = new Admin(null, "cq" + (i+1), "123", "陈七" + (i+1), "22222@qq.com"+ (i+1), "2021-08-13");
            adminService.saveAdmin(admin);
        }
        System.out.println("插入的记录条数为==============");
    }
    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
    @Test
    public void testAddRole() {
        for (int i = 0; i < 100; i++) {
            roleMapper.insert(new Role(null, "角色"+ (i+1)));
        }
    }
}
