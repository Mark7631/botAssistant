//package ru.mos.edu.mesassistant.updatehandler;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.telegram.telegrambots.meta.api.objects.*;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//
//import ru.mos.edu.mesassistant.inlinekeybord.KeyboardButtonCreatorService;
//import ru.mos.edu.mesassistant.inlinekeybord.KeyboardCreatorService;
//import ru.mos.edu.mesassistant.repository.*;
//import ru.mos.edu.mesassistant.resolver.ChatTypeHandlerResolver;
//import ru.mos.edu.mesassistant.resolver.GroupChatChatIdHandlerResolver;
//import ru.mos.edu.mesassistant.resolver.UserChatStateHandlerResolver;
//import ru.mos.edu.mesassistant.service.AuthenticationServiceDumbHardCodeImpl;
//import ru.mos.edu.mesassistant.service.AnswerSender;
//import ru.mos.edu.mesassistant.statehandler.ChatTypeHandler;
//import ru.mos.edu.mesassistant.statehandler.grouphandler.GroupTypeHandler;
//import ru.mos.edu.mesassistant.statehandler.userhandler.FinalInfoMessageBaseUserChatStateHandler;
//import ru.mos.edu.mesassistant.statehandler.userhandler.StartBaseUserChatStateHandler;
//import ru.mos.edu.mesassistant.statehandler.userhandler.UserTypeHandler;
//
//import static org.mockito.Mockito.verify;
//
//@SpringBootTest(classes = {CallbackQueryUpdateHandler.class, StartBaseUserChatStateHandler.class, ChatTypeHandlerResolver.class, UserSessionDumbMapCacheRepository.class, AuthenticationServiceDumbHardCodeImpl.class, KeyboardCreatorService.class, KeyboardButtonCreatorService.class, ButtonRepositoryImpl.class, TextRepositoryImpl.class, FileRepositoryImpl.class, ChatTypeHandler.class, UserTypeHandler.class, GroupTypeHandler.class, UserChatStateHandlerResolver.class, GroupChatChatIdHandlerResolver.class, FinalInfoMessageBaseUserChatStateHandler.class})
//public class CallbackQueryUpdateHandlerTests {
//    @Autowired
//    CallbackQueryUpdateHandler callbackQueryUpdateHandler;
//    @Autowired
//    StartBaseUserChatStateHandler startBaseUserChatStateHandler;
//
//    @MockBean
//    AnswerSender answerSender;
//    @Test
//    void checkMonitoring(){
//        Update update = new Update();
//        User user = new User(1077343565L, "Mghfghhb", false);
//        Chat chat = new Chat();
//        chat.setType("private");
//        Message message = new Message();
//        message.setChat(chat);
//        CallbackQuery callbackQuery = new CallbackQuery();
//        callbackQuery.setData("monitoring");
//        callbackQuery.setFrom(user);
//        callbackQuery.setMessage(message);
//        update.setCallbackQuery(callbackQuery);
//
//        try {
//            callbackQueryUpdateHandler.handle(update);
//        } catch (TelegramApiException e) {
//            throw new RuntimeException(e);
//        }
//        verify(startBaseUserChatStateHandler).handleCallbackQuery(callbackQuery);
//    }
//}
