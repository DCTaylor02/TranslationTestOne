package me.nutt;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Translator {

    private static final OpenAiService service = new OpenAiService("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", Duration.ofMinutes(3));

    private static final HashMap<String, List<String>> translatedFiles = new HashMap<>();

    public Translator(){

    }

    public static List<String> translateText(String fileName, List<String> text){
        if (isTranslatedAlready(fileName)){
            return translatedFiles.get(fileName);
        }

        List<String> results = new ArrayList<>();
        List<List<ChatMessage>> actualMessages = new ArrayList<>();

        ChatMessage message = new ChatMessage(ChatMessageRole.USER.value());
        message.setContent("Translate the following to english as accurately as possible:");


        for (List<String> text1 : prepareForTranslation(text)){
            List<ChatMessage> chatMessage = new ArrayList<>();
            chatMessage.add(message);
            chatMessage.add(new ChatMessage(ChatMessageRole.USER.value(), text1.toString()));
            actualMessages.add(chatMessage);
        }


        /*
        For individually requesting translation of each sentence. Likely Impossible due to rate limits even assuming max rate limit.
        for (String string : text){
            List<ChatMessage> chatMessage = new ArrayList<>();
            chatMessage.add(message);
            chatMessage.add(new ChatMessage(ChatMessageRole.USER.value(), string));
            actualMessages.add(chatMessage);
        }
         */

        List<ChatCompletionRequest> requests = new ArrayList<>();
        for (List<ChatMessage> messages : actualMessages){
            ChatCompletionRequest request = ChatCompletionRequest.builder().model("gpt-3.5-turbo-0613").messages(messages).topP(0.1).build();
            requests.add(request);
        }

        List<ChatCompletionChoice> choices1 = new ArrayList<>();
        for (ChatCompletionRequest request : requests){
            choices1.addAll(service.createChatCompletion(request).getChoices());
        }

        for (ChatCompletionChoice choice : choices1){
            results.add(choice.getMessage().getContent());
        }

        translatedFiles.put(fileName, results);
        return results;
    }

    public static boolean isTranslatedAlready(String fileName){
        return translatedFiles.containsKey(fileName);
    }


    public static List<List<String>> prepareForTranslation(List<String> text){
        List<List<String>> sections = new ArrayList<>();
        List<String> tempText = new ArrayList<>(text);

        int charCount = text.toString().length();
        double num = (double) charCount/3000;
        if (num <= 1){
            return new ArrayList<>(Collections.singleton(text));
        }

        num = Math.ceil(num);
        int max = text.size()-1;
        max = max/(int)num;
        for (double i = num; i > 0; i--){
            max = Math.min(max, tempText.size() - 1);
            List<String> sub = tempText.subList(0, max);
            List<String> actual = new ArrayList<>(sub);
            sub.clear();
            sections.add(actual);
        }

        return sections;
    }

}
