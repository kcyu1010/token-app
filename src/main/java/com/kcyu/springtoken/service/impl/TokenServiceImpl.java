package com.kcyu.springtoken.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kcyu.springtoken.entity.Token;
import com.kcyu.springtoken.mapper.TokenMapper;
import com.kcyu.springtoken.service.TokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kcyu
 * @since 2021-09-07
 */
@Service
public class TokenServiceImpl extends ServiceImpl<TokenMapper, Token> implements TokenService {

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public boolean findKeyIsDuplicate(String Key, Object Value) {
        QueryWrapper<Token> wrapper = new QueryWrapper<>();
        wrapper.eq(Key,Value);
        List<Token> tokens = tokenMapper.selectList(wrapper);
        return !tokens.isEmpty();
    }

    @Override
    public int addToken(Token token) throws DuplicateKeyException {
        return tokenMapper.insert(token);
    }

    @Override
    public Token findTokenByName(String name) {
        QueryWrapper<Token> wrapper = new QueryWrapper<>();
        wrapper.eq("who",name);
        try {
            return tokenMapper.selectOne(wrapper);
        } catch (DataAccessException e){
            throw e;
        }

    }

    @Override
    public List<Token> findAllToken() {
        Token token = new Token();
        return token.selectAll();
    }

    @Override
    public List<Token> findAllUsableToken(){
        QueryWrapper<Token> wrapper = new QueryWrapper<>();
        wrapper.eq("isCheck",1);
        Token token = new Token();
        return token.selectList(wrapper);
    }

    @Override
    public int updateToken(String token,String who) {
        UpdateWrapper<Token> wrapper = new UpdateWrapper<>();
        wrapper.eq("who",who);
        Token token1 = new Token();
        token1.setToken(token);
        token1.setStatus(2);
        int update = tokenMapper.update(token1, wrapper);
        return update;
    }

    @Override
    public int updateIsCheck(String who,int status) {
        UpdateWrapper<Token> wrapper = new UpdateWrapper<>();
        wrapper.eq("who",who);
        Token token = new Token();
        token.setIsCheck(status);
        int update = tokenMapper.update(token,wrapper);
        return update;
    }

    @Override
    public int updateStatus(String who, int status) {
        UpdateWrapper<Token> wrapper = new UpdateWrapper<>();
        wrapper.eq("who",who);
        Token token = new Token();
        token.setStatus(status);
        int update = tokenMapper.update(token, wrapper);
        return update;
    }
}
