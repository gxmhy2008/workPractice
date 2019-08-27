package shiroTest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;

public class Test1 {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void testBefore() {
        simpleAccountRealm.addAccount("admin", "123456");
    }

    @Test
    public void testLogin() {
        //构建security manager执行环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        //主体提交请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("admin", "123456");//生成用于验证的口令
        subject.login(usernamePasswordToken);

        Assert.assertTrue(subject.isAuthenticated());

        //主体退出
        subject.logout();
        Assert.assertFalse(subject.isAuthenticated());

    }
}
