//package lyw.demo.security;
//
//import lyw.demo.pojo.User;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
////构建用于登录的安全用户
//public class SecurityUser extends User implements UserDetails {
//
//    public SecurityUser(User user) {
//        if(user != null){
//            this.setUid(user.getUid());
//            this.setUsername(user.getUsername());
//            this.setPassword(user.getUsername());
//        }
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        String username = this.getUsername();
//        if(!StringUtils.isBlank(username)){
//            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(username);
//            grantedAuthorities.add(simpleGrantedAuthority);
//        }
//        return grantedAuthorities;
//    }
//
////    账户是否未过期，过期无法验证
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
////    指定用户是否未过期，过期无法验证
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
////是否已过期的用户的凭据（密码），过期的凭据防止认证
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
////    是否可用，禁用的用户不能身份验证
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
