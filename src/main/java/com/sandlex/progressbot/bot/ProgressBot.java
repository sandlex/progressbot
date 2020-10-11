package com.sandlex.progressbot.bot;

import com.sandlex.progressbot.CommandExecutor;
import com.sandlex.progressbot.InteractionStepExecutor;
import com.sandlex.progressbot.cache.InteractionStateMachine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class ProgressBot extends TelegramLongPollingBot {

    private final CommandExecutor commandExecutor;
    private final InteractionStateMachine interactionStateMachine;
    private final InteractionStepExecutor interactionStepExecutor;

    /**
     * Method for receiving messages.
     *
     * @param update Contains a message from the user.
     */
    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String messageContent = message.getText();
        String chatId = message.getChatId().toString();
        if (messageContent.startsWith("/")) {
            interactionStateMachine.resetFor(message.getFrom().getId());
            Optional<Commands> maybeCommand = Commands.fromString(messageContent.substring(1));
            maybeCommand.ifPresent(command -> sendMsg(chatId, commandExecutor.execute(command, message)));
        } else {
            sendMsg(chatId, interactionStepExecutor.execute(message));
        }
    }

    /**
     * Method for creating a message and sending it.
     *
     * @param chatId chat id
     * @param s      The String that you want to send as a message.
     */
    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Exception: " + e.toString());
        }
    }

    /**
     * This method returns the bot's name, which was specified during registration.
     *
     * @return bot name
     */
    @Override
    public String getBotUsername() {
        return System.getenv("BOT_NAME");
    }

    /**
     * This method returns the bot's token for communicating with the Telegram server
     *
     * @return the bot's token
     */
    @Override
    public String getBotToken() {
        return System.getenv("BOT_TOKEN");
    }

}
