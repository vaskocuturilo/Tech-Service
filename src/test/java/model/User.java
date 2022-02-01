package model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("id")
	private int id;

	@SerializedName("exercisesList")
	private List<ExercisesListItem> exercisesList;

	@SerializedName("username")
	private String username;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setExercisesList(List<ExercisesListItem> exercisesList){
		this.exercisesList = exercisesList;
	}

	public List<ExercisesListItem> getExercisesList(){
		return exercisesList;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"id = '" + id + '\'' + 
			",exercisesList = '" + exercisesList + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}