package com.sandlex.progressbot.bot;

import com.sandlex.progressbot.CommandExecutor;
import com.sandlex.progressbot.InteractionStepExecutor;
import com.sandlex.progressbot.cache.InteractionStateMachine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        if (update.hasMessage()) {
            Message message = update.getMessage();
            String messageContent = message.getText();
            String chatId = message.getChatId().toString();
            Integer personId = message.getFrom().getId();
            if (messageContent.startsWith("/")) {
                interactionStateMachine.resetFor(personId);
                Optional<Commands> maybeCommand = Commands.fromString(messageContent.substring(1));
                maybeCommand.ifPresent(command -> {
                    BotResponse botResponse = commandExecutor.execute(command, message);
                    sendMessage(chatId, botResponse.getMessage(), botResponse.getCallbackOptions());
                });
            } else {
                sendMessage(chatId, interactionStepExecutor.execute(personId, messageContent), Collections.emptyList());
            }
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String selectedOption = callbackQuery.getData();
            Integer personId = callbackQuery.getFrom().getId();
            String chatId = callbackQuery.getMessage().getChatId().toString();
            sendMessage(chatId, interactionStepExecutor.execute(personId, selectedOption), Collections.emptyList());
        }
    }

    private void sendMessage(String chatId, String messageContent, List<String> callbackOptions) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(messageContent);
        if (!callbackOptions.isEmpty()) {
            setButtons(sendMessage, callbackOptions);
        }
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Exception: " + e.toString());
        }
    }

    private void setButtons(SendMessage sendMessage, List<String> callbackOptions) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

        callbackOptions.forEach(option -> {
            List<InlineKeyboardButton> buttonsRow = new ArrayList<>();
            buttonsRow.add(new InlineKeyboardButton().setText(option).setCallbackData(option));
            buttons.add(buttonsRow);
        });

        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        sendMessage.setReplyMarkup(markupKeyboard);
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
