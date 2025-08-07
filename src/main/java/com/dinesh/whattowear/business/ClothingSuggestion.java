package com.dinesh.whattowear.business;

import com.dinesh.whattowear.model.WeatherForecast;
import com.dinesh.whattowear.model.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClothingSuggestion {

    private final ChatClient chatClient;

    public ClothingSuggestion(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    static final Logger logger = LoggerFactory.getLogger(ClothingSuggestion.class);



    public String whatToWear(String template) {

        SystemMessage systemMessage = new SystemMessage("Given the weather forecast suggest me what should I wear in detailed for each time of the day from morning to evening as provided");
        UserMessage userMessage = new UserMessage(template);
//        System.out.println((chatClient.prompt(new Prompt(new UserMessage("hi"))).call().content()));
        Prompt prompt = new Prompt(List.of(systemMessage,userMessage));
        System.out.println("I am here");
        return chatClient.prompt(prompt).call().content();
    }
}
