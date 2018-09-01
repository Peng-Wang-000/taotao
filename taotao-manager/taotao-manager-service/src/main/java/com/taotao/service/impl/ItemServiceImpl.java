package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;

/**
 * 商品管理Service
 * 
 * @author Wait
 *
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public TbItem getItemById(long itemId) {

		// 直接查询
		// return itemMapper.selectByPrimaryKey(itemId);
		// 通过查询条件查询
		TbItemExample itemExample = new TbItemExample();
		Criteria criteria = itemExample.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(itemExample);

		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 查询商品分页数据
	 */
	@Override
	public EUDataGridResult getItemList(int page, int rows) {

		// sql条件
		TbItemExample itemExample = new TbItemExample();

		// 分页
		PageHelper.startPage(page, rows);
		// 获得分页数据
		List<TbItem> list = itemMapper.selectByExample(itemExample);

		// 组装结果
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		result.setTotal(pageInfo.getTotal());

		return result;
	}

	/**
	 * 新增商品信息
	 * 
	 * @throws Exception
	 */
	public TaotaoResult createItem(TbItem item, String desc, String itemParams) throws Exception {
		// item补全
		// 生成商品ID
		long itemId = IDUtils.genItemId();
		item.setId(itemId);
		// 商品状态 1-正常 2-下架 3-删除
		item.setStatus((byte) 1);

		// 设置创建与更新时间
		item.setCreated(new Date());
		item.setUpdated(new Date());
		
		// 插入商品到数据库
		itemMapper.insert(item);
		// 插入商品描述到数据库
		TaotaoResult result = insertItemDesc(itemId, desc);
		
		if (result.getStatus() != 200) {
			throw new Exception("新增商品信息出错！");
		}
		//插入商品规格参数到数据库
		result = inserItemParamItem(itemId, itemParams);
		if (result.getStatus() != 200) {
			throw new Exception("新增商品參數信息出错！");
		}
		return TaotaoResult.ok();
	}

	/**
	 * 保存商品的描述信息
	 * 
	 * @param itemId
	 * @param desc
	 * @return TaotaoResult
	 */
	private TaotaoResult insertItemDesc(Long itemId, String desc) {
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		Date date = new Date();
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		int effRow = itemDescMapper.insert(itemDesc);
		if (effRow > 0)
			return TaotaoResult.ok();
		else
			return null;
	}

	private TaotaoResult inserItemParamItem(Long itemId, String itemParam) {

		// 创建一个pojo
		TbItemParamItem itemParamItem = new TbItemParamItem();
		Date date = new Date();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		itemParamItem.setCreated(date);
		itemParamItem.setUpdated(date);
		// 向表中插入数据
		int effRows = itemParamItemMapper.insert(itemParamItem);
		if (effRows > 0)
			return TaotaoResult.ok(itemParamItem);
		else
			return null;
	}

}
