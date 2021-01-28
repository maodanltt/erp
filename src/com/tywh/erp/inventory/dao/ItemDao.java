package com.tywh.erp.inventory.dao;

import com.tywh.erp.bean.Condition;
import com.tywh.erp.bean.Item;

import java.util.List;
import java.util.Map;

public interface ItemDao {

    //��ѯͼ���б�
    List<Item> queryItemList(Condition condition) throws Exception;

//    //�����Ų�ѯ��Ʒ��������
//    Map<String, Integer> queryXscs(Condition condition);

    //�����Ų�ѯ����������
    Integer queryZxscs(Condition condition) throws Exception;

    //������ ͼ������� ���� �ڼ� ���ܸ����ڼ��ڳ�����ĩ���
    Map<String, Integer> queryKucun() throws Exception;


}
