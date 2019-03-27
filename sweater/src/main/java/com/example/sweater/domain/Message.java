package com.example.sweater.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// Аннотация  @Entity дает знать Spring,что нужно сохранить в БД
// Поле id будет идентификатором @Id
@Entity
public class Message {
	@Id		
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;	
	
	private String text;		// Поле в котором будем хранить сообщения
	private String tag;			// Поле в котором будем хранить тэги
	
	public Message() {
	}
	
	public Message(String text, String tag) {
		this.text = text;
		this.tag = tag;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
}


/* 	
 *  В данном случае поле id =  @Id - иденитификатор  
 * 	Идентификатор нужен чтобы различать 2е записи в одной таблице
 * 	@GeneratedValue  = должен сам определять, какой идентификатор 
 *	и в каком порядке будет генериироваться
 */