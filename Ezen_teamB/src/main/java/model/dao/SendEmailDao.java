package model.dao;

public class SendEmailDao extends Dao{
	
	private static SendEmailDao sendEmailDao = new SendEmailDao();
	public static SendEmailDao getInstance() {
		return sendEmailDao;
	}
	private SendEmailDao() {}
	
	
	
	
	
}
