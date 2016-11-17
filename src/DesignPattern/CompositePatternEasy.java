package DesignPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. ������:�ܵ꣬�ֵ꣬���˵�...
 * 2. �������е���Ϊ�ǣ�ˢ��Ա��...
 * 3. ����֮���в�ι�ϵ���ܵ����зֵꡢ�ֵ��¿���ӵ�м��˵ꡣ
 * */

abstract class Market {
	String name;

	public abstract void add(Market m);

	public abstract void remove(Market m);

	public abstract void PayByCard();
}

// �ֵ� ��������м��˵�
class MarketBranch extends Market {
	// ���˵��б�
	List<Market> list = new ArrayList<Market>();

	public MarketBranch(String s) {
		this.name = s;
	}

	@Override
	public void add(Market m) {
		// TODO Auto-generated method stub
		list.add(m);
	}

	@Override
	public void remove(Market m) {
		// TODO Auto-generated method stub
		list.remove(m);
	}

	// ����֮�󣬸÷ֵ��µļ��˵��Զ��ۼӻ���
	@Override
	public void PayByCard() {
		// TODO Auto-generated method stub
		System.out.println(name + "����,�������ۼ���û�Ա��");
		for (Market m : list) {
			m.PayByCard();
		}
	}
}

// ���˵� ���治���зֵ�ͼ��˵꣬��ײ�
class MarketJoin extends Market {
	public MarketJoin(String s) {
		this.name = s;

	}

	@Override
	public void add(Market m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Market m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void PayByCard() {
		// TODO Auto-generated method stub
		System.out.println(name + "����,�������ۼ���û�Ա��");
	}
}

public class CompositePatternEasy {

	public static void main(String[] args) {

		MarketBranch rootBranch = new MarketBranch("�ܵ�");
		MarketBranch qhdBranch = new MarketBranch("�ػʵ��ֵ�");
		MarketJoin hgqJoin = new MarketJoin("�ػʵ��ֵ�һ���������˵�");
		MarketJoin btlJoin = new MarketJoin("�ػʵ��ֵ����������˵�");

		qhdBranch.add(hgqJoin);
		qhdBranch.add(btlJoin);
		rootBranch.add(qhdBranch);
		rootBranch.PayByCard();
	}
}
