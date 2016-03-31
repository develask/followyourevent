package followyourevent;

import java.util.HashMap;

import javax.servlet.http.Cookie;

public class Sessions {
	
	private class Info{
		
		private String name;
		
		public Info(String name){
			this.name = name;
		}
		
		public String getName(){
			return this.name;
		}
	}
	
	private static Sessions me = null;
	private HashMap<String, Info> sessions = null;
	
	private Sessions(){
		this.sessions = new HashMap<String, Info>();
	}
	
	public static Sessions getSessions(){
		if (me == null) me = new Sessions();
		return me;
	}
	
	public void setNewSession(String cookie, String name){
		this.sessions.put(cookie, new Info(name));
	}
	
	public String getSessionName(String cookie) throws Exception{
		if (this.sessions.containsKey(cookie)){
			return this.sessions.get(cookie).getName();
		}else{
			throw new Exception("Cookie not Found");
		}
	}
	
	public String verifySession(Cookie[] cookies){
		Cookie cookie = null;
		if( cookies != null ){
			for (int i = 0; i < cookies.length; i++){
				cookie = cookies[i];
				if (cookie.getName().equals("oauth")){
					return this.sessions.containsKey(cookie.getValue())?cookie.getValue():null;
				}
			}
		}
		return null;
	}
	
	private void endSession(Cookie[] cookies){
		Cookie cookie = null;
		if( cookies != null ){
			for (int i = 0; i < cookies.length; i++){
				cookie = cookies[i];
				if (cookie.getName().equals("oauth")){
					this.sessions.remove(cookie.getValue());
				}
			}
		}
	}
}
