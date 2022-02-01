package model;

import com.google.gson.annotations.SerializedName;

public class ExercisesListItem{

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private int id;

	@SerializedName("completed")
	private boolean completed;

	@SerializedName("title")
	private String title;

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCompleted(boolean completed){
		this.completed = completed;
	}

	public boolean isCompleted(){
		return completed;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return 
			"ExercisesListItem{" + 
			"description = '" + description + '\'' + 
			",id = '" + id + '\'' + 
			",completed = '" + completed + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}