package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	private int id;
    private String title;
    private String category;
    private String desc;
    private String due_date;
    private String current_date;
    private int is_completed;

	public TodoItem(String category, String title, String desc, String due_date){
        this.title=title;
        this.category=category;
        this.desc=desc;
        this.due_date=due_date;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date = f.format(new Date());
    }
	
	public TodoItem(String category, String title, String desc, String due_date, int is_completed){
        this.title=title;
        this.category=category;
        this.desc=desc;
        this.due_date=due_date;
        this.is_completed=is_completed;
    }
    
    public TodoItem(String category, String title, String desc, String due_date, String current_date){
        this.title=title;
        this.category=category;
        this.desc=desc;
        this.due_date=due_date;
        this.current_date=current_date;
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

    public String getCurrent_date() {
        return current_date;
    }

	public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
	
	public int getIs_completed() {
		return is_completed;
	}

	public void setIs_completed(int is_completed) {
		this.is_completed = is_completed;
	}
	
	@Override
	public String toString() {
		if(this.getIs_completed() == 1)
			return id + " [" + category + "] " + "[V] " + title + " - " + desc + " - " + due_date + " - " + current_date;
		return id + " [" + category + "] " + title + " - " + desc + " - " + due_date + " - " + current_date;
	}
	
	public String toSaveString() {
		return category + "##" + title + "##" + desc + "##" + due_date + "##" + current_date + "\n";
	}
}
