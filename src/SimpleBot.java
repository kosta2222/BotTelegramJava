
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
 
public class SimpleBot extends TelegramLongPollingBot {
 
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