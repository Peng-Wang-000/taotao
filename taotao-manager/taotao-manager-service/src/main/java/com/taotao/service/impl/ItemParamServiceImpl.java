package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemParamService;

@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public TaotaoResult getItemParamByCid(long itemCatId) {

		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(itemCatId);
		// 参数模板列为大文本列
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		// 判断是否查询到结果
		if (list != null && list.size() > 0) {
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult insertItemParam(TbItemParam itemParam) {
		Date date = new Date();
		itemParam.setCreated(date);
		itemParam.setUpdated(date);
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}
	
	public EUDataGridResult getItemParamList(int page,int rows){
		//分页处理
		PageHelper.startPage(page, rows);
		//查询规格列表
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
		//取分页信息
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		//返回结果
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		
		return result;
		
	}


}