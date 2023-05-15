package org.jeecg.modules.demo.equipment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.demo.equipment.entity.EquipmentAlarm;
import org.jeecg.modules.demo.equipment.mapper.EquipmentAlarmMapper;
import org.jeecg.modules.demo.equipment.service.IEquipmentAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 设备报警表
 * @Author: jeecg-boot
 * @Date:   2023-03-28
 * @Version: V1.0
 */
@Service
public class EquipmentAlarmServiceImpl extends ServiceImpl<EquipmentAlarmMapper, EquipmentAlarm> implements IEquipmentAlarmService {
    @Override
    public IPage<EquipmentAlarm> selectList(Page<EquipmentAlarm> page, QueryWrapper<EquipmentAlarm> queryWrapper) {
        return null;
    }

//    @Autowired
//    private EquipmentRecordMapper equipmentRecordMapper;


}
