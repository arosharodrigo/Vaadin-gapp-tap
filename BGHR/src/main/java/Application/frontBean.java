package Application;
import java.io.*;
public class frontBean implements Serializable{
private String id;
private String name;
private String add;
private String phone;
private String email;
private String desc;
private String showProf;
private String noProf;
private String hidden;
private int count;

public frontBean(){


}

    public int getCount(){
        return this.count;
    }

    public void setCount(int count){
	this.count = count;
    }
    public String getId(){
        return this.id;
    }

    public void setId(String id){
	this.id = id;
    }
    public String getHidden(){
        return this.hidden;
    }

    public void setHidden(String hidden){
	this.hidden = hidden;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
	this.name = name;
    }

    public String getAdd(){
	return this.add;
    }

    public void setAdd(String add){
	this.add = add;
    }

    public String getPhone(){
	return this.phone;
    }

    public void setPhone(String phone){
	this.phone = phone;
    }

    public String getEmail(){
	return this.email;
    }

    public void setEmail(String email){
	this.email = email;
    }

    public String getDesc(){
	return this.desc;
    }

    public void setDesc(String desc){
	this.desc = desc;
    }

    public String getShowProf(){
	return this.showProf;
    }

    public void setShowProf(String showProf){
	this.showProf = showProf;
    }

    public String getNoProf(){
	return this.noProf;
    }

    public void setNoProf(String noProf){
	this.noProf = noProf;
    }

}
