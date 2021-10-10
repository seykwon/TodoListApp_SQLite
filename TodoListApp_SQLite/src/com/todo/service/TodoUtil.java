package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {

	public static void createItem(TodoList l) {

		String title, category, desc, due_date;
		Scanner sc = new Scanner(System.in);

		System.out.print("[�׸� �߰�]\n" + "ī�װ� > ");
		category = sc.next();

		sc.nextLine();
		System.out.print("���� > ");
		title = sc.nextLine().trim();

		if (l.isDuplicate(title)) {
			System.out.println("������ �ߺ��˴ϴ�!");
			return;
		}

		System.out.print("���� > ");
		desc = sc.nextLine().trim();

		System.out.print("��������(yyyy/mm/dd) > ");
		due_date = sc.nextLine().trim();

		TodoItem t = new TodoItem(category, title, desc, due_date);

		if (l.addItem(t) > 0)
			System.out.println("�߰��Ǿ����ϴ�.");
	}

	public static void deleteItem(TodoList l) {

		Scanner sc = new Scanner(System.in);

		System.out.print("[�׸� ����]\n" + "������ �׸��� ��ȣ�� �Է��Ͻÿ� > ");
		int i = sc.nextInt();

		if(l.deleteItem(i) > 0)
			System.out.println("�����Ǿ����ϴ�.");
	}

	public static void updateItem(TodoList l) {

		String new_category, new_title, new_description, new_due_date;
		Scanner sc = new Scanner(System.in);

		System.out.print("[�׸� ����]\n" + "������ �׸��� ��ȣ�� �Է��Ͻÿ� > ");
		int i = sc.nextInt();

		System.out.print("�� ī�װ� > ");
		new_category = sc.next();

		sc.nextLine();
		System.out.print("�� ���� > ");
		new_title = sc.nextLine().trim();

		if (l.isDuplicate(new_title)) {
			System.out.println("������ �ߺ��˴ϴ�!");
			return;
		}
		System.out.print("�� ���� > ");
		new_description = sc.nextLine().trim();

		System.out.print("�� ��������(yyyy/mm/dd) > ");
		new_due_date = sc.nextLine().trim();

		TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date);
		t.setId(i);
		if (l.updateItem(t) > 0)
			System.out.println("�����Ǿ����ϴ�.");
	}

	public static void completeItem(TodoList l,int index) {
		if(l.completeItem(index) > 0)
			System.out.println("�Ϸ� üũ�Ͽ����ϴ�.");
	}
	
	public static void listAll(TodoList l) {
		System.out.printf("[��ü ���, �� %d��]\n", l.getCount());

		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[��ü ���, �� %d��]\n", l.getCount());
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	

	public static void listAll(TodoList l, int comp) {
		int count = 0;
		for (TodoItem item : l.getList(comp)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n�� %d���� �׸��� �Ϸ�Ǿ����ϴ�.\n", count);
	}

	
	public static void ls_cate(TodoList l) {
		int count = 0;
		for(String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.printf("\n�� %d���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.\n", count);
	}
	
	


	public static void find(TodoList l, String keyword) {
		int count = 0;
		for(TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n�� %d���� �׸��� ã�ҽ��ϴ�.\n", count);
	}

	public static void find_cate(TodoList l, String cate) {
		int count = 0;
		for(TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n�� %d���� �׸��� ã�ҽ��ϴ�.\n", count);
	}

//	public static void loadList(TodoList l, String string) {
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(string));
//			String oneline;
//			while ((oneline = br.readLine()) != null) {
//				StringTokenizer st = new StringTokenizer(oneline, "##");
//				String title = st.nextToken();
//				String category = st.nextToken();
//				String desc = st.nextToken();
//				String due_date = st.nextToken();
//				String current_date = st.nextToken();
//
//				TodoItem t = new TodoItem(title, category, desc, due_date, current_date);
//				l.addItem(t);
//			}
//			br.close();
//			System.out.println(l.getCount() + "���� �׸��� �о����ϴ�.");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public static void saveList(TodoList l, String string) {
		try {
			Writer w = new FileWriter(string);
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
