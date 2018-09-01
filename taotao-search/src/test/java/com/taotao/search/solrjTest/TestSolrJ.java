package com.taotao.search.solrjTest;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * 测试使用solrJ来连接solr服务器
 * @author Wait
 *
 */
public class TestSolrJ {

	/**
	 * 添加,修改索引文档
	 * 其中修改的实质是：先删除再插入，以Id为依据
	 * @throws SolrServerException
	 * @throws IOException
	 */
	@Test
	public void addDocument() throws SolrServerException, IOException {

		// 创建一个链接
		SolrServer solrServer = new HttpSolrServer("http://192.168.127.66:8080/solr");
		// 创建一个文档对象
		SolrInputDocument document = new SolrInputDocument();
		// 添加域
		document.addField("id", "test002");
		document.addField("item_title", "商品标题");
		document.addField("item_price", 123);
		// 把文档对象导入索引库
		solrServer.add(document);

		// 提交
		solrServer.commit();
	}
	/**
	 * 删除索引文档
	 * @throws SolrServerException
	 * @throws IOException
	 */
	@Test
	public void deleteDocument() throws SolrServerException, IOException {
		
		// 创建一个链接
		SolrServer solrServer = new HttpSolrServer("http://192.168.127.66:8080/solr");
		// 创建一个文档对象
//		solrServer.deleteById("test001");
		solrServer.deleteByQuery("*:*");
		// 提交
		solrServer.commit();
	}
	/**
	 * 查询测试
	 * @throws Exception
	 */
	@Test
	public void queryDocument() throws Exception {
		SolrServer solrServer = new HttpSolrServer("http://192.168.127.66:8080/solr");
		//创建一个查询对象
		SolrQuery query = new SolrQuery();
		//设置查询条件
		query.setQuery("*:*");
		query.setStart(20);
		query.setRows(50);
		//执行查询
		QueryResponse response = solrServer.query(query);
		//取查询结果
		SolrDocumentList solrDocumentList = response.getResults();
		
		System.out.println("共查询到记录：" + solrDocumentList.getNumFound());
		for (SolrDocument solrDocument : solrDocumentList) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));
		}
	}

}
