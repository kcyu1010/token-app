package com.kcyu.springtoken.service;

import com.kcyu.springtoken.entity.Token;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kcyu
 * @since 2021-09-07
 */
public interface TokenService extends IService<Token> {

    Token findTokenByName(String name);

    List<Token> findAllToken();

    int updateToken(String token,String who);

    List<Token> findAllUsableToken();

    int updateIsCheck(String who,int status);
}
