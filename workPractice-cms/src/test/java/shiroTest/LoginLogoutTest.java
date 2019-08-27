package shiroTest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
public class LoginLogoutTest {

    @Test
    public void testHelloworld() {
        //1、获取SecurityManager工厂,此处使用ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:iniConfig/shiro.ini");
        //2、得到SecurityManager实例并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3、得到Subject及创建用户名/密码身份验证Token(即用户身份/凭证)
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            //4、登录,即身份验证
            subject.login(token);
            System.out.println("登录成功");
        } catch (AuthenticationException e) {
            //5、身份验证失败
            System.out.println("登录失败");
        }
        //6、退出
        subject.logout();
        System.out.println("退出");
    }

    @Test
    public void testCustomRealm() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:iniConfig/shiro-realm.ini");

        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try{
            subject.login(token);
        }catch(AuthenticationException e){
            e.printStackTrace();
        }

        Assert.assertEquals(true, subject.isAuthenticated());

        subject.logout();
    }

    @Test
    public void testIsPermitted() {
        login("classpath:iniConfig/shiro-permission.ini","zhang","123");
        Assert.assertTrue(subject().isPermitted("user:create"));
        Assert.assertTrue(subject().isPermittedAll("user:update", "user:delete"));
        Assert.assertFalse(subject().isPermitted("user:view"));
    }

    @Test(expected = UnauthorizedException.class)
    public void testCheckPermission() {
        login("classpath:iniConfig/shiro-permission.ini","zhang","123");
        subject().checkPermission("user:create");
        subject().checkPermissions("user:delete","user:update");
        subject().checkPermissions("user:view");
    }

    protected void login(String configFile, String username, String password) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
    }

    protected Subject subject() {
        return SecurityUtils.getSubject();
    }

    private void test1() {
        subject().checkPermissions("system:user:update");
        //用户拥有资源 system:user 的 update操作权限
        //单个资源多个权限
        subject().checkPermissions("system:user:update","system:user:delete");
        //拥有拥有资源system:user 的 update和 delete权限，可以简写为：
        subject().checkPermissions("system:user:update,delete");
        //单个资源的所有权限
        //如下判断
        subject().checkPermissions("system:user:*");
        //或者
        subject().checkPermissions("system:user");
        //单个实例多个权限
        subject().checkPermissions("user:delete,update:1");

    }
}
