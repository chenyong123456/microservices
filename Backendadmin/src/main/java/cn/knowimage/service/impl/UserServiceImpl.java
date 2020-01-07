package cn.knowimage.service.impl;

import cn.knowimage.mapper.UserMapper;
import cn.knowimage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int checkUser(String username, String password) {
        //数据库中密码字段是加密了
        String base = password;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        System.out.println("加密之后的密码----->:" + md5);
        String id = userMapper.checkUser(username, md5);
        System.out.println("核对是否成功----->::" + id);
        if (id!=null){
            System.out.println("核查成功!");
            //成功
            return 1;
        }else{
            return 0;
        }

    }
}
