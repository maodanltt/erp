package com.tywh.erp.inventory.dao;

import com.tywh.erp.bean.Item;

import java.util.List;
import java.util.Map;

public interface ItemDao {

    //��ѯͼ���б�
    List<Item> queryItemList(String bmmc);

    //��ѯ�ڳ����
    Map<String, Integer> queryQckc();

    //��ѯ��ĩ���
    Map<String, Integer> queryQmkc();

    //�����Ų�ѯ��Ʒ��������
    Map<String, Integer> queryXscs();

    //�����Ų�ѯ����������
    Integer queryZxscs();

}
