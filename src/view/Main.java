package view;

import java.util.Calendar;

import model.ModelException;
import model.Post;
import model.User;
import model.dao.DAOFactory;
import model.dao.PostDAO;
import model.dao.UserDAO;

public class Main {
	static PostDAO postDAO = DAOFactory.createDAO(PostDAO.class);
	static UserDAO userDAO = DAOFactory.createDAO(UserDAO.class);
	
	public static void main(String[] args) {
		testListPosts();
		testListUsers();
	}
	
	private static void testUpdateUser(int idUser, String name){
		// Pra dar certo precisa ter o user com o idUser
		User userASerAlterado = new User(idUser);
		userASerAlterado.setName(name);
		userASerAlterado.setGender("M");
		
		try {
			String message = userDAO.update(userASerAlterado) ? "Alterou!" : "Não alterou";
			
			System.out.println(message);
		} catch (ModelException e) {
			e.printStackTrace();
		}
	}
	
	private static void testDeleteUser(int idUser){
		// Pra dar certo precisa ter o user com o idUser
		User userASerDeletado = new User(idUser);
		
		try {
			String message = userDAO.delete(userASerDeletado) ? "Deletou!" : "Não deletou";
			
			System.out.println(message);
		} catch (ModelException e) {
			e.printStackTrace();
		}
	} 
	
	private static void testListUsers(){
		try {
			for (User u : userDAO.listAll()) {
				System.out.println(u.getName());
			}
		} catch (ModelException e) {
			e.printStackTrace();
		}
	} 
	
	private static void testInsertUser(){
		User newUser = new User();
		newUser.setName("Novo Usuário via DAO");
		newUser.setGender("M");
		newUser.setEmail("new@mail");
		
		try {
			String message = userDAO.save(newUser) ? "Inseriu!" : "Não inseriu";
			
			System.out.println(message);
		} catch (ModelException e) {
			// Imprime o erro
			e.printStackTrace();
		}
	}
	
	private static void testBuscaUserPorId(int userId) {		
		try {
			User u = userDAO.findById(userId);
			
			if (u != null)
				System.out.println(u.getName());
			else System.out.println("Usuário não encontrado");
		} catch (ModelException e) {
			e.printStackTrace();
			e.getCause().printStackTrace();
		}
	}
	
	private static void testBuscaPostPorId(int postId) {		
		try {
			Post p = postDAO.findById(postId);
			
			if (p != null)
				System.out.println(p.getUser().getName() + " postou " + p.getContent());
			else System.out.println("Post não encontrado");
		} catch (ModelException e) {
			e.printStackTrace();
			e.getCause().printStackTrace();
		}
	}

	private static void testInsertPost(int userId){
		Post newPost = new Post();
		newPost.setContent("Insert Via DAO " + 
				Calendar.getInstance().getTime().toString());
		
		newPost.setPostDate(Calendar.getInstance().getTime());
		
		// Pra não dar erro precisa ter user com userId no Banco
		newPost.setUser(new User(userId));
		
		try {
			String message = postDAO.save(newPost) ? "Inseriu!" : "Não inseriu";
			
			System.out.println(message);
		} catch (ModelException e) {
			// Imprime o erro
			e.printStackTrace();
		}
	}
	
	private static void testListPosts(){
		try {
			for (Post p : postDAO.listAll()) {
				System.out.println(p.getUser().getName() + " postou " + p.getContent());
			}
		} catch (ModelException e) {
			e.printStackTrace();
		}
	}
	
	private static void testDeletePost(int idPost){
		// Pra dar certo precisa ter o post com o idPost
		Post postASerDeletado = new Post(idPost);
		
		try {
			String message = postDAO.delete(postASerDeletado) ? "Deletou!" : "Não deletou";
			
			System.out.println(message);
		} catch (ModelException e) {
			e.printStackTrace();
		}
	}
	
	private static void testUpdatePost(int idPost, String content){
		// Pra dar certo precisa ter o post com o idPost
		Post postASerAlterado = new Post(idPost);
		postASerAlterado.setContent(content);
		
		try {
			String message = postDAO.update(postASerAlterado) ? "Alterou!" : "Não alterou";
			
			System.out.println(message);
		} catch (ModelException e) {
			e.printStackTrace();
		}
	} 
}
