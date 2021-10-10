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

		System.out.print("[항목 추가]\n" + "카테고리 > ");
		category = sc.next();

		sc.nextLine();
		System.out.print("제목 > ");
		title = sc.nextLine().trim();

		if (l.isDuplicate(title)) {
			System.out.println("제목이 중복됩니다!");
			return;
		}

		System.out.print("내용 > ");
		desc = sc.nextLine().trim();

		System.out.print("마감일자(yyyy/mm/dd) > ");
		due_date = sc.nextLine().trim();

		TodoItem t = new TodoItem(category, title, desc, due_date);

		if (l.addItem(t) > 0)
			System.out.println("추가되었습니다.");
	}

	public static void deleteItem(TodoList l) {

		Scanner sc = new Scanner(System.in);

		System.out.print("[항목 삭제]\n" + "삭제할 항목의 번호를 입력하시오 > ");
		int i = sc.nextInt();

		if(l.deleteItem(i) > 0)
			System.out.println("삭제되었습니다.");
	}

	public static void updateItem(TodoList l) {

		String new_category, new_title, new_description, new_due_date;
		Scanner sc = new Scanner(System.in);

		System.out.print("[항목 수정]\n" + "수정할 항목의 번호를 입력하시오 > ");
		int i = sc.nextInt();

		System.out.print("새 카테고리 > ");
		new_category = sc.next();

		sc.nextLine();
		System.out.print("새 제목 > ");
		new_title = sc.nextLine().trim();

		if (l.isDuplicate(new_title)) {
			System.out.println("제목이 중복됩니다!");
			return;
		}
		System.out.print("새 내용 > ");
		new_description = sc.nextLine().trim();

		System.out.print("새 마감일자(yyyy/mm/dd) > ");
		new_due_date = sc.nextLine().trim();

		TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date);
		t.setId(i);
		if (l.updateItem(t) > 0)
			System.out.println("수정되었습니다.");
	}

	public static void completeItem(TodoList l,int index) {
		if(l.completeItem(index) > 0)
			System.out.println("완료 체크하였습니다.");
	}
	
	public static void listAll(TodoList l) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());

		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
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
		System.out.printf("\n총 %d개의 항목이 완료되었습니다.\n", count);
	}

	
	public static void ls_cate(TodoList l) {
		int count = 0;
		for(String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다.\n", count);
	}
	
	


	public static void find(TodoList l, String keyword) {
		int count = 0;
		for(TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n총 %d개의 항목을 찾았습니다.\n", count);
	}

	public static void find_cate(TodoList l, String cate) {
		int count = 0;
		for(TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n총 %d개의 항목을 찾았습니다.\n", count);
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
//			System.out.println(l.getCount() + "개의 항목을 읽었습니다.");
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
