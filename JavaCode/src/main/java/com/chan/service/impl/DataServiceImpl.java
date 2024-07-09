package com.chan.service.impl;

import com.chan.entity.Data;
import com.chan.mapper.DataMapper;
import com.chan.service.IDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chan
 * @since 2024-07-08
 */
@Service
public class DataServiceImpl extends ServiceImpl<DataMapper, Data> implements IDataService {

}
