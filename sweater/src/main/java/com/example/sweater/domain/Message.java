package com.example.sweater.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
// Аннотация  @Entity дает знать Spring,что нужно сохранить в БД
// Поле id будет идентификатором @Id
/**
 * @author ilinoa
 *
 */
@Entity
public class Message {
	@Id		
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;	
	
	private String text;		// Поле в котором будем хранить сообщения
	private String tag;			// Поле в котором будем хранить тэги
								// в этой связи одному user соответствует множество сообщений
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User author;		// Добавляем пол автора сообщений
	


	public Message() {
	}
	
	public Message(String text, String tag, User user ) {	// constructor
		this.author = user;
		this.text = text;
		this.tag = tag;
	}
	
	// проверка на существование автора сообщения
	public String getAuthorName() {
		return (author!=null) ? author.getUsername() : "<none>";
	}
	 
					/**Getter and Setter */
	
	public User getAuthor() {	return author;	}
	public void setAuthor(User author) {this.author = author;	}
	
	public Integer getId() 	{	return id;	}
	public void setId(Integer id) 	{	this.id = id;		}
	
	public String getText() { return text;	}
	public void setText(String text) { 	this.text = text; 	}
	
	public String getTag() 	{	return tag;	}
	public void setTag(String tag) {	this.tag = tag;		}
}


/* 	
 * 
 *  @ManyToOne(fetch = FetchType.EAGER) - EAGER дает  знать информацию об авторе
 *  @JoinColumn(name = "user_id") - изменяем название в базе данных
 *  В данном случае поле id =  @Id - иденитификатор  
 * 	Идентификатор нужен чтобы различать 2е записи в одной таблице
 * 	@GeneratedValue  = должен сам определять, какой идентификатор 
 *	и в каком порядке будет генериироваться
 */