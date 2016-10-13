
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
 
public class SimpleBot extends TelegramLongPollingBot {
ManagmentSystem ms=null;
Connection connection=null;
Statement statement=null;
ResultSet resultSet=null;
    public SimpleBot() {
	try{
        ms=ManagmentSystem.getInstance("pathToConfig");
        connection=ms.getConn();

}catch(Exception ex){
Logger.getLogger(SimpleBot.class.getName()).log(Level.SEVERE, null, ex);
}
}
    
 
	public static void main(String[] args) {
		TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
		try {
			telegramBotsApi.registerBot(new SimpleBot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
 
	@Override
	public String getBotUsername() {
		return "kostaUserBot";
	}
 
	@Override
	public String getBotToken() {
		return "292803635:AAG8PNuZONfq63iI1q0UbYcicONFn6dl9ug";
	}
 
	@Override
	public void onUpdateReceived(Update update) {
	try{
         statement=connection.createStatement();
         String sql="";
         resultSet=statement.executeQuery(sql);
 	}catch(Exception ex){
        Logger.getLogger(SimpleBot.class.getName()).log(Level.SEVERE, null, ex);
	}
            
		Message message = update.getMessage();
		if (message != null && message.hasText()) {
			if (message.getText().equals("/help"))
				sendMsg(message, "ѕривет, € робот от Kosta");
			else
				sendMsg(message, "я не знаю что ответить на это");
		}
	}
 
	private void sendMsg(Message message, String text) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.enableMarkdown(true);
		sendMessage.setChatId(message.getChatId().toString());
		sendMessage.setReplyToMessageId(message.getMessageId());
		sendMessage.setText(text);
		try {
			sendMessage(sendMessage);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
 
}